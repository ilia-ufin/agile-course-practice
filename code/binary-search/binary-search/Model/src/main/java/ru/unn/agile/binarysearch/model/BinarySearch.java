package ru.unn.agile.binarysearch.model;

public class BinarySearch {

    private int[] array;
    private int size;


    public BinarySearch(final int[] arrayParam) throws BadArrayException {
        if (arrayParam.length != 0) {
            array = arrayParam;
            size = array.length;
        } else {
            throw new BadArrayException("Размер массива равен нулю");
        }
        for (int i = 0; i < size - 1; i++) {
            if (array[i] > array[i + 1]) {
                throw new BadArrayException("Массив не отсортирован");
            }
        }
    }

    public int[] getArray() {
        return array;
    }

    public boolean inLeftHalf(final int key) {
        return  (key < array[size / 2]);
    }

    public boolean inRightHalf(final int key) {
        return (array[size / 2] < key);
    }

    public int search(final int key) {
        int middle;
        int leftBorder = 0;
        int rightBorder = size - 1;
        while ((leftBorder <= rightBorder)) {
            middle = (leftBorder + rightBorder) / 2;
            if (key > array[middle]) {
                leftBorder = middle + 1;
            }
            if (key < array[middle]) {
                rightBorder = middle - 1;
            }
            if (key == array[middle]) {
                return middle;
            }
        }
        return -1;
    }
}
