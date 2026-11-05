package pl.piomin.java.samples.smallapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTests {

    @Test
    void formatted() {
        assertEquals("Hello Piotr, you are 40", "Hello %s, you are %d".formatted("Piotr", 40));
    }

    @Test
    void stripIndent() {
        assertEquals("a\n  b\nc", "    a\n      b\n    c".stripIndent());
    }

    @Test
    void translateEscapes() {
        assertEquals("a\tb\nc", "a\\tb\\nc".translateEscapes());
    }

    @Test
    void splitWithDelimiters() {
        String[] parts = "a1b22c".splitWithDelimiters("\\d+", 0);
        assertArrayEquals(new String[]{"a", "1", "b", "22", "c"}, parts);
    }

    @Test
    void stringBuilderRepeat() {
        StringBuilder sb = new StringBuilder();
        sb.repeat("ab", 3).repeat('-', 2);
        assertEquals("ababab--", sb.toString());
    }
}
