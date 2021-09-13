package syntaxchecking.methods.exceptions;

/**
 * an Exception class for methods declarations Exceptions.
 */
public class DeclarationException extends MethodException {
    private static final long SerialVersionUID = 1L;
    private static final String DEFAULT_MESSAGE = "Invalid Method Declaration Line.";

    // Constructors:
    /**
     * a Default constructor, construct a new Exception with a default message.
     */
    public DeclarationException(){
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new Exception with a custom message.
     * @param message: String, message to show.
     */
    public DeclarationException(String message) {
        super(message);
    }
}
