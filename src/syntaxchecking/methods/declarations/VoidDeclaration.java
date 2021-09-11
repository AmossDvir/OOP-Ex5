package syntaxchecking.methods.declarations;

import syntaxchecking.VariablesTypes;
import syntaxchecking.methods.exceptions.DeclarationException;
import utilities.MethodsPair;
import utilities.Pair;
import utilities.VariablesPair;


import java.util.*;
import java.util.regex.Matcher;

import static syntaxchecking.methods.Method.extractMethodName;
import static syntaxchecking.ReservedWords.*;
import static utilities.RegexExpressions.*;
import static utilities.StringManipulations.*;

/**
 * a static class that analyzes methods' declarations.
 */
public class VoidDeclaration {

    /**
     * Gets a line of code
     *
     * @param line:
     * @param paramsMap:
     * @return :
     * @throws DeclarationException:
     */
    public static MethodsPair analyzeDeclaration(String line, Map<String, Pair<String, Boolean>> paramsMap)
            throws DeclarationException {
        // Try to verify the general struct of a method:
        Matcher genericMatcher = VOID_METHOD_DEC_PATTERN.matcher(line);

        if (!genericMatcher.matches()) {
            throw new DeclarationException();
        }
        // take out the parameters:
        String params = extractFromParantheses(line);
        List<String> paramsList = Arrays.asList(params.split(SPLIT_PARAMS));

        if (paramsList.size() != countOccurrences(params, COMMA) + 1) {
            throw new DeclarationException();
        }
        List<String> typesList = new ArrayList<>();
        for (String param : paramsList) {
            VariablesPair temp = analyzeParameter(param);
            paramsMap.put(temp.getName(), temp);
            typesList.add(temp.getFirst());
        }
        return new MethodsPair(extractMethodName(line), typesList);
    }

    /**
     * @param methodLine
     * @return
     */
    public static String extractMethodName(String methodLine) {
        return methodLine.substring(methodLine.indexOf(WHITE_SPACE_CHAR) + 1, methodLine.indexOf("("));
    }


    public static VariablesPair analyzeParameter(String paramLine) throws DeclarationException {
        List<String> paramsList = splitToWords(paramLine);
        boolean finalFlag = false;
        int place = 0;
        if (paramsList.get(0).equals(FINAL)) {
            if (paramsList.size() != 3) {
                throw new DeclarationException();
            }
            finalFlag = true;
            place = 1;

        } else if (!VariablesTypes.TYPES_LIST.contains(paramsList.get(place))) {
            throw new DeclarationException();

        } else if (paramsList.size() != 2) {
            throw new DeclarationException();
        }
        Matcher matcher = PARAMS_NAME_PATTERN.matcher(paramsList.get(place + 1));
        if (!matcher.matches()) {
            throw new DeclarationException();
        }

        return new VariablesPair(paramsList.get(place + 1), paramsList.get(place), finalFlag);
    }
}
