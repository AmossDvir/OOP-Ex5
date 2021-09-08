package syntaxchecking.methods.declarations;

import java.util.regex.Pattern;


public class Expressions {

    private static final String VOID_METHODS_DECLARATION = " *void *[a-zA-Z]+\\w *\\(.*\\) *\\{";
    private static final String PARAMS_EXTRACTION = "\\((.*)\\)";
    private static final String PARAMS_NAME = "([a-zA-Z]\\w*)|(_\\w+)";



    public static final Pattern VOID_METHOD_DEC_PATTERN = Pattern.compile(VOID_METHODS_DECLARATION);
    public static final Pattern PARAMS_EXTRACTION_PATTERN = Pattern.compile(PARAMS_EXTRACTION);
    public static final Pattern PARAMS_NAME_PATTERN = Pattern.compile(PARAMS_NAME);


}
