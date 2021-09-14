package oop.ex5.main;

import fileshandling.FileReader;
import syntaxchecking.CodeManager;
import syntaxchecking.methods.exceptions.MethodException;
import syntaxchecking.variables.VariableException;

import java.io.IOException;
import java.util.List;

import static fileshandling.TextParser.removeUnnecessaryLines;

public class Sjavac {
    // Constants:
    private static final int VALID_CODE = 0;
    private static final int INVALID_CODE = 1;
    private static final int READING_ERROR = 2;
    private static final String READING_ERROR_MESSAGE = "There Was a Problem With The File, Please Try Again";
    public static final int SINGLE_ARG = 1;


    /**
     * Main method of the program.
     *
     * @param args: String array containing arguments.
     */
    public static void main(String[] args) {
        try {
            // Read and cut unnecessary lines:
            if(args.length != SINGLE_ARG){
                throw new IOException();
            }
            List<String> lines = FileReader.readFile(args[0]);
            removeUnnecessaryLines(lines);
            CodeManager cm = new CodeManager(lines);
            // Check it:
            cm.checkCode();
            System.out.println(VALID_CODE);
            // File reading error:
        } catch (IOException e) {
            System.out.println(READING_ERROR);
            System.err.println(READING_ERROR_MESSAGE);
        }
        // Invalid text:
        catch (VariableException | MethodException e) {
            System.out.println(INVALID_CODE);
            System.err.println(e.getMessage());
        }
    }
}