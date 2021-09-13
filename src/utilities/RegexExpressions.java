package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores regular expressions.
 */
public class RegexExpressions {


    private static final String VOID_METHODS_DECLARATION = "\\s*void\\s*[a-zA-Z]+\\w*\\s*\\(.*\\)\\s*\\{";
    private static final String EXTRACTION = "\\((.*)\\)";
    private static final String PARAMS_NAME = "([a-zA-Z]\\w*)|(_\\w+)";
    private static final String CLOSING_BRACKETS = "\\s*\\}\\s*";

    public static final String SPLIT_PARAMS = ",+\\s*,*";
    public static final String SPLIT_SUB_CONDITIONS = "(\\|\\||&&)+\\s*(\\|\\||&&)*";

    // Types detecting:
    private static final String INT_TYPE = "\\s*-?\\d+\\s*";
    private static final String DOUBLE_TYPE = "\\s*-?\\d+(.\\d+)?\\s*";
    private static final String STRING_TYPE = "\"[^\"]*\"";
    private static final String CHAR_TYPE = "'([^'])'";
    private static final String BOOLEAN_TYPE = "(true|false|" + INT_TYPE + "|" + DOUBLE_TYPE + ")";

    private static final String INT_VAR_DECLARATION =
            "\\s*(final\\s+)?(int)\\s+\\w+\\s*(=\\(" + INT_TYPE + "\\)|\\(" + PARAMS_NAME +"\\))?(,\\s*\\w+\\s*(=" + INT_TYPE + ")?)*;\\s*";
    private static final String DOUBLE_VAR_DECLARATION =
            "\\s*(final\\s+)?(double)\\s+\\w+\\s*(=" + DOUBLE_TYPE + ")?(,\\s*\\w+\\s*(=" + DOUBLE_TYPE +
                    ")?)*;\\s*";
    private static final String STRING_VAR_DECLARATION =
            "\\s*(final\\s+)?(String)\\s+\\w+\\s*(=" + STRING_TYPE + ")?(,\\s*\\w+\\s*(=" + STRING_TYPE +
                    ")?)*;\\s*";
    private static final String CHAR_VAR_DECLARATION =
            "\\s*(final\\s+)?(char)\\s+\\w+\\s*(=" + CHAR_TYPE + ")?(,\\s*\\w+\\s*(=" + CHAR_TYPE + ")?)*;\\s*";
    private static final String BOOLEAN_VAR_DECLARATION =
            "\\s*(final\\s+)?(boolean)\\s+\\w+\\s*(=" + BOOLEAN_TYPE + ")?(,\\s*\\w+\\s*(=" + BOOLEAN_TYPE +
                    ")?)*;\\s*";

    public static final char COMMA = ',';
    public static final String OR_OPERATOR = "\\|\\|";
    public static final String AND_OPERATOR = "&&";
    public static final String WHITE_SPACE = " ";
    public static final char WHITE_SPACE_CHAR = ' ';
    public static final String COMMENT_LINE = "//.*";
    public static final String BLANK_LINE = "\\s*";



    // Compile Regexes:
    public static final Pattern VOID_METHOD_DEC_PATTERN = Pattern.compile(VOID_METHODS_DECLARATION);
    public static final Pattern EXTRACTION_PATTERN = Pattern.compile(EXTRACTION);
    public static final Pattern PARAMS_NAME_PATTERN = Pattern.compile(PARAMS_NAME);

    public static final Pattern INT_TYPE_PATTERN = Pattern.compile(INT_TYPE);
    public static final Pattern DOUBLE_TYPE_PATTERN = Pattern.compile(DOUBLE_TYPE);
    public static final Pattern STRING_TYPE_PATTERN = Pattern.compile(STRING_TYPE);
    public static final Pattern CHAR_TYPE_PATTERN = Pattern.compile(CHAR_TYPE);
    public static final Pattern BOOLEAN_TYPE_PATTERN = Pattern.compile(BOOLEAN_TYPE);

    public static final Pattern INT_DEC_PATTERN = Pattern.compile(INT_VAR_DECLARATION);
    public static final Pattern DOUBLE_DEC_PATTERN = Pattern.compile(DOUBLE_VAR_DECLARATION);
    public static final Pattern STRING_DEC_PATTERN = Pattern.compile(STRING_VAR_DECLARATION);
    public static final Pattern CHAR_DEC_PATTERN = Pattern.compile(CHAR_VAR_DECLARATION);
    public static final Pattern BOOLEAN_DEC_PATTERN = Pattern.compile(BOOLEAN_VAR_DECLARATION);

    public static final Pattern CLOSING_BRACKETS_PATTERN = Pattern.compile(CLOSING_BRACKETS);

    public static final Pattern COMMENT_LINE_PATTERN = Pattern.compile(COMMENT_LINE);
    public static final Pattern BLANK_LINE_PATTERN = Pattern.compile(BLANK_LINE);



    // Line Classification:
    private static final String IF_WHILE_BLOCK = "\\s*(if|while)\\s*\\(.*\\)\\s*\\{\\s*";
    private static final String RETURN_BLOCK = "\\s*return\\s*;\\s*";
    private static final String METHOD_CALLING_BLOCK =
            "^\\s*(?!\\s*if\\s*\\(+.*\\)+\\s*\\{+\\s*$|\\s*while\\s*\\(+.*\\)+\\s*\\{+\\s*$)\\S*\\s*\\(+.*\\)+\\s*;+\\s*$";

    // Compile Regexes:
    public static final Pattern IF_WHILE_BLOCK_PATTERN = Pattern.compile(IF_WHILE_BLOCK);
    public static final Pattern METHOD_CALLING_BLOCK_PATTERN = Pattern.compile(METHOD_CALLING_BLOCK);
    public static final Pattern RETURN_BLOCK_PATTERN = Pattern.compile(RETURN_BLOCK);

    // TODO: TEST ZONE,DELETE!!!
    public static void main(String[] args) {
        Matcher matcher;
        boolean isMatching = true;
        String failedStr = "";
        List<String> intDecs = new ArrayList<>();
        // Test these:
        intDecs.add("    final  int  grg = 5,r; ");
        intDecs.add("     int  grg = 5,r; ");
        intDecs.add("    final  int  grg = 5,r=8; ");
//        intDecs.add("      int  grg = 5,r=8,f      ,f,     f,f;");
        intDecs.add("int  t,r=8; ");
//        intDecs.add("int  t,r=jkjhkjhj; ");

        // Check if everything matches:
        for(String intDec:intDecs){
            matcher = INT_DEC_PATTERN.matcher(intDec);
            if(!matcher.matches()){
                isMatching = false;
                failedStr = intDec;
            }
        }
        System.out.println(isMatching ?"Passed all tests!":"Failed at: " + failedStr);
    }
}
