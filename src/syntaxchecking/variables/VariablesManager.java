package syntaxchecking.variables;

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
    public VariablesManager() {
        this.registry = new HashMap<>();
    }

    public void analyzeVariableLine(String line) throws VariableException {
        line = line.replaceAll("\\s*=\\s*", "\\s=\\s");
        List<String> words = splitToWords(line.replaceAll(",", " "));
        prefixCheck(words);
    }

    private boolean isAssignment(String line) {
        return line.contains(EQUALS_SIGN);
    }

    public void analyze(List<String> words, String type, boolean isFinal) throws VariableException {
        int i = 0;
        while (i < words.size()) {
            String word = words.get(i);
            Variable var = new Variable(word,type,isFinal);
            if (i < words.size() - 2) {
                if (words.get(i + 1).equals(EQUALS_SIGN)) {
                    valueCheck(words.get(i + 2), type);
                    var.setInitialized(true);
                    i += 3;
                } else {
                    i++;
                }
            } else {
                i++;
            }
            varFinalCheck(var);
            registry.put(word, var);
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
     * @return : HashMap of variables.
     */
    public Map<String, Variable> getVariables() {
        return registry;
    }

    public void prefixCheck(List<String> words) throws VariableException {
        String firstWord = words.get(0);
        String secondWord = words.get(1);
        String type;
        if (firstWord.equals((FINAL))) {
            validType(secondWord);
            type = secondWord;
            analyze(words.subList(2, words.size()), type, true);
        } else if (ifvalidType(firstWord)) {
            type = firstWord;
            analyze(words.subList(1, words.size()), type, false);
        } else {
            if (!registry.containsKey(firstWord)) {
                throw new VariableException();
            }
            type = registry.get(firstWord).getType();
            valueCheck(words.get(2), type);
        }
    }

    /**
     * @param word Name of variable to assign
     * @param word Value to assign
     * @throws VariableException
     */
    private void valueCheck(String word, String type) throws VariableException {

        //Check if the value is another variable we declared before
        if (registry.containsKey(word)) {
            if (!registry.get(word).getType().equals(type)) {
                throw new VariableException();
            }
            if (!registry.get(word).isInitialized()) {
                throw new VariableException();
            }
        } else {
            if (!isInstanceOf(type, word)) {
                throw new VariableException();
            }
        }
    }

    public static void main(String[] args) throws VariableException {
        VariablesManager v = new VariablesManager();
        v.analyzeVariableLine("final int a,b=5,c");
        List<String> lst = Arrays.asList("a", "b", "c,,", "d", "=", "true", "e", "f", "=", "5");
        v.analyze(lst, "boolean", false);
        for (String key : v.registry.keySet()) {
            System.out.println(key);
            System.out.println(v.registry.get(key).getType());
            System.out.println(v.registry.get(key).isFinal());
            System.out.println(v.registry.get(key).isInitialized());
        }
    }
}
