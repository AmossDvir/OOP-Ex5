package syntaxchecking.methods.invoking;

import syntaxchecking.methods.exceptions.InvokingException;
import syntaxchecking.variables.Variable;
import syntaxchecking.variables.VariableException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static syntaxchecking.variables.TypeIdentifier.isInstanceOf;
import static utilities.RegexExpressions.COMMA;
import static utilities.RegexExpressions.SPLIT_PARAMS;
import static utilities.StringManipulations.countOccurrences;
import static utilities.StringManipulations.extractFromParantheses;

/**
 * a class for representing a call for a method.
 */
public class MethodCall {
    // Members:
    private final String methodCalledName;
    private final String methodCalledParams;
    private final Map<String, List<String>> methodList;

    // Constructors:

    /**
     * Stores a given method List and the String in which the method has been called,
     * and extracts the name out of it.
     *
     * @param callLine:   String in which the method has been called
     * @param methodList: a given method List
     */
    public MethodCall(String callLine, Map<String, List<String>> methodList) {
        this.methodCalledParams = extractFromParantheses(callLine);
        this.methodCalledName = extractMethodName(callLine);
        this.methodList = methodList;
    }

    public String extractMethodName(String line) {
        line = line.trim();
        return line.substring(0, line.indexOf("(")).trim();

    }

    /**
     * Checks whether a call for a method is valid or not.
     *
     * @throws InvokingException: in case the method call isn't valid.
     */
    public void checkMethodCall(Map<String, Variable> variables) throws InvokingException {
        // Check if such a method exists:
        if (!methodList.containsKey(methodCalledName)) {
            throw new InvokingException();
        }
        // Check if the parameters are in a valid pattern:
        List<String> paramsList = Arrays.asList(methodCalledParams.split(SPLIT_PARAMS));
        if (paramsList.size() == 1 && paramsList.get(0).equals("")) {
            paramsList = new ArrayList<>();
        }
        if (paramsList.size() != 0) {
            if (paramsList.size() != countOccurrences(methodCalledParams, COMMA) + 1) {
                throw new InvokingException();
            }
        }
        List<String> methodValidTypes = methodList.get(methodCalledName);
        if (methodValidTypes.size() != paramsList.size()) {
            throw new InvokingException();
        }
        // Check validity for the parameters themselves:
        for (int i = 0; i < paramsList.size(); i++) {
            String param = paramsList.get(i).trim();
            String wantedType = methodValidTypes.get(i);
            // Check if param is not a known variable and illegal:
            if (!variables.containsKey(param)) {
                if (!isInstanceOf(wantedType, param)) {
                    throw new InvokingException();
                }
                // param is a known variable:
            } else {
                // Check if illegal:
                if (!variables.get(param).getType().equals(wantedType)) {
                    throw new InvokingException();
                }
            }
        }
    }
}