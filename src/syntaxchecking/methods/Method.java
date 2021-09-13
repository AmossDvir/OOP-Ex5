package syntaxchecking.methods;

import syntaxchecking.conditions.Condition;
import syntaxchecking.methods.declarations.VoidDeclaration;
import syntaxchecking.methods.exceptions.MethodException;
import syntaxchecking.variables.Variable;
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
        for (String line : methodLines) {
            classifyLine(line);
        }
    }

    /**
     * Classifies a given line and invokes the corresponding method.
     */
    private void classifyLine(String line) throws MethodException {
        Matcher lineMatcher = IF_WHILE_BLOCK_PATTERN.matcher(line);
        if (lineMatcher.matches()) {
            Condition condition = new Condition(line, variables);
            condition.checkCondition();

        }
        lineMatcher = METHOD_CALLING_BLOCK_PATTERN.matcher(line);
        if (lineMatcher.matches()) {
        }
        lineMatcher = RETURN_BLOCK_PATTERN.matcher(line);
        if (lineMatcher.matches()) {
        }
    }


    private void registerBlocks() {
        Matcher closingBlockMatcher;
        while()
        if()
        int lineIndex = 0;
        Matcher ifWhileBlockMatcher;
        Pair<Integer, Integer> blockLines;

        for (; lineIndex < methodLines.size(); lineIndex++) {
            // Check if a new block starts:
            ifWhileBlockMatcher = IF_WHILE_BLOCK_PATTERN.matcher(methodLines.get(lineIndex));
            if (ifWhileBlockMatcher.matches()) {
                blockLines = divideIntoBlocks();
            }

        }
    }

    private Pair<Integer, Integer> divideIntoBlocks() {
    }

    /**
     * @param methodLine
     * @return
     */
    public static String extractMethodName(String methodLine) {
        return methodLine.substring(methodLine.indexOf(' ') + 1, methodLine.indexOf("("));
    }
}
