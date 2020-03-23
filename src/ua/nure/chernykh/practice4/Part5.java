package ua.nure.chernykh.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {
    private static final String EN = "en";
    private static final String RU = "ru";
    private static final String MSG = "No such values";
    private static final String ENCODING = "cp1251";
    private static final int HEXADECIMAL = 16;

    public static void main(String[] args) {
        processCommands();
    }

    public static void processCommands() {
        String enRes = Util.readFile("src/resources_en.properties");
        String ruRes = Util.readFile("src/resources_ru.properties");
        Scanner s = new Scanner(System.in, ENCODING);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if ("stop".equals(line)) {
                break;
            } else {
                String[] str = line.split(" ");
                if (str.length <= 1) {
                    System.out.println(MSG);
                } else {
                    switch (str[1]) {
                        case RU:
                            System.out.println(findMatches(str[0], ruRes));
                            break;
                        case EN:
                        default:
                            System.out.println(findMatches(str[0], enRes));
                            break;
                    }
                }
            }
        }
    }

    public static String findMatches(String input, String res) {
        Pattern p = Pattern.compile("(?U)(?<=" + input + " = ).+");
        Matcher m = p.matcher(res);
        if (m.find()) {
            if ("\\".equals("" + m.group().charAt(0))) {
                return convertString(m.group());
            }
            return m.group();
        }
        return MSG;
    }

    private static String convertString(String input) {
        input = input.replace("\\", "");
        String[] arr = input.split("u");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            int hexVal = Integer.parseInt(arr[i], HEXADECIMAL);
            sb.append((char) hexVal);
        }
        return sb.toString();
    }

}
