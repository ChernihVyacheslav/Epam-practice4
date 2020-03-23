package ua.nure.chernykh.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String ENCODING = "Cp1251";

    public static void main(String[] args) {
        processComands("part3.txt");
    }

    public static void processComands(String path) {
        String input = Util.readFile(path);
        Scanner s = new Scanner(System.in, ENCODING);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if ("stop".equals(line)) {
                break;
            }
            switch (line) {
                case "char":
                    System.out.println(findMatches(input, "(?U)(?=\\D)(\\b\\w\\b){1}"));
                    break;
                case "String":
                    System.out.println(findMatches(input, "(?U)(?=\\D)\\w{2,}"));
                    break;
                case "double":
                    System.out.println(findMatches(input, "\\d*\\.\\d*"));
                    break;
                case "int":
                    System.out.println(findMatches(input, "\\s\\d+\\s"));
                    break;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
    }

    public static String findMatches(String input, String regex) {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        while (m.find()) {
            sb.append(m.group().trim()).append(" ");
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        } else {
            sb.append("No such values");
        }
        return sb.toString();
    }

}
