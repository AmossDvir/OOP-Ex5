package syntaxchecking.variables;

import java.util.regex.Matcher;

import static syntaxchecking.ReservedWords.*;
import static syntaxchecking.VariablesTypes.*;
import static utilities.RegexExpressions.*;

/**
 *
 */
public class TypeIdentifier {

    /**
     * @param wantedType
     * @param variable
     * @return
     */
    public static boolean isInstanceOf(String wantedType, String variable) {
        Matcher intMatcher = INT_TYPE_PATTERN.matcher(variable);
        Matcher doubleMatcher = DOUBLE_TYPE_PATTERN.matcher(variable);
        Matcher stringMatcher = STRING_TYPE_PATTERN.matcher(variable);
        Matcher charMatcher = CHAR_TYPE_PATTERN.matcher(variable);
        Matcher booleanMatcher = BOOLEAN_TYPE_PATTERN.matcher(variable);

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
