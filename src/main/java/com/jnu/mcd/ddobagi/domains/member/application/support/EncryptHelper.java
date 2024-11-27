package com.jnu.mcd.ddobagi.domains.member.application.support;

import com.jnu.mcd.ddobagi.common.consts.StaticConst;
import com.jnu.mcd.ddobagi.common.exception.InvalidEncryptKeyException;
import com.jnu.mcd.ddobagi.common.exception.NotFoundAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.mindrot.jbcrypt.BCrypt;

public class EncryptHelper {

    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean isMatch(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }

    public static byte[] hmacEncrypt(String value, String key) {
        String algorithm = StaticConst.HMAC_ALGORITHM;
        Mac sha256hmac;

        try {
            sha256hmac = Mac.getInstance(algorithm);
            SecretKeySpec secretKey =
                    new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algorithm);
            sha256hmac.init(secretKey);
        } catch (NoSuchAlgorithmException e) {
            throw new NotFoundAlgorithmException();
        } catch (InvalidKeyException e) {
            throw new InvalidEncryptKeyException();
        }

        return sha256hmac.doFinal(value.getBytes(StandardCharsets.UTF_8));
    }

    private static String byteArrayToHex(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
