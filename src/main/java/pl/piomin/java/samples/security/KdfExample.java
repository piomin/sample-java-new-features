package pl.piomin.java.samples.security;

import javax.crypto.KDF;
import javax.crypto.SecretKey;
import javax.crypto.spec.HKDFParameterSpec;
import java.security.GeneralSecurityException;

/**
 * Key Derivation Function API (JEP 510, final in Java 25) with HKDF-SHA256.
 */
public class KdfExample {

    public SecretKey deriveAesKey(byte[] ikm, byte[] salt, byte[] info) throws GeneralSecurityException {
        KDF hkdf = KDF.getInstance("HKDF-SHA256");
        HKDFParameterSpec spec = HKDFParameterSpec.ofExtract()
                .addIKM(ikm)
                .addSalt(salt)
                .thenExpand(info, 32);
        return hkdf.deriveKey("AES", spec);
    }

    public byte[] deriveData(byte[] ikm, byte[] salt, byte[] info, int length) throws GeneralSecurityException {
        KDF hkdf = KDF.getInstance("HKDF-SHA256");
        return hkdf.deriveData(HKDFParameterSpec.ofExtract()
                .addIKM(ikm)
                .addSalt(salt)
                .thenExpand(info, length));
    }
}
