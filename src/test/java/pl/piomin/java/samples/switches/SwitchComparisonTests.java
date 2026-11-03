package pl.piomin.java.samples.switches;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.piomin.java.samples.sealedclasses.Cat;
import pl.piomin.java.samples.sealedclasses.Dog;

public class SwitchComparisonTests {

    @Test
    void switchWithoutBreak() {
        SwitchComparison switchComparison = new SwitchComparison();
        String x = switchComparison.oldSwitchWithBreak(1);
        Assertions.assertEquals("one", x);
    }

    @Test
    void switchPatternMatching() {
        Cat c = new Cat();
        SwitchComparison switchComparison = new SwitchComparison();
        String x = switchComparison.newSwitchWithPatternMatching(c);
        Assertions.assertEquals("cat", x);
    }

    @Test
    void switchPatternMatchingWithGuard() {
        SwitchComparison switchComparison = new SwitchComparison();
        Assertions.assertEquals("puppy", switchComparison.newSwitchWithPatternMatching(new Dog(1)));
        Assertions.assertEquals("dog", switchComparison.newSwitchWithPatternMatching(new Dog(5)));
    }

    @Test
    void switchPatternMatchingWithNull() {
        SwitchComparison switchComparison = new SwitchComparison();
        Assertions.assertEquals("no pet", switchComparison.newSwitchWithPatternMatching(null));
    }

}
