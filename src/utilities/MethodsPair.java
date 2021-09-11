package utilities;

import java.util.List;

/**
 * a sub-class represents method pairs: method name and its parameters' types.
 */
public class MethodsPair extends Pair<String, List<String>> {
    /**
     * Gets a method name and list of types of parameters and constructs a pair.
     * @param methodName: a String, the method name.
     * @param typesList: list of types of parameters.
     */
    public MethodsPair(String methodName,List<String> typesList){
        super(methodName,typesList);
    }
}
