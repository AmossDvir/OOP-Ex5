package syntaxchecking.variables;

import utilities.Pair;

import java.util.List;
import java.util.Map;

import static syntaxchecking.VariablesTypes.TYPES_LIST;
import static utilities.StringManipulations.splitToWords;

public class Variable {
    private String name;
    private String type;
    private boolean isFinal;
    private boolean isInitialized;


    public Variable() {
        this.isFinal = false;
        this.isInitialized = false;
        this.type = null;
        this.name = null;
    }
    public Variable( String name,String type,boolean isFinal,boolean isInitialized) {
        this.isFinal = isFinal;
        this.isInitialized = isInitialized;
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }
}



