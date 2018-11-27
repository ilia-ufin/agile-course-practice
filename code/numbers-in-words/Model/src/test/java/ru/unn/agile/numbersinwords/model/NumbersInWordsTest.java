package ru.unn.agile.numbersinwords.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumbersInWordsTest {

    @Test
    public void canConvertZero() {
        String convertString = NumbersInWords.convert(0);

        assertEquals("zero", convertString);
    }

    @Test
    public void canConvertTheTopTenNumbers() {
        String convertString = NumbersInWords.convert(6);

        assertEquals("Six", convertString);
    }

    @Test
    public void canConvertNumbersToHundreds() {
        String convertString = NumbersInWords.convert(54);

        assertEquals("Fifty Four", convertString);
    }

    @Test
    public void canConvertMultiplesOfTen() {
        String convertString = NumbersInWords.convert(80);

        assertEquals("Eighty", convertString);
    }
    @Test
    public void canConvertMultiplesOfHundred() {
        String convertString = NumbersInWords.convert(200);

        assertEquals("Two hundred", convertString);
    }

    @Test
    public void canConvertLessThousand() {
        String convertString = NumbersInWords.convert(115);

        assertEquals("One hundred Fifteen", convertString);
    }

    @Test
    public void canConvertMultiplesOfThousand() {
        String convertString = NumbersInWords.convert(3000);

        assertEquals("Three thousand", convertString);
    }

    @Test
    public void canConvertLessMillion() {
        String convertString = NumbersInWords.convert(29076);

        assertEquals("Twenty Nine thousand Seventy Six", convertString);
    }

    @Test
    public void canConvertMultiplesOfMillion() {
        String convertString = NumbersInWords.convert(9000000);

        assertEquals("Nine million", convertString);
    }

    @Test
    public void canConvertLessBillion() {
        String convertString = NumbersInWords.convert(7832504);

        assertEquals("Seven million Eight hundred Thirty Two thousand Five hundred Four",
                convertString);
    }

    @Test
    public void canConvertMultiplesOfBillion() {
        String convertString = NumbersInWords.convert(1000000000);

        assertEquals("One billion", convertString);
    }

    @Test
    public void canConvertMoreBillion() {
        String convertString = NumbersInWords.convert(2000005600);

        assertEquals("Two billion Five thousand Six hundred",
                convertString);
    }
}
