package syntaxchecking.methods.invoking;

import syntaxchecking.methods.exceptions.InvokingException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static syntaxchecking.methods.Method.extractMethodName;
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
     * @param callLine: String in which the method has been called
     * @param methodList: a given method List
     */
    public MethodCall(String callLine,Map<String, List<String>> methodList) {
        this.methodCalledParams = extractFromParantheses(callLine);
        this.methodCalledName = extractMethodName(callLine);
        this.methodList = methodList ;
    }

    /**
     * Checks whether a call for a method is valid or not.
     * @throws InvokingException: in case the method call isn't valid.
     */
    public void checkMethodCall() throws InvokingException {
        // Check if such a method exists:
        if (!methodList.containsKey(methodCalledName)){
            throw new InvokingException();
        }
        // Check if the parameters are in a valid pattern:
        List<String> paramsList = Arrays.asList(methodCalledParams.split(SPLIT_PARAMS));
        if (paramsList.size() != countOccurrences(methodCalledParams, COMMA) + 1) {
            throw new InvokingException();
        }
        List<String> methodValidTypes = methodList.get(methodCalledName);
        if (methodValidTypes.size() != paramsList.size()){
            throw new InvokingException();
        }
        // Check validity for the parameters themselves:
        for (int i =0 ; i<paramsList.size(); i++){
            String param = paramsList.get(i).trim();
            String wantedType = methodValidTypes.get(i);
            if (!isInstanceOf(wantedType,param)){
                throw new InvokingException();
            }
        }
    }
}

