package pl.piomin.java.samples.unmodifiablecollections;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CollectionsCreatorTest {

    @Test
    void listFormArray() {
        List<String> list = Arrays.asList("A", "B", "C");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.add("D"));
    }

    @Test
    void unmodifiableList() {
        List<String> list = List.of("A", "B", "C");
        Assertions.assertThrows(UnsupportedOperationException.class, () -> list.add("D"));
    }

    @Test
    void unmodifiableList2() {
        ImmutableList<String> list = Lists.immutable.of("A", "B", "C");
        Assertions.assertEquals(3, list.size());
    }
}
