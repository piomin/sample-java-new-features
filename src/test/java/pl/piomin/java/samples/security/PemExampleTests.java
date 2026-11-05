package pl.piomin.java.samples.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.GeneralSecurityException;

public class PemExampleTests {

    private final PemExample pem = new PemExample();

    @Test
    void publicKeyRoundTrip() throws GeneralSecurityException {
        var pair = pem.generateKeyPair();
        String encoded = pem.encode(pair.getPublic());
        Assertions.assertTrue(encoded.startsWith("-----BEGIN PUBLIC KEY-----"));
        Assertions.assertEquals(pair.getPublic(), pem.decodePublic(encoded));
    }

    @Test
    void privateKeyRoundTrip() throws GeneralSecurityException {
        var pair = pem.generateKeyPair();
        String encoded = pem.encode(pair.getPrivate());
        Assertions.assertTrue(encoded.startsWith("-----BEGIN PRIVATE KEY-----"));
        Assertions.assertEquals(pair.getPrivate(), pem.decodePrivate(encoded));
    }

    @Test
    void encryptedPrivateKeyRoundTrip() throws GeneralSecurityException {
        var pair = pem.generateKeyPair();
        char[] password = "changeit".toCharArray();
        String encoded = pem.encodeEncrypted(pair.getPrivate(), password);
        Assertions.assertTrue(encoded.startsWith("-----BEGIN ENCRYPTED PRIVATE KEY-----"));
        Assertions.assertEquals(pair.getPrivate(), pem.decodeEncryptedPrivate(encoded, password));
    }
}
