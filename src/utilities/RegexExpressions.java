package utilities;

import java.util.regex.Pattern;

/**
 * Stores regular expressions.
 */
public class RegexExpressions {


    private static final String VOID_METHODS_DECLARATION = "\\s*void\\s*[a-zA-Z]+\\w*\\s*\\(.*\\)\\s*\\{";
    private static final String EXTRACTION = "\\((.*)\\)";
    private static final String PARAMS_NAME = "([a-zA-Z]\\w*)|(_\\w+)";
    public static final String SPLIT_PARAMS = ",+\\s*,*";
    public static final String SPLIT_SUB_CONDITIONS = "(\\|\\||&&)+\\s*(\\|\\||&&)*";
    // Types detecting:
    private static final String INT_TYPE = "\\s*-?\\d+\\s*";
    private static final String DOUBLE_TYPE = "\\s*-?\\d+(.\\d+)?\\s*";
    private static final String STRING_TYPE = "\".*\"";
    private static final String CHAR_TYPE = "'.'";
    private static final String BOOLEAN_TYPE = "(true|false|" + INT_TYPE + "|"+DOUBLE_TYPE +")";

    public static final char COMMA = ',';
    public static final String OR_OPERATOR = "\\|\\|";
    public static final String AND_OPERATOR = "&&";
    public static final String WHITE_SPACE = "\\s";
    public static final char WHITE_SPACE_CHAR = ' ';


    // Compile them:
    public static final Pattern VOID_METHOD_DEC_PATTERN = Pattern.compile(VOID_METHODS_DECLARATION);
    public static final Pattern EXTRACTION_PATTERN = Pattern.compile(EXTRACTION);
    public static final Pattern PARAMS_NAME_PATTERN = Pattern.compile(PARAMS_NAME);

    public static final Pattern INT_TYPE_PATTERN = Pattern.compile(INT_TYPE);
    public static final Pattern DOUBLE_TYPE_PATTERN = Pattern.compile(DOUBLE_TYPE);
    public static final Pattern STRING_TYPE_PATTERN = Pattern.compile(STRING_TYPE);
    public static final Pattern CHAR_TYPE_PATTERN = Pattern.compile(CHAR_TYPE);
    public static final Pattern BOOLEAN_TYPE_PATTERN = Pattern.compile(BOOLEAN_TYPE);



    // Line Classification:
    private static final String IF_WHILE_BLOCK = "\\s*(if|while)\\s*\\(.*\\)\\s*\\{\\s*";
    private static final String RETURN_BLOCK = "\\s*return\\s*;\\s*";
    private static final String METHOD_CALLING_BLOCK = "^\\s*(?!\\s*if\\s*\\(+.*\\)+\\s*\\{+\\s*$|\\s*while\\s*\\(+.*\\)+\\s*\\{+\\s*$)\\S*\\s*\\(+.*\\)+\\s*;+\\s*$";

    // Compile them:
    public static final Pattern IF_WHILE_BLOCK_PATTERN = Pattern.compile(IF_WHILE_BLOCK);
    public static final Pattern METHOD_CALLING_BLOCK_PATTERN = Pattern.compile(METHOD_CALLING_BLOCK);
    public static final Pattern RETURN_BLOCK_PATTERN = Pattern.compile(RETURN_BLOCK);

}
