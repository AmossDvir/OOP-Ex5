package syntaxchecking.conditions;

import syntaxchecking.conditions.exceptions.ConditionLogicException;

import static syntaxchecking.VariablesTypes.*;
import static syntaxchecking.variables.TypeIdentifier.*;
import utilities.Pair;

import java.util.*;


import static syntaxchecking.ReservedWords.*;
import static utilities.RegexExpressions.*;
import static utilities.StringManipulations.*;

public class Condition {
    // Define a set of legal variables and initialize it:
    private static final Set<String> LEGAL_TYPES = new HashSet<>();

    static {
        LEGAL_TYPES.add(BOOLEAN);
        LEGAL_TYPES.add(DOUBLE);
        LEGAL_TYPES.add(INT);
    }

    private String condition;
    private Map<String, Pair<String, Boolean>> variables;

    public Condition(String condition, Map<String, Pair<String, Boolean>> variables) {
        // Take out only the condition inside the parentheses.
        this.condition = extractFromParantheses(condition);
        this.variables = variables;
    }

    public void checkCondition() throws ConditionLogicException {
        // Split sub-conditions and store them into a list:
        List<String> conditionsList = Arrays.stream(condition.split(SPLIT_SUB_CONDITIONS)).toList();
        // Check sum of operators dividing the sub conditions:
        if (conditionsList.size() !=
                (countOccurrences(condition, OR_OPERATOR)) + (countOccurrences(condition, AND_OPERATOR)) +
                        1) {
            throw new ConditionLogicException();
        }
        for (String subCondition : conditionsList) {
            subCondition = subCondition.trim();
            checkSubCondition(subCondition);
        }
    }

    private void checkSubCondition(String subCondition) throws ConditionLogicException {
        // Check if subCondition is a boolean form:
        if(isInstanceOf(BOOLEAN,subCondition)){
            return;
        }
        // Check if subCondition is one of the method's variables:
        if (variables.containsKey(subCondition)) {
            if (LEGAL_TYPES.contains(variables.get(subCondition).getFirst())){
                return;
            }
        }
        throw new ConditionLogicException();
    }

    // TODO: DELETE!!!
    public static void main(String[] args) {
        Condition condition = new Condition("if(rfeg && rgr){", new HashMap<String, Pair<String, Boolean>>());
        try {
            condition.checkCondition();
        } catch (ConditionLogicException e) {
            System.out.println(e.getMessage());
        }
    }
}
