package ru.unn.agile.binarysearch.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BinarySearchTests {

    @Test
    public void canInitializeBinarySearch() throws BadArrayException {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        BinarySearch binarySearch = new BinarySearch(array);

        assertEquals(array, binarySearch.getArray());
    }

    @Test
    public void isKeyLocateOnTheRightHalfOfTheArray() throws BadArrayException {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int key = 8;
        BinarySearch binarySearch = new BinarySearch(array);

        assertTrue(binarySearch.inRightHalf(key));
    }

    @Test
    public void isKeyLocateOnTheLeftHalfOfTheArray() throws BadArrayException {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int key = 2;
        BinarySearch binarySearch = new BinarySearch(array);

        assertTrue(binarySearch.inLeftHalf(key));
    }

    @Test
    public void canSearchKeyInArray() throws BadArrayException {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int key = 7;
        BinarySearch binarySearch = new BinarySearch(array);

        assertEquals(7, binarySearch.search(key));
    }

    @Test
    public void canSearchLastKeyInArray() throws BadArrayException {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int key = 9;
        BinarySearch binarySearch = new BinarySearch(array);

        assertEquals(9, binarySearch.search(key));
    }

    @Test
    public void canSearchMiddleKeyInArray() throws BadArrayException {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int key = 5;
        BinarySearch binarySearch = new BinarySearch(array);

        assertEquals(4, binarySearch.search(key));
    }

    @Test
    public void canSearchFirstKeyInArray() throws BadArrayException {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int key = 0;
        BinarySearch binarySearch = new BinarySearch(array);

        assertEquals(0, binarySearch.search(key));
    }

    @Test
    public void canNotSearchKeyInArray() throws BadArrayException {
        int[] array = {0, 1, 2, 3, 5, 6, 7, 8};
        int key = 4;
        BinarySearch binarySearch = new BinarySearch(array);

        assertEquals(-1, binarySearch.search(key));
    }

    @Test(expected = BadArrayException.class)
    public void throwsExceptionWithEmptyArray() throws BadArrayException {
        int[] array = {};
        BinarySearch binarySearch = new BinarySearch(array);
    }


    @Test(expected = BadArrayException.class)
    public void throwsExceptionWithUnorderedArray() throws BadArrayException {
        int[] array = {0, 2, 8, 3, 9, 6, 4, 7};
        BinarySearch binarySearch = new BinarySearch(array);
    }
}
