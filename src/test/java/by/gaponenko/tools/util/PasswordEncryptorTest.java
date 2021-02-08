package by.gaponenko.tools.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PasswordEncryptorTest {

    @Test
    void encryptPositiveTest() {
        String expected = "d8578edf8458ce06fbc5bb76a58c5ca4";
        String actual = PasswordEncryptor.encrypt("qwerty");
        Assert.assertEquals(actual, expected);
    }

    @Test
    void encryptNegativeTest() {
        String unexpected = "d8578edf8458ce06fbc5bb76a58c5ca3";
        String actual = PasswordEncryptor.encrypt("qwerty");
        Assert.assertNotEquals(unexpected, actual);
    }
}
