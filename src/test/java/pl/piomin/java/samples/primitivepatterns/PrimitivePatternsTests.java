package pl.piomin.java.samples.primitivepatterns;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimitivePatternsTests {

    private final PrimitivePatterns patterns = new PrimitivePatterns();

    @Test
    void switchOverDouble() {
        Assertions.assertEquals("zero", patterns.describeDouble(0.0));
        Assertions.assertEquals("negative", patterns.describeDouble(-2.5));
        Assertions.assertEquals("fraction", patterns.describeDouble(0.5));
        Assertions.assertEquals("positive", patterns.describeDouble(10.0));
    }

    @Test
    void switchOverLong() {
        Assertions.assertEquals("zero", patterns.describeLong(0L));
        Assertions.assertEquals("int-sized", patterns.describeLong(42L));
        Assertions.assertEquals("big", patterns.describeLong(Long.MAX_VALUE));
    }

    @Test
    void switchOverInt() {
        Assertions.assertEquals("one", patterns.describeInt(1));
        Assertions.assertEquals("negative", patterns.describeInt(-5));
        Assertions.assertEquals("other", patterns.describeInt(100));
    }

    @Test
    void instanceOfByte() {
        Assertions.assertTrue(patterns.fitsInByte(127));
        Assertions.assertFalse(patterns.fitsInByte(128));
        Assertions.assertEquals((byte) 100, patterns.toByteSafely(100));
        Assertions.assertNull(patterns.toByteSafely(1000));
    }

    @Test
    void instanceOfLongToInt() {
        Assertions.assertTrue(patterns.fitsInInt(123L));
        Assertions.assertFalse(patterns.fitsInInt(Long.MAX_VALUE));
    }

    @Test
    void instanceOfDoubleToInt() {
        Assertions.assertTrue(patterns.isWholeNumber(5.0));
        Assertions.assertFalse(patterns.isWholeNumber(5.5));
    }

    @Test
    void instanceOfIntToFloat() {
        Assertions.assertTrue(patterns.fitsInFloat(1 << 24));
        Assertions.assertFalse(patterns.fitsInFloat((1 << 24) + 1));
    }

    @Test
    void boxedValues() {
        Assertions.assertEquals("small int 5", patterns.describeObject(5));
        Assertions.assertEquals("int 5000", patterns.describeObject(5000));
        Assertions.assertEquals("double 1.5", patterns.describeObject(1.5));
        Assertions.assertEquals("other", patterns.describeObject("x"));
    }

}
