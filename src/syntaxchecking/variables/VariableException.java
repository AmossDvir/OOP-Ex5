package syntaxchecking.variables;

/**
 * an Exception class for variable's Exceptions.
 */
public class VariableException extends Exception {
    // Constants:
    private static final long SerialVersionUID = 1L;
    private static final String DEFAULT_MESSAGE = "Invalid Variable";

    // Constructors:
    /**
     * a Default constructor, construct a new Exception with a default message.
     */
    public VariableException(){
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new Exception with a custom message.
     * @param message: String, message to show.
     */
    public VariableException(String message) {
        super(message);
    }
}
