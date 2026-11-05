package pl.piomin.java.samples.primitivepatterns;

/**
 * Primitive types in patterns, {@code instanceof} and {@code switch} (JEP 507, third preview in Java 25).
 * Requires {@code --enable-preview}.
 */
public class PrimitivePatterns {

    /**
     * {@code switch} over a {@code double} with a constant and guarded primitive patterns.
     */
    public String describeDouble(double value) {
        return switch (value) {
            case 0.0 -> "zero";
            case double d when d < 0 -> "negative";
            case double d when d < 1 -> "fraction";
            case double d -> "positive";
        };
    }

    /**
     * {@code switch} over a {@code long} with a guard.
     */
    public String describeLong(long value) {
        return switch (value) {
            case 0L -> "zero";
            case long l when l > Integer.MAX_VALUE || l < Integer.MIN_VALUE -> "big";
            case long l -> "int-sized";
        };
    }

    /**
     * {@code switch} over an {@code int} using a primitive type pattern for the remaining values.
     */
    public String describeInt(int value) {
        return switch (value) {
            case 1 -> "one";
            case 2 -> "two";
            case int i when i < 0 -> "negative";
            case int i -> "other";
        };
    }

    /**
     * Safe conversion: matches only if the {@code int} can be represented exactly as a {@code byte}.
     */
    public boolean fitsInByte(int value) {
        return value instanceof byte;
    }

    /**
     * Returns the value as {@code byte}, or {@code null} if the conversion would lose information.
     */
    public Byte toByteSafely(int value) {
        if (value instanceof byte b) {
            return b;
        }
        return null;
    }

    /**
     * Checks whether a {@code long} can be converted to {@code int} without loss.
     */
    public boolean fitsInInt(long value) {
        return value instanceof int;
    }

    /**
     * Checks whether a {@code double} holds an exact {@code int} value.
     */
    public boolean isWholeNumber(double value) {
        return value instanceof int;
    }

    /**
     * Checks whether an {@code int} can be exactly represented as a {@code float}.
     */
    public boolean fitsInFloat(int value) {
        return value instanceof float;
    }

    /**
     * Primitive patterns combined with boxed types matched from an {@code Object}.
     */
    public String describeObject(Object o) {
        return switch (o) {
            case Integer i when i.intValue() instanceof byte b -> "small int " + b;
            case Integer i -> "int " + i;
            case Double d -> "double " + d;
            default -> "other";
        };
    }

}
