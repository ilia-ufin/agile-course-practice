package ru.unn.agile.caesarcipher.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class CaesarCipherTest {

    private static final String TEST_STRING =
            "\"The fault, dear Brutus, is not in our stars, but in ourselves.\"";
    private static final String TEST_STRING_SHIFTED_BY_20 =
            "\"Nby zuofn, xyul Vlonom, cm hin ch iol mnulm, von ch iolmyfpym.\"";

    @Test
    public void canEncodeEmptyString() {
        String encodedString = CaesarCipher.encode("", 1);

        assertEquals(encodedString, "");
    }

    @Test
    public void canShiftStringByZeroOffset() {
        String encodedString = CaesarCipher.encode("abc", 0);

        assertEquals(encodedString, "abc");
    }

    @Test
    public void canShiftStringByPositiveOffset() {
        String encodedString = CaesarCipher.encode("abc", 1);

        assertEquals(encodedString, "bcd");
    }

    @Test
    public void canShiftStringByNegativeOffset() {
        String encodedString = CaesarCipher.encode("bcd", -1);

        assertEquals(encodedString, "abc");
    }

    @Test
    public void canWrapAroundWithNegativeOffset() {
        String encodedString = CaesarCipher.encode("a", -1);

        assertEquals(encodedString, "z");
    }

    @Test
    public void canWrapAroundWithPositiveOffset() {
        String encodedString = CaesarCipher.encode("z", 1);

        assertEquals(encodedString, "a");
    }

    @Test
    public void canShiftCapitalLettersString() {
        String encodedString = CaesarCipher.encode("ABC", 1);

        assertEquals(encodedString, "BCD");
    }

    @Test
    public void canIgnoreOtherSymbols() {
        String encodedString = CaesarCipher.encode("&^#$!.?", 1);

        assertEquals(encodedString, "&^#$!.?");
    }

    @Test
    public void canDecodeString() {
        String testString = "Test 123";

        String encodedString = CaesarCipher.encode(testString, 1);
        String decodedString = CaesarCipher.decode(encodedString, 1);

        assertEquals(testString, decodedString);
    }

    @Test
    public void canHandleBigPositiveOffset() {
        String encodedString = CaesarCipher.encode("abc", 6828277);

        assertEquals(encodedString, "bcd");
    }

    @Test
    public void canHandleBigNegativeOffset() {
        String encodedString = CaesarCipher.encode("bcd", -6828277);

        assertEquals(encodedString, "abc");
    }

    @Test
    public void canEncodeTestString() {
        String encodedString = CaesarCipher.encode(TEST_STRING, 20);

        assertEquals(encodedString, TEST_STRING_SHIFTED_BY_20);
    }

    @Test
    public void canDecodeTestString() {
        String decodedString = CaesarCipher.decode(TEST_STRING_SHIFTED_BY_20, 20);

        assertEquals(TEST_STRING, decodedString);
    }
}

