package syntaxchecking.methods.declarations;

import syntaxchecking.VariablesTypes;
import syntaxchecking.methods.exceptions.DeclartionException;
import utilities.MethodsPair;
import utilities.Pair;
import utilities.VariablesPair;


import java.util.*;
import java.util.regex.Matcher;

import static syntaxchecking.methods.declarations.Expressions.*;


/**
 * a class that analyzes void methods' patterns.
 */
public class VoidDeclaration {
    public static final char WHITE_SPACE = ' ';
    public static final char COMMA = ',';


    public static MethodsPair analyzeDeclaration(String line, Map<String, Pair<String, Boolean>> paramsMap)
            throws DeclartionException {
        // Try to verify the general struct of a method:
        Matcher genericMatcher = VOID_METHOD_DEC_PATTERN.matcher(line);

        if (!genericMatcher.matches()) {
            throw new DeclartionException();
        }
        // take out the parameters:
        Matcher matcherParams = PARAMS_EXTRACTION_PATTERN.matcher(line);
        matcherParams.find();
        String params = matcherParams.group(1);
        List<String> paramsList = Arrays.stream(params.split((",+ *,*"))).toList();

        if (paramsList.size() != countOccurrences(params, COMMA) + 1) {
            throw new DeclartionException();
        }
        List<String> typesList = new ArrayList<>();
        for (String param : paramsList) {
            VariablesPair temp = analyzeParameter(param);
            paramsMap.put(temp.getName(), temp);
            typesList.add(temp.getFirst());
        }
        return new MethodsPair(extractMethodName(line),typesList);
    }

    /**
     *
     * @param methodLine
     * @return
     */
    public static String extractMethodName(String methodLine){
        return methodLine.substring(methodLine.indexOf(' ')+1,methodLine.indexOf("("));
    }
    /**
     * Counts the number of occurrences of a char appears in a String.
     *
     * @param line: a given String.
     * @return the number of occurrences of the char in the String.
     */
    private static int countOccurrences(String line, char someChar) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == someChar) {
                count++;
            }
        }
        return count;
    }


    /**
     * Takes out all white-spaces that are more than one.
     *
     * @param str: a given String
     * @return a corrected string with only 1 white-space in between words.
     */
    private static String fixBlankSpots(String str) {
        str = str.trim();
        str = str.replaceAll("\s{2,}", " ");
        return str;
    }


    public static VariablesPair analyzeParameter(String paramLine) throws DeclartionException {
        String params = fixBlankSpots(paramLine);
        List<String> paramsList = Arrays.stream(params.split("\s")).toList();
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
//        return new VariablesPair(paramsList.get(place+1), new TypeFinalPair(paramsList.get(place), finalFlag));
    }

    public static void main(String[] args) {
        Map<String, Pair<String, Boolean>> paramsMap = new HashMap<>();
        try {
            MethodsPair p = analyzeDeclaration("void foo(int a, final boolean b,String s){", paramsMap);
            p.printMethodsPair();


        }
        catch(DeclartionException e){
            System.out.println("Error!");
        }
    }
}
