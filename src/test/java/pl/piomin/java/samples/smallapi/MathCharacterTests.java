package pl.piomin.java.samples.smallapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathCharacterTests {

    @Test
    void clamp() {
        assertEquals(10, Math.clamp(15, 0, 10));
        assertEquals(0, Math.clamp(-5, 0, 10));
        assertEquals(7, Math.clamp(7, 0, 10));
        assertEquals(1.5, Math.clamp(1.5, 0.0, 2.0));
    }

    @Test
    void clampInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> Math.clamp(5, 10, 0));
    }

    @Test
    void emoji() {
        assertTrue(Character.isEmoji(0x1F600)); // grinning face
        assertFalse(Character.isEmoji('a'));
        assertTrue(Character.isEmojiPresentation(0x1F600));
    }
}
