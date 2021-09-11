package utilities;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static utilities.RegexExpressions.*;

public class StringManipulations {
    /**
     * @param line
     * @return
     */
    public static String extractFromParantheses(String line) {
        Matcher paramsMatcher = EXTRACTION_PATTERN.matcher(line);
        paramsMatcher.find();
        return paramsMatcher.group(1);
    }

    /**
     * Counts the number of occurrences of a char appears in a String.
     * @param someChar: a given char.
     * @param line: a given String.
     * @return the number of occurrences of the char in the String.
     */
    public static int countOccurrences(String line, char someChar) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == someChar) {
                count++;
            }
        }
        return count;
    }


    /**
     * Counts the number of occurrences of a sub-string appears in a String.
     * @param someString: a given sub-string.
     * @param line: a given String.
     * @return the number of occurrences of the char in the String.
     */
    public static int countOccurrences(String line, String someString) {
        return line.split(someString, -1).length-1;

    }

    /**
     * Takes out all white-spaces that are more than one.
     *
     * @param str: a given String
     * @return a corrected string with only 1 white-space in between words.
     */
    public static String fixBlankSpots(String str) {
        str = str.trim();
        str = str.replaceAll("\\s{2,}", WHITE_SPACE);
        return str;
    }

    /**
     *
     * @param str
     * @return list of the words in the line
     */

    public  static  List<String>  splitToWords(String str){
        String params = fixBlankSpots(str);
        return  Arrays.stream(params.split(WHITE_SPACE)).toList();
    }
}
