package syntaxchecking.variables;

import static syntaxchecking.variables.Variable.GLOBAL;

import java.util.*;

import static syntaxchecking.ReservedWords.*;
import static syntaxchecking.VariablesTypes.TYPES_LIST;
import static syntaxchecking.variables.TypeIdentifier.isInstanceOf;
import static utilities.StringManipulations.splitToWords;

public class VariablesManager {
    // Members:
    private static final String EQUALS_SIGN = "=";
    private static final String SEMICOLON = ";";
    // Here we store all the variables:
    private Map<String, Variable> registry;

    // Constructors:

    /**
     *
     */
    public VariablesManager(Map<String, Variable> variableMap) {
        this.registry = variableMap;
    }

    public void analyzeVariableLine(String line, int lineIndex, boolean isGlobal) throws VariableException {
        line = line.replaceAll("\\s*=\\s*", "\\s=\\s");
        List<String> words = splitToWords(line.replaceAll(",", " "));
        prefixCheck(words, lineIndex, isGlobal);
    }

    private boolean isAssignment(String line) {
        return line.contains(EQUALS_SIGN);
    }

    public void analyze(List<String> words, String type, boolean isFinal, int lineIndex, boolean isGlobal)
            throws VariableException {
        int i = 0;
        while (i < words.size()) {
            String varName = words.get(i);
            Variable var;
            if (isGlobal) {
                var = new Variable(varName, type, isFinal, GLOBAL);
            } else {
                var = new Variable(varName, type, isFinal, lineIndex);
            }
            if (i < words.size() - 2) {
                if (words.get(i + 1).equals(EQUALS_SIGN)) {
                    valueCheck(words.get(i + 2), type,lineIndex);
                    var.setInitialized(true);
                    i += 3;
                } else {
                    i++;
                }
            } else {
                i++;
            }
            varFinalCheck(var);
            registry.put(varName, var);
        }
    }

    public void varFinalCheck(Variable var) throws VariableException {
        if (var.isFinal() && !var.isInitialized()) {
            throw new VariableException();
        }
    }

    private void validType(String type) throws VariableException {
        if (!TYPES_LIST.contains(type)) {
            throw new VariableException();
        }
    }

    private boolean ifvalidType(String type) {
        return TYPES_LIST.contains(type);
    }

    /**
     * Returns a HashMap of variables.
     *
     * @return : HashMap of variables.
     */
    public Map<String, Variable> getVariables() {
        return registry;
    }

    public void prefixCheck(List<String> words, int lineIndex, boolean isGlobal) throws VariableException {
        String firstWord = words.get(0);
        String secondWord = words.get(1);
        String type;
        if (firstWord.equals(FINAL)) {
            validType(secondWord);
            type = secondWord;
            analyze(words.subList(2, words.size()), type, true, lineIndex, isGlobal);
        } else if (ifvalidType(firstWord)) {
            type = firstWord;
            analyze(words.subList(1, words.size()), type, false, lineIndex, isGlobal);
        } else {
            if (!registry.containsKey(firstWord)) {
                throw new VariableException();
            }
            if (registry.get(firstWord).getDefinedLine() > lineIndex) {
                throw new VariableException();
            }

            type = registry.get(firstWord).getType();
            valueCheck(words.get(2), type, lineIndex);
        }
    }

    /**
     * @param value Name of variable to assign
     * @param value Value to assign
     * @throws VariableException
     */
    private void valueCheck(String value, String type, int line) throws VariableException {

        // Check if the value is another variable we declared before:
        if (registry.containsKey(value)) {
            if (!registry.get(value).getType().equals(type) || !registry.get(value).isInitialized() ||
                    registry.get(value).getDefinedLine() > line) {
                throw new VariableException();
            }
        } else {
            if (!isInstanceOf(type, value)) {
                throw new VariableException();
            }
        }
        registry.get(value).setInitialized(true);
    }

    public static void main(String[] args) throws VariableException {
//        v.analyzeVariableLine("final int a,b=5,c");
//        List<String> lst = Arrays.asList("a", "b", "c,,", "d", "=", "true", "e", "f", "=", "5");
//        v.analyze(lst, "boolean", false);
//        for (String key : v.registry.keySet()) {
//            System.out.println(key);
//            System.out.println(v.registry.get(key).getType());
//            System.out.println(v.registry.get(key).isFinal());
//            System.out.println(v.registry.get(key).isInitialized());
//        }
    }
}
