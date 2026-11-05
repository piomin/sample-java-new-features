package pl.piomin.java.samples.unnamed;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import pl.piomin.java.samples.unnamed.UnnamedVariables.Circle;
import pl.piomin.java.samples.unnamed.UnnamedVariables.Point;
import pl.piomin.java.samples.unnamed.UnnamedVariables.Rectangle;
import pl.piomin.java.samples.unnamed.UnnamedVariables.Resource;
import pl.piomin.java.samples.unnamed.UnnamedVariables.Square;

public class UnnamedVariablesTests {

    private final UnnamedVariables example = new UnnamedVariables();

    @Test
    void shouldParseOrReturnDefault() {
        Assertions.assertEquals(5, example.parseOrDefault("5", -1));
        Assertions.assertEquals(-1, example.parseOrDefault("abc", -1));
    }

    @Test
    void shouldCountItems() {
        Assertions.assertEquals(3, example.count(List.of("a", "b", "c")));
        Assertions.assertEquals(0, example.count(List.of()));
    }

    @Test
    void shouldInitCounters() {
        var map = example.initCounters(List.of("a", "b"));
        Assertions.assertEquals(0, map.get("a"));
        Assertions.assertEquals(2, map.size());
    }

    @Test
    void shouldCloseResource() {
        Resource r = new Resource();
        Assertions.assertTrue(example.useResource(() -> r));
        Assertions.assertTrue(r.isClosed());
    }

    @Test
    void shouldHandleUnnamedLocal() {
        Assertions.assertEquals(7, example.firstOrZero(List.of(7, 8)));
        Assertions.assertEquals(0, example.firstOrZero(List.of()));
    }

    @Test
    void shouldExtractXWithUnnamedPatterns() {
        Assertions.assertEquals(1, example.xOfTopLeft(new Circle(new Point(1, 2), 3)));
        Assertions.assertEquals(4, example.xOfTopLeft(new Rectangle(new Point(4, 5), new Point(6, 7))));
        Assertions.assertEquals(8, example.xOfTopLeft(new Square(new Point(8, 9), 2)));
    }

    @Test
    void shouldDescribeShapes() {
        Assertions.assertEquals("round", example.describe(new Circle(new Point(0, 0), 1)));
        Assertions.assertEquals("polygon", example.describe(new Square(new Point(0, 0), 1)));
        Assertions.assertEquals("polygon", example.describe(new Rectangle(new Point(0, 0), new Point(1, 1))));
    }

    @Test
    void shouldMatchRecordWithUnnamedPatterns() {
        Assertions.assertTrue(example.isCircle(new Circle(new Point(0, 0), 1)));
        Assertions.assertFalse(example.isCircle("x"));
    }
}
