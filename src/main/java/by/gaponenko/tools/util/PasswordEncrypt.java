package by.gaponenko.tools.util;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncrypt {

    public static String encrypt(String password) {
        return (DigestUtils.md5Hex(password));
    }

    private PasswordEncrypt() {
    }
}
