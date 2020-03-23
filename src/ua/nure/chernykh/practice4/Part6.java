package ua.nure.chernykh.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final String CYRILLIC = "(\\p{IsCyrillic})+";
    private static final String LATIN = "(\\p{IsLatin})+";
    private static final String ENCODING = "Cp1251";

    public static void main(String[] args) {
        processCommands("part6.txt");
    }

    public static void processCommands(String pathfile) {
        String input = Util.readFile(pathfile);
        Scanner s = new Scanner(System.in, ENCODING);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if ("stop".equalsIgnoreCase(line)) {
                break;
            } else {
                if ("latn".equalsIgnoreCase(line)) {
                    System.out.println(findMatches(input, LATIN, line));
                } else if ("cyrl".equalsIgnoreCase(line)) {
                    System.out.println(findMatches(input, CYRILLIC, line));
                } else {
                    System.out.println(line + ": Incorrect input");
                }
            }
        }
    }

    public static String findMatches(String input, String regex, String request) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        StringBuilder sb = new StringBuilder();
        if (m.find()) {
            sb.append(request).append(": ").append(m.group()).append(" ");
        }
        while (m.find()) {
            sb.append(m.group()).append(" ");
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
            return sb.toString();
        } else {
            return "No such values";
        }
    }

}
