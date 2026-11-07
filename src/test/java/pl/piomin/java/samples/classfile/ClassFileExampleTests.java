package pl.piomin.java.samples.classfile;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ClassFileExampleTests {

    @Test
    void shouldListMethods() throws IOException {
        ClassFileExample example = new ClassFileExample();
        Assertions.assertTrue(example.listMethods(ClassFileExample.class).contains("generateAndCall"));
    }

    @Test
    void shouldGenerateClass() throws ReflectiveOperationException {
        ClassFileExample example = new ClassFileExample();
        Assertions.assertEquals(42, example.generateAndCall());
    }

}
