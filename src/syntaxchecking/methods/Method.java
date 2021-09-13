package syntaxchecking.methods;

import syntaxchecking.methods.declarations.VoidDeclaration;
import syntaxchecking.methods.exceptions.MethodException;
import syntaxchecking.variables.Variable;
import utilities.MethodsPair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Method {
    private List<String> methodLines;
    private Map<String, Variable> variables;
    private Map<String, List<String>> methodsList;

    public Method(List<String> methodLines, Map<String, List<String>> methodsList) {
        this.methodLines = methodLines;
        this.variables = new HashMap<>();
        this.methodsList = methodsList;
    }

    /**
     * @return
     * @throws MethodException
     */
    public void analyze() throws MethodException {
        MethodsPair m = VoidDeclaration.analyzeDeclaration(methodLines.get(0), variables);
        // TODO: CONTINUE


    }

    /**
     * @param methodLine
     * @return
     */
    public static String extractMethodName(String methodLine) {
        return methodLine.substring(methodLine.indexOf(' ') + 1, methodLine.indexOf("("));
    }
}
