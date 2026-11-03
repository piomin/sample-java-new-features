package pl.piomin.java.samples.scopedvalues;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScopedValueExampleTests {

    private static final String USER = "John Smith";

    @Test
    void shouldGetValueInScope() throws Exception {
        ScopedValueExample example = new ScopedValueExample();
        String user = ScopedValue.where(ScopedValueExample.USER, USER).call(example::getUser);
        Assertions.assertEquals(USER, user);
    }

    @Test
    void shouldNotBeBoundOutsideScope() {
        Assertions.assertFalse(ScopedValueExample.USER.isBound());
    }

    @Test
    void shouldGetValueInFork() throws Exception {
        ScopedValueExample example = new ScopedValueExample();
        String user = ScopedValue.where(ScopedValueExample.USER, USER).call(example::getUserInFork);
        Assertions.assertEquals(USER, user);
    }

}
