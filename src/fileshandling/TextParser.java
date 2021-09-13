package fileshandling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import static utilities.RegexExpressions.BLANK_LINE_PATTERN;
import static utilities.RegexExpressions.COMMENT_LINE_PATTERN;

/**
 * a simple static class for parsing text.
 */
public class TextParser {
    /**
     * Gets text lines (List of Strings) and removes the lines contain comments and blank lines.
     * @param lines: List of String, the text.
     */
    public static void removeUnnecessaryLines(List<String> lines){
        Matcher blankLineMat,commentLineMat;
        Iterator<String> it = lines.iterator();
        while(it.hasNext()){
            String line = it.next();
            blankLineMat = BLANK_LINE_PATTERN.matcher(line);
            commentLineMat = COMMENT_LINE_PATTERN.matcher(line);
            // Check if unnecessary line:
            if (blankLineMat.matches() || commentLineMat.matches()) {
                it.remove();
            }
        }
    }
}
