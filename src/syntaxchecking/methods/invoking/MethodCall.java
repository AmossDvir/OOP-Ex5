package syntaxchecking.methods.invoking;

import syntaxchecking.methods.exceptions.DeclartionException;
import syntaxchecking.methods.exceptions.InvokingException;
import utilities.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static syntaxchecking.methods.Method.extractMethodName;
import static syntaxchecking.variables.TypeIdentifier.isInstanceOf;
import static utilities.RegexExpressions.COMMA;
import static utilities.RegexExpressions.SPLIT_PARAMS;
import static utilities.StringManipulations.countOccurrences;
import static utilities.StringManipulations.extractFromParantheses;

public class MethodCall {
    private final String methodCalledName;
    private final String methodCalledParams;
    private final Map<String, List<String>> methodList;

    public MethodCall(String callLine,Map<String, List<String>> methodList) {

        this.methodCalledParams = extractFromParantheses(callLine);
        this.methodCalledName = extractMethodName(callLine);
        this.methodList = methodList ;
    }
    public void checkMethodCall() throws InvokingException {
        if (!methodList.containsKey(methodCalledName)){
            throw new InvokingException();
        }

        List<String> paramsList = Arrays.stream(methodCalledParams.split(SPLIT_PARAMS)).toList();
        if (paramsList.size() != countOccurrences(methodCalledParams, COMMA) + 1) {
            throw new InvokingException();
        }
        List<String> methodValidTypes = methodList.get(methodCalledName);
        if (methodValidTypes.size() != paramsList.size()){
            throw new InvokingException();
        }

        for (int i =0 ; i<paramsList.size(); i++){
            String param = paramsList.get(i).trim();
            String wantedType = methodValidTypes.get(i);
            if (!isInstanceOf(wantedType,param)){
                throw new InvokingException();
            }
        }
    }



}

