package syntaxchecking.methods.exceptions;

public abstract class MethodException extends Exception {
    // Constants:
    private static final long SerialVersionUID = 1L;

    // Constructors:

    /**
     * a Default constructor, construct a new Exception with a default message.
     */
    public MethodException(){
        super();
    }

    /**
     * Constructs a new Exception with a custom message.
     * @param message: String, message to show.
     */
    public MethodException(String message){
        super(message);
    }
}
