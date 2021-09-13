package syntaxchecking.methods.exceptions;

/**
 * an Exception class for methods invoking Exceptions.
 */
public class InvokingException extends MethodException {
    // Constants:
    private static final long SerialVersionUID = 1L;
    private static final String DEFAULT_MESSAGE = "Invalid Method calling Line.";

    // Constructors:
    /**
     * a Default constructor, construct a new Exception with a default message.
     */
    public InvokingException(){
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new Exception with a custom message.
     * @param message: String, message to show.
     */
    public InvokingException(String message) {
        super(message);
    }
}

