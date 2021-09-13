package syntaxchecking.blocks;

import syntaxchecking.blocks.exceptions.BlockException;
import utilities.Pair;

import java.util.*;

/**
 * a class represents a single block in a code.
 */
public class IfWhileBlock {
    private List<String> codeLines = new ArrayList<>();
    private Set<Integer> checkedLines;

    public IfWhileBlock(){
        checkedLines = new HashSet<>();
    }

    public Pair<Integer, Integer> divideIntoBlocks(int startingLine) throws BlockException {
        int line = startingLine;
        for(;line < ;){

        }
    }

    /**
     * Returns the smallest
     * @return
     */
    private Pair<Integer,Integer> findMostInnerBlock(List<Pair<Integer,Integer>> blocksLines){
        int difference = blocksLines.get(0).getSecond()-blocksLines.get(0).getFirst();
        Pair<Integer,Integer> retValue= new Pair<>(blocksLines.get(0).getFirst(),blocksLines.get(0).getSecond());
        for(Pair<Integer,Integer> p:blocksLines){
            if(p.getSecond()-p.getFirst() < difference){
                difference = p.getSecond()-p.getFirst();
                retValue = new Pair<>(p.getFirst(),p.getSecond());
            }
        }
        return retValue;
    }

        private void checkParentheses() throws BlockException {

    }
}