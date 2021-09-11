package syntaxchecking.methods;

import syntaxchecking.conditions.Condition;
import syntaxchecking.methods.declarations.VoidDeclaration;
import syntaxchecking.methods.exceptions.MethodException;
import utilities.MethodsPair;
import utilities.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static utilities.RegexExpressions.*;


/**
 *
 */
public class Method {
    private List<String> methodLines;
    private Map<String, Pair<String, Boolean>> variables;

    public Method(List<String> methodLines) {
        this.methodLines = methodLines;
        this.variables = new HashMap<>();
    }

    /**
     *
     * @return
     * @throws MethodException
     */
    public MethodsPair analyze() throws MethodException {
        MethodsPair m = VoidDeclaration.analyzeDeclaration(methodLines.get(0), variables);
        classifyLine(methodLines.get(1));
        return m;
    }

    /**
     * Classifies a given line and invokes the corresponding method.
     */
    private void classifyLine(String line) throws MethodException {
        Matcher lineMatcher = IF_WHILE_BLOCK_PATTERN.matcher(line);
        if(lineMatcher.matches()){
            Condition condition = new Condition(line,variables);
            condition.checkCondition();
        }
        lineMatcher = METHOD_CALLING_BLOCK_PATTERN.matcher(line);
        if(lineMatcher.matches()){
            }
        lineMatcher = RETURN_BLOCK_PATTERN.matcher(line);
        if(lineMatcher.matches()){
        }
    }

    /**
     * @param methodLine
     * @return
     */
    public static String extractMethodName(String methodLine) {
        return methodLine.substring(methodLine.indexOf(' ') + 1, methodLine.indexOf("("));
    }

    //    private void
    public static void main(String[] args) throws MethodException {
        List<String> aa = new ArrayList<>();
        aa.add("void foo(int a, final String bibi){");
        aa.add("final     (  rthsryhy   )   ;    ");

        Method m = new Method(aa);
        m.analyze();
//        for (String i : m.variables.keySet()){
//            System.out.println(i);
//            System.out.println(m.variables.get(i).getFirst() + m.variables.get(i).getSecond());
//        }
    }
}
