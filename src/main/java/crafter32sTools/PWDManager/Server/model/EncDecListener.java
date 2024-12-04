package crafter32sTools.PWDManager.Server.model;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PreUpdate;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncDecListener {

    private static final String ALGORITHM = "AES";

    public static String encrypt(String data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }
    public static String decrypt(String encryptedData, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }

    @PostLoad
    public void decrypt(User a) {
    }

    @PreUpdate
    public void encrypt(User a) {

    }
}
