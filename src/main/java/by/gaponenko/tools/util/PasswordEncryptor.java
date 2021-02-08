package by.gaponenko.tools.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * The Password encryptor.
 * <p>
 * Encrypt password.
 *
 * @author Haponenka Ihar.
 * @version 1.0
 */
public class PasswordEncryptor {
    /**
     * Encrypt password.
     *
     * @param password the password
     * @return the string
     */
    public static String encrypt(String password) {
        return (DigestUtils.md5Hex(password));
    }

    private PasswordEncryptor() {
    }
}
