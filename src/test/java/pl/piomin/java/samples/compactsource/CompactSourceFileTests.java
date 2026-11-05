package pl.piomin.java.samples.compactsource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class CompactSourceFileTests {

    private static final Path DIR = Path.of("src/main/source-launcher");

    @Test
    void shouldRunCompactSourceFile() throws Exception {
        Assertions.assertEquals("Hello from a compact source file!", run(DIR.resolve("Hello.java")));
    }

    @Test
    void shouldRunMultiFileSourceProgram() throws Exception {
        Assertions.assertEquals("Hello from a multi-file source program!",
                run(DIR.resolve("multifile/Main.java")));
    }

    private String run(Path file) throws Exception {
        String java = Path.of(System.getProperty("java.home"), "bin", "java").toString();
        Process process = new ProcessBuilder(java, file.toString())
                .redirectErrorStream(true)
                .start();
        String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8).trim();
        Assertions.assertEquals(0, process.waitFor(), output);
        return output;
    }

}
