package oop.ex5.main;

import fileshandling.FileReader;
import syntaxchecking.CodeManager;
import syntaxchecking.blocks.exceptions.BlockException;
import syntaxchecking.methods.exceptions.DeclarationException;
import syntaxchecking.methods.exceptions.MethodException;
import syntaxchecking.variables.VariableException;

import java.io.IOException;
import java.util.List;

public class Sjavac {
    /**
     * Main method of the program.
     *
     * @param args: String array containing arguments.
     */
    public static void main(String[] args) {
        try {
            List<String> lines = FileReader.readFile(args[0]);
            CodeManager cm = new CodeManager(lines);
            cm.checkCode();
//            int result = cm.checkCode();
//            System.out.println(result);
        } catch (IOException e) {
            // some Error.
        } catch (VariableException e) {

        } catch (BlockException e) {

        } catch (DeclarationException e) {

        } catch (MethodException e) {

        }
    }
}