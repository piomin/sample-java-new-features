package pl.piomin.java.samples.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PEMDecoder;
import java.security.PEMEncoder;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * PEM Encodings of Cryptographic Objects (JEP 470, preview in Java 25).
 */
public class PemExample {

    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        return KeyPairGenerator.getInstance("EC").generateKeyPair();
    }

    public String encode(PublicKey key) {
        return PEMEncoder.of().encodeToString(key);
    }

    public String encode(PrivateKey key) {
        return PEMEncoder.of().encodeToString(key);
    }

    public String encodeEncrypted(PrivateKey key, char[] password) {
        return PEMEncoder.of().withEncryption(password).encodeToString(key);
    }

    public PublicKey decodePublic(String pem) {
        return PEMDecoder.of().decode(pem, PublicKey.class);
    }

    public PrivateKey decodePrivate(String pem) {
        return PEMDecoder.of().decode(pem, PrivateKey.class);
    }

    public PrivateKey decodeEncryptedPrivate(String pem, char[] password) {
        return PEMDecoder.of().withDecryption(password).decode(pem, PrivateKey.class);
    }
}
