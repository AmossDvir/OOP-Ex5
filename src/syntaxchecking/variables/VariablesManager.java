package syntaxchecking.variables;

import java.util.*;

import static syntaxchecking.ReservedWords.*;
import static syntaxchecking.VariablesTypes.TYPES_LIST;
import static syntaxchecking.variables.TypeIdentifier.isInstanceOf;
import static utilities.StringManipulations.splitToWords;

public class VariablesManager {
    private static final String EQUAL = "=";
    private static final String SEMICOLON = ";";
    private Map<String, Variable> registry;

    public VariablesManager(String variableLine) {

        this.registry = new HashMap<>();
    }

    public void analyzeVariableLine(String line) throws VariableException {
        line = line.replaceAll("=", " = ");
        List<String> words = splitToWords(line.replaceAll(",", " "));
        prefixCheck(words);


    }

    private boolean isAssignment(String line) {
        return line.contains(EQUAL);
    }

    public void analyze(List<String> words, String type, boolean isFinal) throws VariableException {
        int i = 0;
        while (i < words.size()) {
            String word = words.get(i);
            Variable var = new Variable(word,type,isFinal);
            if (i < words.size() - 2) {
                if (words.get(i + 1).equals(EQUAL)) {
                    assignmentCheck(word, words.get(i + 2), type);
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
            assignmentCheck(firstWord, words.get(2), type);
        }

    }

    /**
     * @param word1 Name of variable to assign
     * @param word2 Value to assign
     * @throws VariableException
     */
    private void assignmentCheck(String word1, String word2, String type) throws VariableException {

        //Check if the value is another variable we declared before
        if (registry.containsKey(word2)) {
            if (!registry.get(word2).getType().equals(type)) {
                throw new VariableException();
            }
            if (!registry.get(word2).isInitialized()) {
                throw new VariableException();
            }
        } else {
            if (!isInstanceOf(type, word2)) {
                throw new VariableException();
            }
        }


    }

    public static void main(String[] args) throws VariableException {
        VariablesManager v = new VariablesManager("int i1 , i2 =6 ");
        List<String> lst = Arrays.asList("a", "b", "c", "d", "=", "true", "e", "f", "=", "5");
        v.analyze(lst, "boolean", false);
        for (String key : v.registry.keySet()) {
            System.out.println(key);
            System.out.println(v.registry.get(key).getType());
            System.out.println(v.registry.get(key).isFinal());
            System.out.println(v.registry.get(key).isInitialized());
        }
    }


}
