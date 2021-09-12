package syntaxchecking.blocks.exceptions;

import syntaxchecking.methods.exceptions.MethodException;

/**
 * a class for handling block Exceptions.
 */
public class BlockException extends MethodException {
    // Constants:
    private static final long SerialVersionUID = 1L;
    private static final String DEFAULT_MESSAGE = "Invalid Block of Code";

    // Constructors:

    /**
     * a Default constructor, sends a default message.
     */
    public BlockException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * a custom message constructor.
     *
     * @param message: a custom message.
     */
    public BlockException(String message) {
        super(message);
    }
}

