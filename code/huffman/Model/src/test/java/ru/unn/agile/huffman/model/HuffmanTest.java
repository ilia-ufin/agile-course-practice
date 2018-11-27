package ru.unn.agile.huffman.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class HuffmanTest {
    @Test
    public void canCreateHuffmanWithEmptyString() {
        Huffman code = new Huffman("");
        assertNotNull(code);
    }

    @Test
    public void decodedStringIsEmptyIfHuffmanCreatedWithEmptyString() {
        Huffman code = new Huffman("");
        assertTrue(code.getDecodedString().equals(""));
    }

    @Test
    public void encodedStringIsEmptyIfHuffmanCreatedWithEmptyString() {
        Huffman code = new Huffman("");
        assertTrue(code.getEncodedString().equals(""));
    }

    @Test
    public void decodedStringIsCorrectWith1AlphabeticSymbolInput() {
        Huffman code = new Huffman("a");
        assertEquals(code.getDecodedString(), "a");
    }

    @Test
    public void encodedStringIsCorrectWith1AlphabeticSymbolInput() {
        Huffman code = new Huffman("a");
        assertEquals(code.getEncodedString(), "0");
    }

    @Test
    public void encodedStringIsCorrectWith2NumericSymbolsInput() {
        Huffman code = new Huffman("ab");
        assertEquals(code.getDecodedString(), "ab");
    }

    @Test
    public void encodedStringIsCorrectWith2AlphabeticSymbolsInput() {
        Huffman code = new Huffman("ab");
        assertEquals(code.getEncodedString(), "01");
    }

    @Test
    public void encodedStringIsCorrectWithRepeatedAlphabeticSymbolInput() {
        Huffman code = new Huffman("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        assertEquals(code.getEncodedString(), "000000000000000000000000000000");
    }

    @Test
    public void encodedStringIsCorrectWith10DifferentAlphabeticSymbolsInput() {
        Huffman code = new Huffman("abhfklopmn");
        assertEquals(code.getEncodedString(), "0010111011110010100110111001111000");
    }

    @Test
    public void encodedStringIsCorrectWith10CoupleSymbolsInput() {
        Huffman code = new Huffman("ababababab");
        assertEquals(code.getEncodedString(), "0101010101");
    }

    @Test
    public void encodedStringIsCorrectWith10TripletSymbolsInput() {
        Huffman code = new Huffman("abcabcabcabcabc");
        assertEquals(code.getEncodedString(), "1001110011100111001110011");
    }

    @Test
    public void encodedStringIsCorrectWithAlphabeticSymbolsAndWhitespaceInput() {
        Huffman code = new Huffman("hello world");
        assertEquals(code.getEncodedString(), "01011101010110000111111000110011");
    }

    @Test
    public void encodedStringIsCorrectWithTheSameUpperAndLowerSymbolsInput() {
        Huffman code = new Huffman("Ww");
        assertEquals(code.getEncodedString(), "01");
    }

    @Test
    public void encodedStringIsCorrectWithNumericSymbolsInput() {
        Huffman code = new Huffman("1029384756");
        assertEquals(code.getEncodedString(), "0011100011110111100001011111010100");
    }

    @Test
    public void encodedStringIsCorrectWithNumericAndAlphabeticSymbolsInput() {
        Huffman code = new Huffman("111aaa 9e9e9dm  ke3mm 2333 004040404");
        assertEquals(code.getEncodedString(), "111011101110000000000101110011111100111111001101"
                + "00011011011101111111011001001101110110011011011101100100010100010100010100010");
    }

    @Test
    public void encodedStringIsCorrectWithNumericAndAlphabeticAndSpecialSymbolsInput() {
        Huffman code = new Huffman("!!! dddd ?? dd ? !! ddd333 99999UUUUUcwqdfjdkpw");
        assertEquals(code.getEncodedString(), "0100100101101010101011001110111110101011001111100"
                + "100101101010100110011001101100010010010010010000000000000001"
                + "1110011101111001101111011111101011111111100011101");
    }

    @Test
    public void canCompareTwoTheSameStringsWithTheSameAlphabets() {
        Huffman code1 = new Huffman("170995o0unn");
        Huffman code2 = new Huffman("170995o0unn");
        assertTrue(code1.equals(code2));
    }

    @Test
    public void encodedStringsAreEqualForTwoDifferentStringsWithTheSimilarAlphabets() {
        Huffman code1 = new Huffman("abbb");
        Huffman code2 = new Huffman("baaa");
        assertTrue(code1.getEncodedString().equals(code2.getEncodedString()));
    }

    @Test
    public void equalsIsFalseForTwoDifferentStringsWithTheSameAlphabets() {
        Huffman code1 = new Huffman("abbb");
        Huffman code2 = new Huffman("baaa");
        assertFalse(code1.equals(code2));
    }

    @Test
    public void decodedStringsAreDifferentForTwoDifferentStringsWithDifferentAlphabets() {
        Huffman code1 = new Huffman("abbb");
        Huffman code2 = new Huffman("cabbb");
        assertFalse(code1.getEncodedString().equals(code2.getEncodedString()));
    }

    @Test
    public void canCompareWithNullObject() {
        Huffman code1 = new Huffman("100");
        Huffman code2 = null;
        assertFalse(code1.equals(code2));
    }

    @Test
    public void canCompareWithAnotherObject() {
        Huffman code1 = new Huffman("100");
        Integer num = new Integer(100);
        assertFalse(code1.equals(num));
    }
}
