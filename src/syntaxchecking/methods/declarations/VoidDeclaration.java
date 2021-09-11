package syntaxchecking.methods.declarations;

import syntaxchecking.VariablesTypes;
import syntaxchecking.methods.exceptions.DeclartionException;
import utilities.MethodsPair;
import utilities.Pair;
import utilities.VariablesPair;


import java.util.*;
import java.util.regex.Matcher;

import static syntaxchecking.methods.Method.extractMethodName;
import static utilities.RegexExpressions.*;
import static utilities.StringManipulations.*;


/**
 * a class that analyzes void methods' patterns.
 */
public class VoidDeclaration {


    public static MethodsPair analyzeDeclaration(String line, Map<String, Pair<String, Boolean>> paramsMap)
            throws DeclartionException {
        // Try to verify the general struct of a method:
        Matcher genericMatcher = VOID_METHOD_DEC_PATTERN.matcher(line);

        if (!genericMatcher.matches()) {
            throw new DeclartionException();
        }
        // take out the parameters:
        String params = extractFromParantheses(line);
        List<String> paramsList = Arrays.stream(params.split(SPLIT_PARAMS)).toList();

        if (paramsList.size() != countOccurrences(params, COMMA) + 1) {
            throw new DeclartionException();
        }
        List<String> typesList = new ArrayList<>();
        for (String param : paramsList) {
            VariablesPair temp = analyzeParameter(param);
            paramsMap.put(temp.getName(), temp);
            typesList.add(temp.getFirst());
        }
        return new MethodsPair(extractMethodName(line), typesList);
    }


    public static VariablesPair analyzeParameter(String paramLine) throws DeclartionException {
        List<String> paramsList = splitToWords(paramLine);
        boolean finalFlag = false;
        int place = 0;
        if (paramsList.get(0).equals("final")) {
            if (paramsList.size() != 3) {
                throw new DeclartionException();
            }
            finalFlag = true;
            place = 1;

        } else if (!VariablesTypes.TYPES_LIST.contains(paramsList.get(place))) {
            throw new DeclartionException();

        } else if (paramsList.size() != 2) {
            throw new DeclartionException();
        }
        Matcher matcher = PARAMS_NAME_PATTERN.matcher(paramsList.get(place + 1));
        if (!matcher.matches()) {
            throw new DeclartionException();
        }

        return new VariablesPair(paramsList.get(place + 1), paramsList.get(place), finalFlag);
    }
}
