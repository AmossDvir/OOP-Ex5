package syntaxchecking.methods.exceptions;

public abstract class MethodException extends Exception {
    // Constants:
    private static final long SerialVersionUID = 1L;

    // Constructors:
    public MethodException(){
        super();
    }

    public MethodException(String message){
        super(message);
    }
}
