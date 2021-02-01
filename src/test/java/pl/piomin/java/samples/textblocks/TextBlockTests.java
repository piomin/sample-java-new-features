package pl.piomin.java.samples.textblocks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextBlockTests {

    @Test
    void shouldBeTheSame() {
        TextBlockString textBlockString = new TextBlockString();
        System.out.println(textBlockString.getOldPrettyPrintJson());
        System.out.println(textBlockString.getNewPrettyPrintJson());
        Assertions.assertEquals(textBlockString.getOldPrettyPrintJson(),
                textBlockString.getNewPrettyPrintJson());
    }
}
