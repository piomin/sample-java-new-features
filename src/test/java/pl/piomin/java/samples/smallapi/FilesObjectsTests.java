package pl.piomin.java.samples.smallapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FilesObjectsTests {

    @TempDir
    Path dir;

    @Test
    void mismatch() throws IOException {
        Path a = Files.writeString(dir.resolve("a.txt"), "hello world");
        Path b = Files.writeString(dir.resolve("b.txt"), "hello there");
        Path c = Files.writeString(dir.resolve("c.txt"), "hello world");

        assertEquals(6, Files.mismatch(a, b));
        assertEquals(-1, Files.mismatch(a, c));
    }

    @Test
    void requireNonNullElse() {
        String value = null;
        assertEquals("default", Objects.requireNonNullElse(value, "default"));
        assertEquals("x", Objects.requireNonNullElse("x", "default"));
        assertEquals("lazy", Objects.requireNonNullElseGet(value, () -> "lazy"));
    }

    @Test
    void requireNonNullElseBothNull() {
        assertThrows(NullPointerException.class, () -> Objects.requireNonNullElse(null, null));
    }
}
