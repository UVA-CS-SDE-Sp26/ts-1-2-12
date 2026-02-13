import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CipherTest {

    @Test
    void decipherDataInvalidKey() {
        Cipher cipher = new Cipher("bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a", "test3(for_cipher_test).txt");
        String result = cipher.decipherData();
        assertEquals("Error: invalid key.", result, "key should be an error.");
    }

    @Test
    void decipherDataValidKey() {
        Cipher cipher = new Cipher("efghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcd", "key2Test.txt");
        String result = cipher.decipherData();
        assertEquals("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", result,"expected abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 from efghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcd");
    }

    @Test
    void decipherDataNonexistentDataInKey() {
        Cipher cipher = new Cipher("***", "key.txt");
        String result = cipher.decipherData();
        assertEquals("***", result,"*** should decipher to ***.");
    }

    @Test
    void testEmptyData() {
        Cipher cipher = new Cipher("", "key.txt");
        String result = cipher.decipherData();
        assertEquals("", result, "expect nothing");
    }
}