package syntaxchecking.methods;

import syntaxchecking.methods.declarations.VoidDeclaration;
import syntaxchecking.methods.exceptions.MethodException;
import syntaxchecking.variables.Variable;
import utilities.MethodsPair;
import utilities.Pair;

import java.util.*;
import java.util.regex.Matcher;

import static utilities.RegexExpressions.*;

/**
 *
 */
public class Method {
    public static final int F = 1;
    public static final int FIRST_LINE = 1;
    private List<String> methodLines;
    private Map<String, Variable> variables;
    private Map<String, List<String>> methodsList;
    private Set<Integer> checkedLines;
    private Map<Integer,Integer> blocksTree;

    public Method(List<String> methodLines, Map<String, List<String>> methodsList) {
        this.methodLines = methodLines;
        this.variables = new HashMap<>();
        this.methodsList = methodsList;
        checkedLines = new HashSet<>();
        blocksTree = new HashMap<>();
    }

    /**
     * @return
     * @throws MethodException
     */
    public void analyze() throws MethodException {
        MethodsPair m = VoidDeclaration.analyzeDeclaration(methodLines.get(0), variables);
        List<Pair<Integer, Integer>> blockLines = registerBlocks();
        List<List<String>> blocksLst = cutAndArrangeBlock(blockLines);

        for(List<String> lst:blocksLst){
            for(String line:lst){
                System.out.println(line);

            }
            System.out.println("-----------");
        }



//        for()
//         TODO: CONTINUE

    }

    private List<List<String>> cutAndArrangeBlock(List<Pair<Integer,Integer>> blocks){
        List<List<String>> retLst = new ArrayList<>();
        for(Pair<Integer,Integer> p:blocks){
            List<String> tmpBlockLines = new ArrayList<>();
            for(int i = p.getFirst();i < p.getSecond() + 1; i++)
                if(!checkedLines.contains(i)){
                    tmpBlockLines.add(methodLines.get(i));
                    checkedLines.add(i);
                }
            retLst.add(tmpBlockLines);
        }
        List<String> mainBlock = new ArrayList<>();
        for(int i = FIRST_LINE; i < methodLines.size(); i++){
            if(!checkedLines.contains(i)){
                mainBlock.add(methodLines.get(i));
            }
        }
        retLst.add(mainBlock);
        Collections.reverse(retLst);
        return retLst;
    }

    private List<Pair<Integer,Integer>> registerBlocks() {

        int lineIndex = 0;
        Matcher ifWhileBlockMatcher;
        List<Pair<Integer, Integer>> blockLines = new ArrayList<>();

        for (; lineIndex < methodLines.size(); lineIndex++) {
            // Check if a new block starts:
            ifWhileBlockMatcher = IF_WHILE_BLOCK_PATTERN.matcher(methodLines.get(lineIndex));
            if (ifWhileBlockMatcher.matches()) {
                List<Pair<Integer, Integer>> tmp = new ArrayList<>();
                blockLines = divideIntoBlocks(lineIndex, tmp);
                break;
            }
        }
        return blockLines;
    }

    private void buildHierarchyTree(){

    }

    private List<Pair<Integer, Integer>> divideIntoBlocks(int startingLine,
                                                          List<Pair<Integer, Integer>> pairsLst) {
        Matcher closingBlockMatcher, ifWhileMatcher;
        int line = startingLine+1;
        for (; line < methodLines.size(); line++) {
            closingBlockMatcher = CLOSING_BRACKETS_PATTERN.matcher(methodLines.get(line));
            if (closingBlockMatcher.matches()) {
                pairsLst.add(new Pair<Integer, Integer>(startingLine, line));
                return pairsLst;
            }
            ifWhileMatcher = IF_WHILE_BLOCK_PATTERN.matcher(methodLines.get(line));
            if (ifWhileMatcher.matches()) {
                pairsLst = divideIntoBlocks(line, pairsLst);
                line += pairsLst.get(pairsLst.size()-1).getSecond()-pairsLst.get(pairsLst.size()-1).getFirst();
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


    public static void main(String[] args) throws MethodException {
        List<String> m = new ArrayList<>();
        Map<String, List<String>> methodsList=new HashMap<>();
        // Test these:
        //0:
        m.add("void foo(){");
        m.add("int a = 5;");
        //1:
        m.add("if(1){");
        m.add("int b = a;");
        m.add("1line");
        //2:
        m.add("if(2){");
        m.add("int c = m;");
        //3:
        m.add("if(3){");
        m.add("3line");
        m.add("}");
        m.add("2line");
        m.add("}");
        m.add("}");
        m.add("int m = 5;");

        m.add("return;");
        m.add("}");
        Method meth = new Method(m,methodsList);
        meth.analyze();
    }
}
