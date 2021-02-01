package pl.piomin.java.samples.unmodifiablecollections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CollectionsCreatorTest {

    @Test
    void shouldThrow() {
//        CollectionsCreator creator = new CollectionsCreator();
//        creator.fruits().add("strawberry");

        Arrays.asList("apple", "banana", "orange").add("strawberry");
    }

}
