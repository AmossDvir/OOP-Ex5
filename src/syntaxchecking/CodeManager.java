package syntaxchecking;

import syntaxchecking.blocks.exceptions.BlockException;
import syntaxchecking.methods.Method;
import syntaxchecking.methods.declarations.VoidDeclaration;
import syntaxchecking.methods.exceptions.DeclarationException;
import syntaxchecking.methods.exceptions.MethodException;
import syntaxchecking.variables.Variable;
import syntaxchecking.variables.VariableException;
import syntaxchecking.variables.VariablesManager;
import utilities.MethodsPair;
import utilities.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static utilities.RegexExpressions.*;

public class CodeManager {
    public static final int PARENTHESES_DEFAULT = 1;
    public static final int END_OF_BLOCK = 0;
    private List<String> lines;
    private Map<String, Variable> globalVars;
    private Map<String, List<String>> methodsDeclarations;
    private Map<String, Pair<Integer, Integer>> methodsLines;

    public CodeManager(List<String> lines) {
        this.lines = lines;
        this.methodsDeclarations = new HashMap<>();
        this.globalVars = new HashMap<>();
        this.methodsLines = new HashMap<>();
    }

    public void registerMethods() throws BlockException, DeclarationException {
        Matcher declarationMatcher, globalVarMatcher;
        Pair<Integer, Integer> blockLines;
        int lineIndex = 0;
        // Iterate through all the code lines:
        for (; lineIndex < lines.size(); lineIndex++) {
            declarationMatcher = VOID_METHOD_DEC_PATTERN.matcher(lines.get(lineIndex));
            // Try to verify the general struct of a method declaration:
            if (declarationMatcher.matches()) {
                Map<String, Variable> paramsMap = new HashMap<>();
                MethodsPair mp = VoidDeclaration.analyzeDeclaration(lines.get(lineIndex), paramsMap);
                checkMethodsDuplication(mp);
                blockLines = divideIntoMethods(lineIndex);
                methodsLines.put(mp.getFirst(), blockLines);
                lineIndex += blockLines.getSecond();
                methodsDeclarations.put(mp.getFirst(), mp.getSecond());
            }
        }
    }


    /**
     * Check whether a given method name is already in use.
     *
     * @throws MethodException: in case it is already in use.
     */
    private void checkMethodsDuplication(MethodsPair mp) throws DeclarationException {
        String methodName = mp.getFirst();
        if (this.methodsDeclarations.containsKey(methodName)) {
            throw new DeclarationException();
        }
    }

    /**
     * @throws VariableException
     */
    public void registerGlobalVars() throws VariableException {
        int lineIndex = 0;
        // Iterate through the code lines:
        for (; lineIndex < lines.size(); lineIndex++) {
            // Check if a line is already registered as a method:
            for (Pair<Integer, Integer> p : methodsLines.values()) {
                if (lineIndex == p.getFirst()) {
                    lineIndex += p.getSecond() + 1;
                }
            }
            // Global variable declaration line:
            VariablesManager vm = new VariablesManager(globalVars);
            // Create new Variable:
            vm.analyzeVariableLine(lines.get(lineIndex));

        }
    }


//    /**
//     * @param line
//     * @return
//     */
//    private boolean isGlobalVar(String line) {
//        Matcher intMatcher, doubleMatcher, stringMatcher, charMatcher, booleanMatcher;
//        intMatcher = INT_DEC_PATTERN.matcher(line);
//        doubleMatcher = DOUBLE_DEC_PATTERN.matcher(line);
//        stringMatcher = STRING_DEC_PATTERN.matcher(line);
//        charMatcher = CHAR_DEC_PATTERN.matcher(line);
//        booleanMatcher = BOOLEAN_DEC_PATTERN.matcher(line);
//
//
//        if (intMatcher.matches() || doubleMatcher.matches() || stringMatcher.matches() ||
//                charMatcher.matches() || booleanMatcher.matches()) {
//            return true;
//        }
//        return false;
//    }

    private void checkMethods() throws MethodException {
        Method m;
        for (Pair<Integer, Integer> p : this.methodsLines.values()) {
            m = new Method(lines.subList(p.getFirst(), p.getSecond() + 1), methodsDeclarations);
            m.analyze();
        }
    }

    public Pair<Integer, Integer> divideIntoMethods(int startingLine) throws BlockException {
        int parenthesesCount = PARENTHESES_DEFAULT;
        Matcher closing, ifWhile;
        // Iterate from the current line:
        int lineIndex;
        for (lineIndex = startingLine + 1; lineIndex < lines.size(); lineIndex++) {
            String currentLine = lines.get(lineIndex);
            ifWhile = IF_WHILE_BLOCK_PATTERN.matcher(currentLine);
            if (ifWhile.matches()) {
                parenthesesCount++;
            }
            closing = CLOSING_BRACKETS_PATTERN.matcher(currentLine);
            if (closing.matches()) {
                parenthesesCount--;
            }
            if (parenthesesCount == END_OF_BLOCK) {
                return new Pair<Integer, Integer>(startingLine, lineIndex);
            }
        }
        throw new BlockException();
    }

    /**
     * The main method to check a whole code.
     *
     * @throws MethodException:   in case of invalid code inside a method.
     * @throws VariableException: in case of invalid variable declaration.
     */
    public void checkCode() throws MethodException, VariableException {
        this.registerMethods();
        this.registerGlobalVars();
        this.checkMethods();
    }

    public static void main(String[] args) throws BlockException, DeclarationException {
        List<String> code = new ArrayList<>();
        code.add("void foo(){");
        code.add("if(trh){");
        code.add("}");
        code.add("return;");
        code.add("}");
        code.add("void foo2(){");
        code.add("while(trytfdytfh){");
        code.add("}");
        code.add("return;");
        code.add("}");

        CodeManager cm = new CodeManager(code);
        cm.registerMethods();
    }
}


/*
int a =b;
int b =4;
 */