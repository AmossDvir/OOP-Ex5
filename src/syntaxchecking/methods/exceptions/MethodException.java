package syntaxchecking.methods.exceptions;

public abstract class MethodException extends Exception {

    public MethodException(){
        super();
    }
    public MethodException(String message){
        super(message);
    }
}
