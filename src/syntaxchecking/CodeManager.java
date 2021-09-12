package syntaxchecking;

import syntaxchecking.blocks.exceptions.BlockException;
import syntaxchecking.methods.declarations.VoidDeclaration;
import syntaxchecking.methods.exceptions.DeclarationException;
import syntaxchecking.variables.Variable;
import syntaxchecking.variables.VariableException;
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
        Matcher genericMatcher;
        int lineIndex = 0;
        // Iterate through all the code lines:
        for (String line : lines) {
            genericMatcher = VOID_METHOD_DEC_PATTERN.matcher(line);
            // Try to verify the general struct of a method declaration:
            if (genericMatcher.matches()) {
                Map<String, Variable> paramsMap = new HashMap<>();
                MethodsPair mp = VoidDeclaration.analyzeDeclaration(line,paramsMap);
//                if (!methodsDeclarations.containsKey(mp.getFirst())) {
                    methodsLines.put(mp.getFirst(),divideIntoMethods(lineIndex));
//                    methodsLines.put(mp.getFirst(),new Pair(lineIndex,8) )
//                    methodsDeclarations.put(mp.getFirst(), mp.getSecond());
                }
            }
            lineIndex++;

    }

    public void registerGlobalVars() throws VariableException {
        for (String line : lines) {

        }
    }
    public Pair<Integer,Integer> divideIntoMethods(int startingLine) throws BlockException {
        int parenthesesCount = PARENTHESES_DEFAULT;
        Matcher closing,ifWhile;
        // Iterate from the current line:
        int lineIndex;
        for (lineIndex = startingLine+1; lineIndex < lines.size(); lineIndex++) {

                String currentLine = lines.get(lineIndex);
            ifWhile = IF_WHILE_BLOCK_PATTERN.matcher(currentLine);
            if(ifWhile.matches()){
                parenthesesCount++;
            }
            closing = CLOSING_BRACKETS_PATTERN.matcher(currentLine);
            if(closing.matches()){
                parenthesesCount--;
            }
            if(parenthesesCount == 0){
                return new Pair(startingLine,lineIndex);
            }
        }
        throw new BlockException();
    }

    public static void main(String[] args) throws BlockException {
        List<String> code = new ArrayList<>();
        code.add("void foo(){");
        code.add("if(trh){");
        code.add("}");
        code.add("return;");
//        code.add("}");

        CodeManager cm = new CodeManager(code);

        System.out.println(cm.divideIntoMethods(0).getSecond());
    }
}
