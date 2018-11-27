package ru.unn.agile.primenumber.model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

import java.util.List;

public final class PrimeNumberTest {

    @Test
    public void canReceiveOnePrimeValueFromSegmentZeroToTwo() {
        int firstValueSegment = 0;
        int lastValueSegment = 2;
        List<Integer> expectedArray = new ArrayList<>();
        expectedArray.add(2);

        List<Integer> actualArray =
                PrimeNumber.findPrimeNumbersInSegment(firstValueSegment, lastValueSegment);

        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void canReceiveTwoPrimeValueFromIntervalZeroToThree() {
        List<Integer> expectedArray = new ArrayList<>();
        expectedArray.add(2);
        expectedArray.add(3);
        List<Integer> actualArray =
                PrimeNumber.findPrimeNumbersInSegment(0, 3);

        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void canReceiveThreePrimeValueFromIntervalZeroToFive() {
        int firstValueSegment = 0;
        int lastValueSegment = 5;
        List<Integer> expectedArray = new ArrayList<>();
        expectedArray.add(2);
        expectedArray.add(3);
        expectedArray.add(5);

        List<Integer> actualArray =
                PrimeNumber.findPrimeNumbersInSegment(firstValueSegment, lastValueSegment);

        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void canReceiveFourPrimeValueFromIntervalZeroToNine() {
        int firstValueSegment = 0;
        int lastValueSegment = 9;
        List<Integer> expectedArray = new ArrayList<>();
        expectedArray.add(2);
        expectedArray.add(3);
        expectedArray.add(5);
        expectedArray.add(7);

        List<Integer> actualArray =
                PrimeNumber.findPrimeNumbersInSegment(firstValueSegment, lastValueSegment);

        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void canReceiveFourPrimeValueFromIntervalFiveToFifteen() {
        int firstValueSegment = 5;
        int lastValueSegment = 15;
        List<Integer> expectedArray = new ArrayList<>();
        expectedArray.add(5);
        expectedArray.add(7);
        expectedArray.add(11);
        expectedArray.add(13);

        List<Integer> actualArray =
                PrimeNumber.findPrimeNumbersInSegment(firstValueSegment, lastValueSegment);

        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void canReceiveTwoPrimeValueFromIntervalMinusFiveToThree() {
        int firstValueSegment = -5;
        int lastValueSegment = 3;
        List<Integer> expectedArray = new ArrayList<>();
        expectedArray.add(2);
        expectedArray.add(3);

        List<Integer> actualArray =
                PrimeNumber.findPrimeNumbersInSegment(firstValueSegment, lastValueSegment);

        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void canReceiveTwoPrimeValueFromDoubleNumbersInterval() {
        double firstValueSegment = 4.2;
        double lastValueSegment = 7.6;
        List<Integer> expectedArray = new ArrayList<>();
        expectedArray.add(5);
        expectedArray.add(7);

        List<Integer> actualArray =
                PrimeNumber.findPrimeNumbersInSegment(firstValueSegment, lastValueSegment);

        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void canReceiveOnePrimeValueFromDoubleNumbersInterval() {
        double firstValueSegment = 0.99;
        double lastValueSegment = 2.99;
        List<Integer> expectedArray = new ArrayList<>();
        expectedArray.add(2);

        List<Integer> actualArray =
                PrimeNumber.findPrimeNumbersInSegment(firstValueSegment, lastValueSegment);

        assertEquals(expectedArray, actualArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotReceivePrimeValueFromIncorrectInterval() {
        int firstValueSegment = 3;
        int lastValueSegment = -3;

        PrimeNumber.findPrimeNumbersInSegment(firstValueSegment, lastValueSegment);
    }

    @Test
    public void canReceiveOnePrimeValueFromIntervalFiveToFive() {
        int firstValueSegment = 5;
        int lastValueSegment = 5;
        List<Integer> expectedArray = new ArrayList<>();
        expectedArray.add(5);

        List<Integer> actualArray =
                PrimeNumber.findPrimeNumbersInSegment(firstValueSegment, lastValueSegment);

        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void canNortReceivePrimeValueFromIntervalFourToFour() {
        int firstValueSegment = 4;
        int lastValueSegment = 4;
        List<Integer> expectedArray = new ArrayList<>();

        List<Integer> actualArray =
                PrimeNumber.findPrimeNumbersInSegment(firstValueSegment, lastValueSegment);

        assertEquals(expectedArray, actualArray);
    }
}
