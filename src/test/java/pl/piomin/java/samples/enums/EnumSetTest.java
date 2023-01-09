package pl.piomin.java.samples.enums;

import org.junit.jupiter.api.Test;
import pl.piomin.java.samples.helper.Bar;
import pl.piomin.java.samples.helper.ColorOfRainbow;
import pl.piomin.java.samples.helper.Foo;

import java.util.EnumSet;

public class EnumSetTest {

    @Test
    void test() {
        EnumSet<ColorOfRainbow> all = EnumSet.allOf(ColorOfRainbow.class);
        EnumSet<ColorOfRainbow> part = EnumSet.range(ColorOfRainbow.ORANGE, ColorOfRainbow.BLUE);
        part.removeIf(c -> c.name().length() > 5);
        System.out.println(part);
        assert part.size() == 2;
        EnumSet<ColorOfRainbow> c = EnumSet.complementOf(part);
        System.out.println(c);
    }

}
