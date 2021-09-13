package syntaxchecking.variables;

import java.util.regex.Matcher;

import static syntaxchecking.VariablesTypes.*;
import static utilities.RegexExpressions.*;

/**
 * a class that helps identify the variable type.
 */
public class TypeIdentifier {

    /**
     * Tells if a single variable is type of some given type.
     * @param wantedType: a given type.
     * @param variable: a given variable.
     * @return : True if so, else: false.
     */
    public static boolean isInstanceOf(String wantedType, String variable) {
        // Create Matchers:
        Matcher intMatcher = INT_TYPE_PATTERN.matcher(variable);
        Matcher doubleMatcher = DOUBLE_TYPE_PATTERN.matcher(variable);
        Matcher stringMatcher = STRING_TYPE_PATTERN.matcher(variable);
        Matcher charMatcher = CHAR_TYPE_PATTERN.matcher(variable);
        Matcher booleanMatcher = BOOLEAN_TYPE_PATTERN.matcher(variable);

        // Check if variable matches to the given wanted type:
        switch (wantedType) {
            case INT:
                return intMatcher.matches();
            case DOUBLE:
                return doubleMatcher.matches();
            case STRING:
                return stringMatcher.matches();
            case CHAR:
                return charMatcher.matches();
            case BOOLEAN:
                return booleanMatcher.matches();
        }
        return false;
    }
}
