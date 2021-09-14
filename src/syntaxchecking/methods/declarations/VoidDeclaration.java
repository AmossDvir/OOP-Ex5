package syntaxchecking.methods.declarations;

import syntaxchecking.VariablesTypes;
import syntaxchecking.methods.exceptions.DeclarationException;
import syntaxchecking.variables.Variable;
import syntaxchecking.variables.VariableException;
import utilities.MethodsPair;

import java.util.*;
import java.util.regex.Matcher;

import static syntaxchecking.ReservedWords.*;
import static syntaxchecking.methods.Method.extractMethodName;
import static syntaxchecking.variables.Variable.GLOBAL_LINE;
import static utilities.RegexExpressions.*;
import static utilities.StringManipulations.*;

/**
 * a static class that analyzes methods' declarations.
 */
public class VoidDeclaration {

    // Constants:
    private static final int FINAL_VAR = 3;
    private static final int NON_FINAL_VAR = 2;

    /**
     * Gets a line of a method declaration and filters the parameters and the name.
     *
     * @param line:      String, the code line.
     * @param paramsMap: stored variables the has been already declared.
     * @return : a Method Pair, contains the name and the parameters of the method.
     * @throws DeclarationException: in case of invalid arguments.
     */
    public static MethodsPair analyzeDeclaration(String line, Map<String, Variable> paramsMap)
            throws DeclarationException, VariableException {
        HashSet<String> paramsSet = new HashSet<>();

        // Take out the parameters:
        String params = extractFromParantheses(line);
        if (params.equals("")) {
            return new MethodsPair(extractMethodName(line), new ArrayList<String>());
        } else {
            List<String> paramsList = Arrays.asList(params.split(SPLIT_PARAMS));

            if (paramsList.size() != countOccurrences(params, COMMA) + 1) {
                throw new DeclarationException();
            }
            // Put parameters in paramsMap:
            List<String> typesList = new ArrayList<>();
            for (String param : paramsList) {

                Variable temp = analyzeParameter(param);
                if (paramsSet.contains(temp.getName())){
                    throw new VariableException();
                }
                else{
                    paramsSet.add(temp.getName());
                }
                paramsMap.put(temp.getName(), temp);
                typesList.add(temp.getType());
            }
            return new MethodsPair(extractMethodName(line), typesList);
        }
    }

    /**
     * Analyzes a single parameter given from the method declaration.
     *
     * @param paramLine: a String, the given parameter.
     * @return : a Variables object.
     * @throws DeclarationException: in case of invalid parameter String.
     */
    public static Variable analyzeParameter(String paramLine) throws DeclarationException, VariableException {
        // Split it:
        List<String> paramsList = splitToWords(paramLine);
        boolean finalFlag = false;
        int place = 0;
        // Check if final:
        if (paramsList.get(0).equals(FINAL)) {
            // Check if invalid number of words (should be 3):
            if (paramsList.size() != FINAL_VAR) {
                throw new DeclarationException();
            }
            finalFlag = true;
            place = 1;
        }
        // Check if valid type:
        else if (!VariablesTypes.TYPES_LIST.contains(paramsList.get(place))) {
            throw new DeclarationException();
        }
        // Check if invalid number of words (should be 2):
        else if (paramsList.size() != NON_FINAL_VAR) {
            throw new DeclarationException();
        }
        // Check if valid name:
        Matcher matcher = PARAMS_NAME_PATTERN.matcher(paramsList.get(place + 1));
        if (!matcher.matches()) {
            throw new DeclarationException();
        }
        return new Variable(paramsList.get(place + 1), paramsList.get(place), finalFlag,true, GLOBAL_LINE,false);
    }
}
