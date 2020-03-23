package ua.nure.chernykh.practice4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) {
        String initialText = Util.readFile("part1.txt");
        String convertedText = convert(initialText);
        System.out.println(convertedText);
    }

    public static String convert(String text) {
        Pattern p = Pattern.compile("(?U)\\w{4,}");
        Matcher m = p.matcher(text);
        StringBuffer sb = new StringBuffer(text.length());
        while (m.find()) {
            m.appendReplacement(sb, reverseWord(m.group()));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static String reverseWord(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }

}
