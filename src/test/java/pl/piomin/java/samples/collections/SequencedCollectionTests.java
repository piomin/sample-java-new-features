package pl.piomin.java.samples.collections;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.SequencedSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequencedCollectionTests {

    @Test
    void shouldCallSet() {
        Set<Integer> x = Set.of(6, 7, 4, 8, 2, 3, 9, 0, 1);
        SequencedSet<Integer> s = new LinkedHashSet<>();
        s.add(0);
        s.add(9);
        System.out.println(s);
        assertEquals(s.getFirst(), 0);
        assertEquals(s.getLast(), 9);
        s.addFirst(5);
        s.addLast(10);
        System.out.println(s);
        assertEquals(s.getFirst(), 5);
        assertEquals(s.getLast(), 10);
        s.removeFirst();
        s.removeLast();
        assertEquals(s.getFirst(), 0);
        assertEquals(s.getLast(), 9);
        s = s.reversed();
        System.out.println(s);
        assertEquals(s.getFirst(), 9);
        assertEquals(s.getLast(), 0);
    }
}
