package pl.piomin.java.samples.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.SequencedCollection;
import java.util.SequencedSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequencedCollectionTests {

    @Test
    void shouldCallSet() {
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

    @Test
    void shouldCallList() {
        SequencedCollection<Integer> s = new ArrayList<>();
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
