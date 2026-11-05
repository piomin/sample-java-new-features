package pl.piomin.java.samples.moduleimports;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ModuleImportExampleTests {

    private final ModuleImportExample example = new ModuleImportExample();
    private final List<String> names = List.of("anna", "bob", "christopher");

    @Test
    void shouldUpperCase() {
        Assertions.assertEquals(List.of("ANNA", "BOB", "CHRISTOPHER"), example.toUpperCase(names));
    }

    @Test
    void shouldGroupByLength() {
        var grouped = example.groupByLength(names);
        Assertions.assertEquals(List.of("bob"), grouped.get(3));
        Assertions.assertEquals(3, grouped.size());
    }

    @Test
    void shouldFindLongest() {
        Assertions.assertEquals("christopher", example.findLongest(names).orElseThrow());
        Assertions.assertTrue(example.findLongest(List.of()).isEmpty());
    }

    @Test
    void shouldResolveAmbiguousList() {
        var ambiguous = new AmbiguousImportExample();
        Assertions.assertEquals(3, ambiguous.names().size());
        Assertions.assertInstanceOf(java.awt.List.class, ambiguous.awtList());
        Assertions.assertEquals(java.awt.Color.RED, ambiguous.red());
    }
}
