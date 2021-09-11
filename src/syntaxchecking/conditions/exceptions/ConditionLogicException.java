package syntaxchecking.conditions.exceptions;

import syntaxchecking.methods.exceptions.MethodException;

/**
 * a class for handling conditions exceptions.
 */
public class ConditionLogicException extends MethodException {
    private static final String DEFAULT_MESSAGE = "Logic Exception Encountered";

    public ConditionLogicException() {
        super(DEFAULT_MESSAGE);
    }

    public ConditionLogicException(String message) {
        super(message);
    }
}