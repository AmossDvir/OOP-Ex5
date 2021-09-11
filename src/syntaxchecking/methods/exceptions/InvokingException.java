package syntaxchecking.methods.exceptions;

public class InvokingException extends MethodException {
    private static final long SerialVersionUID = 1L;
    private static final String DEFAULT_MESSAGE = "Invalid Method calling Line.";

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

