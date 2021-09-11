package syntaxchecking.variables;



public class VariableException extends Exception {
    private static final long SerialVersionUID = 1L;
    private static final String DEFAULT_MESSAGE = "Invalid variable.";

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
