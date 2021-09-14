package syntaxchecking.methods;

import syntaxchecking.blocks.Block;
import syntaxchecking.blocks.exceptions.BlockException;
import syntaxchecking.methods.declarations.VoidDeclaration;
import syntaxchecking.methods.exceptions.MethodException;
import syntaxchecking.variables.Variable;
import syntaxchecking.variables.VariableException;
import utilities.MethodsPair;
import utilities.Pair;

import java.util.*;
import java.util.regex.Matcher;

import static utilities.RegexExpressions.*;

/**
 *
 */
public class Method {
    public static final int FIRST_LINE = 1;
    private List<String> methodLines;
    private Map<String, Variable> variables;
    private Map<String, Variable> defaultVar;
    private Map<String, List<String>> methodsList;
    private Set<Integer> checkedLines;


    public Method(List<String> methodLines, Map<String, List<String>> methodsList,
                  Map<String, Variable> globalVars) {
        this.methodLines = methodLines;
        this.defaultVar = new HashMap<>();
        this.methodsList = methodsList;
        checkedLines = new HashSet<>();
        defaultVar.putAll(globalVars);
    }

    /**
     * @return
     * @throws MethodException
     */
    public void analyze() throws MethodException, VariableException {
        VoidDeclaration.analyzeDeclaration(methodLines.get(0), defaultVar);
        List<Pair<Integer, Integer>> blockLines = registerBlocks();
        List<List<Pair<String, Integer>>> blocksLst = cutAndArrangeBlock(blockLines);
        Collections.reverse(blockLines);
        // Check block by block
        Block mainBlock = new Block(blocksLst.get(0), methodsList, defaultVar);
        mainBlock.checkBlock();
        for (int i = 1; i < blocksLst.size(); i++) {
            if (blockLines.get(i).getFirst() > blockLines.get(i - 1).getSecond()) {
                    variables = defaultVar;
            }else{
                variables.putAll(defaultVar);
                Block block = new Block(blocksLst.get(i), methodsList, variables);
                block.checkBlock();
        }
    }
    methodEnding(blocksLst.get(0));


}

    private void methodEnding(List<Pair<String, Integer>> mainBlock) throws BlockException {
        // Check the main block(the method structure)
        if (mainBlock.size() < 2) {
            throw new BlockException();
        }
        Matcher matcher = RETURN_BLOCK_PATTERN.matcher(mainBlock.get(mainBlock.size() - 2).getFirst());
        if (!matcher.matches()) {
            throw new BlockException();

        }
        matcher = CLOSING_BRACKETS_PATTERN.matcher(mainBlock.get(mainBlock.size() - 1).getFirst());
        if (!matcher.matches()) {
            throw new BlockException();

        }

    }

    private List<List<Pair<String, Integer>>> cutAndArrangeBlock(List<Pair<Integer, Integer>> blocks) {
        List<List<Pair<String, Integer>>> retLst = new ArrayList<>();
        for (Pair<Integer, Integer> p : blocks) {
            List<Pair<String, Integer>> tmpBlockLines = new ArrayList<>();
            for (int i = p.getFirst(); i < p.getSecond() + 1; i++)
                if (!checkedLines.contains(i)) {
                    tmpBlockLines.add(new Pair<String, Integer>(methodLines.get(i), i));
                    checkedLines.add(i);
                }
            retLst.add(tmpBlockLines);
        }
        List<Pair<String, Integer>> mainBlock = new ArrayList<>();
        for (int i = FIRST_LINE; i < methodLines.size(); i++) {
            if (!checkedLines.contains(i)) {
                mainBlock.add(new Pair<String, Integer>(methodLines.get(i), i));
            }
        }
        retLst.add(mainBlock);
        Collections.reverse(retLst);
        return retLst;
    }

    private List<Pair<Integer, Integer>> registerBlocks() {
        int lineIndex = 0;
        Matcher ifWhileBlockMatcher;
        List<Pair<Integer, Integer>> blockLines = new ArrayList<>();

        for (; lineIndex < methodLines.size(); lineIndex++) {
            // Check if a new block starts:
            ifWhileBlockMatcher = IF_WHILE_BLOCK_PATTERN.matcher(methodLines.get(lineIndex));
            if (ifWhileBlockMatcher.matches()) {
                List<Pair<Integer, Integer>> tmp = new ArrayList<>();
                blockLines.addAll(divideIntoBlocks(lineIndex, tmp));
                lineIndex = getLastLineBlock(blockLines);
            }
        }
        return blockLines;
    }

    private int getLastLineBlock(List<Pair<Integer, Integer>> blocks) {
        int maxLine = 0;
        for (Pair<Integer, Integer> p : blocks) {
            if (p.getSecond() > maxLine) {
                maxLine = p.getSecond();
            }
        }
        return maxLine;
    }

    private List<Pair<Integer, Integer>> divideIntoBlocks(int startingLine,
                                                          List<Pair<Integer, Integer>> pairsLst) {
        Matcher closingBlockMatcher, ifWhileMatcher;
        int line = startingLine + 1;
        for (; line < methodLines.size(); line++) {
            closingBlockMatcher = CLOSING_BRACKETS_PATTERN.matcher(methodLines.get(line));
            if (closingBlockMatcher.matches()) {
                pairsLst.add(new Pair<Integer, Integer>(startingLine, line));
                return pairsLst;
            }
            ifWhileMatcher = IF_WHILE_BLOCK_PATTERN.matcher(methodLines.get(line));
            if (ifWhileMatcher.matches()) {
                pairsLst = divideIntoBlocks(line, pairsLst);
                line += pairsLst.get(pairsLst.size() - 1).getSecond() -
                        pairsLst.get(pairsLst.size() - 1).getFirst();
            }
        }
        return pairsLst;
    }

    /**
     * @param methodLine
     * @return
     */
    public static String extractMethodName(String methodLine) {

        return methodLine.substring(methodLine.indexOf(' ') + 1, methodLine.indexOf("("));
    }

}
