package pl.piomin.java.samples.switches;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SwitchComparisonTests {

    @Test
    void switchWithoutBreak() {
        SwitchComparison switchComparison = new SwitchComparison();
        String x = switchComparison.oldSwitchWithBreak(1);
        Assertions.assertEquals("one", x);
    }

}
