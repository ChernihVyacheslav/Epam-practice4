package ua.nure.chernykh.practice4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {
    private static final String FILEPATH = "part4.txt";
    private static final Pattern p = Pattern.compile("\\p{javaUpperCase}.*?\\.");

    public static void main(String[] args) {
        Iterator it = new Part4().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }


    @Override
    public Iterator<String> iterator() {
        return new IteratorImpl();
    }

    private static class IteratorImpl implements Iterator<String> {

        private String input;
        private boolean isFound;
        private Matcher m;
        private String lastWord;

        public IteratorImpl() {
            input = Util.readFile(FILEPATH).replaceAll("[\\n\\r]+", "");
            m = p.matcher(input);
            isFound = false;
            lastWord = null;
        }

        @Override
        public boolean hasNext() {
            if (!isFound && m.find()) {
                lastWord = m.group();
                isFound = true;
                return true;
            }
            return isFound;
        }

        @Override
        public String next() {
            String next;
            if (hasNext()) {
                next = lastWord;
                lastWord = null;
                isFound = false;
            } else {
                throw new NoSuchElementException();
            }
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
