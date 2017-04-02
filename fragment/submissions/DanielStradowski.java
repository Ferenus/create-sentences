package fragment.submissions;

import org.junit.*;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DanielStradowski {

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
            in.lines()
                    .map(DanielStradowski::reassemble)
                    .forEach(System.out::println);
        }
    }

    private static enum Direction {
        LEFT,
        RIGHT;
    }

    private static class TextFragment {

        private String text;
        private TextFragment left, right;
        private int matchLength;

        public TextFragment(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public TextFragment getLeft() {
            return left;
        }

        public void setLeft(TextFragment left) {
            this.left = left;
        }

        public TextFragment getRight() {
            return right;
        }

        public void setRight(TextFragment right) {
            this.right = right;
        }

        public int getMatchLength() {
            return matchLength;
        }

        public void setMatchLength(int matchLength) {
            this.matchLength = matchLength;
        }

    }

    private static String reassemble(String line) {
        List<TextFragment> textFragments = Arrays.stream(line.split(";"))
                .map(TextFragment::new)
                .sorted((TextFragment s1, TextFragment s2) -> s2.text.length() - s1.text.length())
                .collect(toList());

        textFragments.forEach(leftFragment -> findMatchingRightFragment(leftFragment, textFragments));

        return createOutputLine(textFragments.stream().filter(e -> e.getLeft() == null).findFirst().get());
    }


    private static String createOutputLine(TextFragment first) {
        StringBuilder solution = new StringBuilder();
        int len = 0;
        while (first != null) {
            solution.append(first.getText().substring(len));
            len = first.getMatchLength();
            first = first.getRight();
        }
        return solution.toString();
    }

    private static void findMatchingRightFragment(TextFragment leftFragment, List<TextFragment> fragments) {
        fragments.stream()
                .filter(rightFragment -> leftFragment != rightFragment)
                .forEach(rightFragment -> overlap(leftFragment, rightFragment)
                );
    }

    private static boolean overlap(TextFragment left, TextFragment right) {
        for (String sub : getSubstrings(left.getText(), Direction.RIGHT)) {
            for (String match : getSubstrings(right.getText(), Direction.LEFT)) {
                if (sub.equals(match)) {
                    if (left.getRight() == null) {
                        left.setRight(right);
                        left.setMatchLength(match.length());
                    }
                    if (right.getLeft() == null) {
                        right.setLeft(left);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static List<String> getSubstrings(String fragment, Direction direction) {

        List<String> substrings = new ArrayList<>();

        for (int i = 0; i < fragment.length(); i++) {
            String overlap;
            if (direction == Direction.LEFT) {
                overlap = fragment.substring(0, fragment.length() - i);
            } else {
                overlap = fragment.substring(i);
            }
            substrings.add(overlap);
        }

        substrings.remove(0);
        substrings.remove(substrings.size() - 1);

        return substrings;

    }

    public static class DanielStradowskiTest {

        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        @Before
        public void setUpStreams() {
            System.setOut(new PrintStream(outContent));
        }

        @Test
        public void shouldPrintProperOutput() throws IOException {
            main(new String[]{"C:\\Users\\Daniel\\Desktop\\test\\CreateSentences\\create-sentances\\src\\fragment\\submissions\\reassemble-text-fragments-example.txt"});

            assertEquals("O draconian devil! Oh lame saint!\r\n" +
                    "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.\r\n", outContent.toString());
        }

        @After
        public void cleanUpStreams() {
            System.setOut(null);
        }

    }
}