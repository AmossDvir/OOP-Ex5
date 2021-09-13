package syntaxchecking.blocks;

import syntaxchecking.blocks.exceptions.BlockException;
import syntaxchecking.conditions.Condition;
import syntaxchecking.methods.exceptions.MethodException;
import syntaxchecking.methods.invoking.MethodCall;
import syntaxchecking.variables.Variable;
import syntaxchecking.variables.VariableException;
import syntaxchecking.variables.VariablesManager;

import java.util.*;
import java.util.regex.Matcher;

import static utilities.RegexExpressions.*;
import static utilities.RegexExpressions.RETURN_BLOCK_PATTERN;

/**
 * a class represents a single block in a code.
 */
public class IfWhileBlock {

    private List<String> blockLines;
    private Map<String, Variable> variables;
    private Map<String, List<String>> methodsList;

    public IfWhileBlock(List<String> methodLines, Map<String, List<String>> methodsList) {
        this.blockLines = methodLines;
        this.variables = new HashMap<>();
        this.methodsList = methodsList;
    }


    private void checkBlock() throws MethodException, VariableException {
        for (String line : blockLines){
            classifyLine(line);

        }
    }

    /**
     * Classifies a given line and invokes the corresponding method.
     */
    private void classifyLine(String line) throws MethodException, VariableException {
        Matcher lineMatcher = IF_WHILE_BLOCK_PATTERN.matcher(line);
        Matcher closeMatcher = CLOSING_BRACKETS_PATTERN.matcher(line);
        if (lineMatcher.matches()) {
            Condition condition = new Condition(line, variables);
            condition.checkCondition();

        }
        lineMatcher = METHOD_CALLING_BLOCK_PATTERN.matcher(line);
        if (lineMatcher.matches()) {
            MethodCall call = new MethodCall(line, methodsList);
            call.checkMethodCall();
        }
        if (isVar(line)){
            VariablesManager varMan = new VariablesManager(variables);
            varMan.analyzeVariableLine(line);
        }
        lineMatcher = RETURN_BLOCK_PATTERN.matcher(line);
        if (!lineMatcher.matches()||closeMatcher.matches()) {
            throw new BlockException();

        }

    }
    /**
     * @param line
     * @return
     */
    //TODO: UPDATE DECLARATIONS AND ADDING ASSIGHMENT PATTERN
    private boolean isVar(String line) {
        Matcher intMatcher, doubleMatcher, stringMatcher, charMatcher, booleanMatcher;
        intMatcher = INT_DEC_PATTERN.matcher(line);
        doubleMatcher = DOUBLE_DEC_PATTERN.matcher(line);
        stringMatcher = STRING_DEC_PATTERN.matcher(line);
        charMatcher = CHAR_DEC_PATTERN.matcher(line);
        booleanMatcher = BOOLEAN_DEC_PATTERN.matcher(line);


        if (intMatcher.matches() || doubleMatcher.matches() || stringMatcher.matches() ||
                charMatcher.matches() || booleanMatcher.matches()) {
            return true;
        }
        return false;
    }
}