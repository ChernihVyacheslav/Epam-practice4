package ua.nure.chernykh.practice4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Demo {

    private static final InputStream STD_IN = System.in;

    private static final String ENCODING = "Cp1251";

    public static void main(String[] args) throws IOException {
        Util.main(args);
        System.out.println("Part 1: ");
        Part1.main(args);
        System.out.println("Part 2: ");
        Part2.main(args);
        System.out.println("Part 3: ");
        System.setIn(new ByteArrayInputStream(
                "char^String^double^int^stop".replace("^", System.lineSeparator()).getBytes(ENCODING)));
        Part3.main(args);
        System.setIn(STD_IN);
        System.out.println("Part 4: ");
        Part4.main(args);
        System.setIn(new ByteArrayInputStream(
                "table ru^table en^asdf en^apple ru^apple en^asdf^stop"
                        .replace("^", System.lineSeparator()).getBytes(ENCODING)));
        System.out.println("Part 5: ");
        Part5.main(args);
        System.setIn(STD_IN);
        System.setIn(new ByteArrayInputStream(
                "Latn^Cyrl^asdf^stop".replace("^", System.lineSeparator()).getBytes(ENCODING)));
        System.out.println("Part 6: ");
        Part6.main(args);
        System.setIn(STD_IN);
    }
}
