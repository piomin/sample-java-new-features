package pl.piomin.java.samples.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HexFormat;

public class KdfExampleTests {

    private final KdfExample kdf = new KdfExample();

    @Test
    void rfc5869TestCase1() throws GeneralSecurityException {
        byte[] ikm = HexFormat.of().parseHex("0b".repeat(22));
        byte[] salt = HexFormat.of().parseHex("000102030405060708090a0b0c");
        byte[] info = HexFormat.of().parseHex("f0f1f2f3f4f5f6f7f8f9");
        byte[] okm = kdf.deriveData(ikm, salt, info, 42);
        Assertions.assertEquals("3cb25f25faacd57a90434f64d0362f2a2d2d0a90cf1a5a4c5db02d56ecc4c5bf34007208d5b887185865",
                HexFormat.of().formatHex(okm));
    }

    @Test
    void deriveAesKey() throws GeneralSecurityException {
        var key = kdf.deriveAesKey("secret".getBytes(), "salt".getBytes(), "ctx".getBytes());
        Assertions.assertEquals("AES", key.getAlgorithm());
        Assertions.assertEquals(32, key.getEncoded().length);
        Assertions.assertArrayEquals(key.getEncoded(),
                kdf.deriveAesKey("secret".getBytes(), "salt".getBytes(), "ctx".getBytes()).getEncoded());
        Assertions.assertFalse(Arrays.equals(key.getEncoded(),
                kdf.deriveAesKey("secret".getBytes(), "salt".getBytes(), "other".getBytes()).getEncoded()));
    }
}
