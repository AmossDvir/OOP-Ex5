package syntaxchecking.variables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static syntaxchecking.ReservedWords.*;
import static syntaxchecking.VariablesTypes.TYPES_LIST;
import static utilities.StringManipulations.splitToWords;

public class VariablesManager {
    private static final String EQUAL = "=";
    private Map<String,Variable> registry;
    public VariablesManager(String  variableLine){

        this.registry = new HashMap<>();
    }

    public void analyzeVariableLine(String line){
        List<String> words = splitToWords(line);


    }
    private  boolean isAssignment(String line){
        return  line.contains(EQUAL) ;
    }
    private  String[] splitNames(String line) {
        if (isAssignment(line)){

        }
    }
    private String splitByEqual(String line){

    }

    public void analyzeFirstWord(String word){
        if (TYPES_LIST.contains(word) || word.equals((FINAL))){
            Variable var = new Variable();
            if (word.equals((FINAL))){
                var.setFinal(true);
            }if (TYPES_LIST.contains(word)){
                var.setType(word);
            }





        }



    }



}
