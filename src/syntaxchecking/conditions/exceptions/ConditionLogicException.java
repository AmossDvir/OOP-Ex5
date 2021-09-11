package syntaxchecking.conditions.exceptions;

import syntaxchecking.methods.exceptions.MethodException;

/**
 * a class for handling conditions Exceptions.
 */
public class ConditionLogicException extends MethodException {
    // Constants:
    private static final String DEFAULT_MESSAGE = "Logic Exception Encountered";

    // Constructors:

    /**
     * a Default constructor, sends a default message.
     */
    public ConditionLogicException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * a custom message constructor.
     *
     * @param message: a custom message.
     */
    public ConditionLogicException(String message) {
        super(message);
    }
}