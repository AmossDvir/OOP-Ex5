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
import utilities.Pair;

import java.util.*;

/**
 * a class represents a single block in a code.
 */
public class Block {

    private List<Pair<String,Integer>> blockLines;
    private Map<String, Variable> variables;
    private Map<String, List<String>> methodsList;

    public Block(List<Pair<String,Integer>>  blockLines, Map<String, List<String>> methodsList) {
        this.blockLines =  blockLines;
        this.variables = new HashMap<>();
        this.methodsList = methodsList;
    }


   public void checkBlock() throws MethodException, VariableException {
        for (Pair<String,Integer> line : blockLines){
            classifyLine(line.getFirst(),line.getSecond());

        }
    }

    /**
     * Classifies a given line and invokes the corresponding method.
     */
    private void classifyLine(String line,int lineIndex) throws MethodException, VariableException {
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
            varMan.analyzeVariableLine(line,lineIndex,false);
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