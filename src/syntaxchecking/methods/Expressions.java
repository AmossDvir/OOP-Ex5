package syntaxchecking.methods;

import java.util.regex.Pattern;


public class Expressions {

    private static final String VOID_METHODS_DECLARATION = "\\s*void\\s*[a-zA-Z]+\\w*\\s*\\(.*\\)\\s*\\{";
    private static final String PARAMS_EXTRACTION = "\\((.*)\\)";
    private static final String PARAMS_NAME = "([a-zA-Z]\\w*)|(_\\w+)";
    public static final String SPLIT_PARAMS = ",+\\s*,*";
    public static final char COMMA = ',';
    public static final String WHITE_SPACE = " ";

    // Compile them:
    public static final Pattern VOID_METHOD_DEC_PATTERN = Pattern.compile(VOID_METHODS_DECLARATION);
    public static final Pattern PARAMS_EXTRACTION_PATTERN = Pattern.compile(PARAMS_EXTRACTION);
    public static final Pattern PARAMS_NAME_PATTERN = Pattern.compile(PARAMS_NAME);

    // Line Classification:
    private static final String IF_WHILE_BLOCK = "\\s*(if|while)\\s*\\(.*\\)\\s*\\{\\s*";
    private static final String RETURN_BLOCK = "\\s*return\\s*;\\s*";
    private static final String METHOD_CALLING_BLOCK = "^\\s*(?!\\s*if\\s*\\(+.*\\)+\\s*\\{+\\s*$|\\s*while\\s*\\(+.*\\)+\\s*\\{+\\s*$)\\S*\\s*\\(+.*\\)+\\s*;+\\s*$";

    // Compile them:
    public static final Pattern IF_WHILE_BLOCK_PATTERN = Pattern.compile(IF_WHILE_BLOCK);
    public static final Pattern METHOD_CALLING_BLOCK_PATTERN = Pattern.compile(METHOD_CALLING_BLOCK);
    public static final Pattern RETURN_BLOCK_PATTERN = Pattern.compile(RETURN_BLOCK);

}
