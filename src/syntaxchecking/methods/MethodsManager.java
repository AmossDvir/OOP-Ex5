package syntaxchecking.methods;

import utilities.MethodsPair;

import java.util.*;

/**
 * a Singleton class for managing Methods calling and declarations.
 */
public class MethodsManager {
    private static MethodsManager single = new MethodsManager();
    private Map<String, List<String>> registry;

    private MethodsManager() {
        registry = new HashMap<>();

    }

    public static MethodsManager getManager() {
        return single;
    }

    /**
     * Adds a single method to the registry.
     *
     * @param pair: a MethodPair.
     */
    public void addMethod(MethodsPair pair) {
        this.registry.put(pair.getFirst(), pair.getSecond());
    }

}
