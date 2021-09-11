package syntaxchecking.conditions;

import syntaxchecking.conditions.exceptions.ConditionLogicException;

import static syntaxchecking.VariablesTypes.*;
import static syntaxchecking.variables.TypeIdentifier.*;

import utilities.Pair;

import java.util.*;

import static utilities.RegexExpressions.*;
import static utilities.StringManipulations.*;

/**
 * Represents a condition and checks its logic.
 */
public class Condition {
    // Constants:
    // Define a set of legal variables and initialize it:
    private static final Set<String> LEGAL_TYPES = new HashSet<>();

    static {
        LEGAL_TYPES.add(BOOLEAN);
        LEGAL_TYPES.add(DOUBLE);
        LEGAL_TYPES.add(INT);
    }

    // Members:
    private String condition;
    private Map<String, Pair<String, Boolean>> variables;

    /**
     * Constructs a condition and stores a list of an already-known variables.
     *
     * @param condition: a String represents a condition.
     * @param variables: list of variables known to some method.
     */
    public Condition(String condition, Map<String, Pair<String, Boolean>> variables) {
        // Take out only the condition inside the parentheses.
        this.condition = extractFromParantheses(condition);
        this.variables = variables;
    }

    /**
     * Checks whether the given condition is valid.
     *
     * @throws ConditionLogicException: In case that one of the sub conditions is invalid.
     */
    public void checkCondition() throws ConditionLogicException {
        // Split sub-conditions and store them into a list:
        List<String> conditionsList = Arrays.asList(condition.split(SPLIT_SUB_CONDITIONS));
        // Check sum of operators dividing the sub conditions:
        if (conditionsList.size() !=
                (countOccurrences(condition, OR_OPERATOR)) + (countOccurrences(condition, AND_OPERATOR)) +
                        1) {
            throw new ConditionLogicException();
        }
        // Check validity of all the sub conditions:
        for (String subCondition : conditionsList) {
            // Take out redundant white spaces:
            subCondition = subCondition.trim();
            checkSubCondition(subCondition);
        }
    }

    /**
     * Checks whether one sub condition is valid or not.
     *
     * @param subCondition: a String, sub condition.
     * @throws ConditionLogicException: In case that the sub conditions is invalid.
     */
    private void checkSubCondition(String subCondition) throws ConditionLogicException {
        // Check if subCondition is a boolean form:
        if (isInstanceOf(BOOLEAN, subCondition)) {
            return;
        }
        // Check if subCondition is one of the method's variables:
        if (variables.containsKey(subCondition)) {
            if (LEGAL_TYPES.contains(variables.get(subCondition).getFirst())) {
                return;
            }
        }
        throw new ConditionLogicException();
    }
}
