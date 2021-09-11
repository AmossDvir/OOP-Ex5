package syntaxchecking.methods.exceptions;

public class DeclartionException extends MethodException {
    private static final long SerialVersionUID = 1L;
    private static final String DEFAULT_MESSAGE = "Invalid Method Declaration Line.";

    /**
     * a Default constructor, construct a new Exception with a default message.
     */
    public DeclartionException(){
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a new Exception with a custom message.
     * @param message: String, message to show.
     */
    public DeclartionException(String message) {
        super(message);
    }
}
