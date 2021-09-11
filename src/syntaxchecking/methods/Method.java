package syntaxchecking.methods;

import syntaxchecking.methods.declarations.VoidDeclaration;
import syntaxchecking.methods.exceptions.MethodException;
import utilities.MethodsPair;
import utilities.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static syntaxchecking.methods.Expressions.*;


public class Method {
    private List<String> methodLines;
    private Map<String, Pair<String, Boolean>> varaibles;

    public Method(List<String> methodLines) {
        this.methodLines = methodLines;
        this.varaibles = new HashMap<>();
    }

    /**
     *
     * @return
     * @throws MethodException
     */
    public MethodsPair analyze() throws MethodException {
        MethodsPair m = VoidDeclaration.analyzeDeclaration(methodLines.get(0), varaibles);
        classifyLine(methodLines.get(1));
        return m;
    }

    /**
     * Classifies a given line and invokes the corresponding method.
     */
    private void classifyLine(String line){
        Matcher lineMatcher = IF_WHILE_BLOCK_PATTERN.matcher(line);
        if(lineMatcher.matches()){
            System.out.println("If or While block!!");
        }
        lineMatcher = METHOD_CALLING_BLOCK_PATTERN.matcher(line);
        if(lineMatcher.matches()){
                System.out.println("Method!");
            }
        lineMatcher = RETURN_BLOCK_PATTERN.matcher(line);
        if(lineMatcher.matches()){
            System.out.println("Return!");
        }
    }

    private void checkCondition(String condition){

    }

    //    private void
    public static void main(String[] args) throws MethodException {
        List<String> aa = new ArrayList<>();
        aa.add("void foo(int a, final String bibi){");
        aa.add("foo     (  rthsryhy   )   ;    ");

        Method m = new Method(aa);
        m.analyze();
//        for (String i : m.variables.keySet()){
//            System.out.println(i);
//            System.out.println(m.variables.get(i).getFirst() + m.variables.get(i).getSecond());
//        }
    }
}
