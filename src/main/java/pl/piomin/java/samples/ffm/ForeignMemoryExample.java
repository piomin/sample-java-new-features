package pl.piomin.java.samples.ffm;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

/**
 * Foreign Function &amp; Memory API (JEP 454, final in Java 22). Restricted methods such as
 * {@link Linker#downcallHandle} need {@code --enable-native-access=ALL-UNNAMED} (configured for surefire in pom.xml),
 * otherwise a warning is printed.
 */
public class ForeignMemoryExample {

    private static final MethodHandle STRLEN;

    static {
        Linker linker = Linker.nativeLinker();
        STRLEN = linker.downcallHandle(
                linker.defaultLookup().find("strlen").orElseThrow(),
                FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS));
    }

    /** Calls libc {@code strlen} on a native, null-terminated copy of the string. */
    public long strlen(String text) throws Throwable {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment str = arena.allocateFrom(text);
            return (long) STRLEN.invokeExact(str);
        }
    }

    /** Allocates a native int array, fills it and returns the sum of its elements. */
    public int sumInts(int... values) {
        try (Arena arena = Arena.ofConfined()) {
            MemorySegment segment = arena.allocate(ValueLayout.JAVA_INT, values.length);
            for (int i = 0; i < values.length; i++) {
                segment.setAtIndex(ValueLayout.JAVA_INT, i, values[i]);
            }
            int sum = 0;
            for (int i = 0; i < values.length; i++) {
                sum += segment.getAtIndex(ValueLayout.JAVA_INT, i);
            }
            return sum;
        }
    }

    /** Returns the segment size in bytes for the given number of ints. */
    public long allocatedSize(int count) {
        try (Arena arena = Arena.ofConfined()) {
            return arena.allocate(ValueLayout.JAVA_INT, count).byteSize();
        }
    }
}
