package syntaxchecking.blocks;

import syntaxchecking.blocks.exceptions.BlockException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * a class represents a single block in a code.
 */
public class Block {
    private List<String> codeLines = new ArrayList<>();
    private Stack<String> parentheses = new Stack<>();

    public Block(){
        this.parentheses.push("{");
        this.parentheses.pop();
    }

    private void checkParentheses() throws BlockException {

    }
}