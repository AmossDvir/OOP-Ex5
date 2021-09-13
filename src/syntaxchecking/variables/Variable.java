package syntaxchecking.variables;

import syntaxchecking.methods.exceptions.DeclarationException;
import utilities.Pair;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static syntaxchecking.VariablesTypes.TYPES_LIST;
import static utilities.RegexExpressions.PARAMS_NAME_PATTERN;
import static utilities.StringManipulations.splitToWords;

public class Variable {
    // Constants:
    public static final int GLOBAL = 0;

    // Members:
    private String name;
    private String type;
    private boolean isFinal;
    private boolean isInitialized;
    private int definedAtLine;

    // Constructors:

    /**
     * a Default constructor, initializes all the members with default values.
     */
    public Variable() {
        this.isFinal = false;
        this.isInitialized = false;
        this.type = null;
        this.name = null;
        this.definedAtLine = 0;
    }

    /**
     * Initializes all the values according to the given values.
     * @param name: the name of the variable.
     * @param type: the type of the variable.
     * @param isFinal: if the variable is final or not.
     * @param definedAtLine: the number of line in which the variable was declared in.
     */
    public Variable(String name,String type,boolean isFinal,int definedAtLine) {
        this.isFinal = isFinal;
        this.isInitialized = false;
        this.type = type;
        this.name = name;
        this.definedAtLine = definedAtLine;
    }

    /**
     * Initializes all the values according to the given values.
     * @param name: the name of the variable.
     * @param type: the type of the variable.
     * @param isFinal: if the variable is final or not.
     * @param isInitialized: if the variables is already initialized.
     * @param definedAtLine: the number of line in which the variable was declared in.
     */
    public Variable(String name,String type,boolean isFinal,boolean isInitialized,int definedAtLine) {
        this.isFinal = isFinal;
        this.isInitialized = isInitialized;
        this.type = type;
        this.name = name;
        this.definedAtLine = definedAtLine;
    }

    // Methods:
    /**
     * Returns the name of the variable.
     * @return : String, the name of the variable.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for the variable.
     * @param name: String, a new name.
     * @throws VariableException: in case it is an invalid name.
     */
    public void setName(String name) throws VariableException {
        Matcher matcher = PARAMS_NAME_PATTERN.matcher(name);
        if (!matcher.matches()) {
            throw new VariableException();
        }
        this.name = name;
    }

    /**
     * Returns the type of the variable.
     * @return : String, the type of the variable.
     */
    public String getType() {
        return type;
    }

    public void setType(String type) throws VariableException {
        if (!TYPES_LIST.contains(type)) {
            throw new VariableException();
        }
        this.type = type;
    }

    /**
     * Returns whether the variable is final or not.
     * @return : True is fo, else: false.
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Sets the state of the variable (final or not).
     * @param aFinal: boolean, false for non-final, true for final.
     */
    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    /**
     * Returns whether the variable is initialized or not.
     * @return : True if so, else: false.
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Sets initialization for a variable.
     * @param initialized: boolean, false for non-initialized, true for initialized.
     */
    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
    }

    /**
     * Returns the number of line in which the variable was declared at.
     * @return : int, the number of line.
     */
    public int getDefinedLine() {
        return definedAtLine;
    }
}



