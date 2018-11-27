package ru.unn.agile.arraysorter.model;

public final class ArraySorter {
    public static <T extends Comparable<T>>
    void sort(final T[] array) {
        QuickSorter<T> sorter = new QuickSorter<T>();
        sorter.sort(array);
    }

    private ArraySorter() {
    }

    private static final class QuickSorter<T extends Comparable<T>> {
        public static final int MIN_ARRAY_ELEMENTS_COUNT = 1;

        void sort(final T[] array) {
            validateArray(array);

            int startElementIndex = 0;
            int endElementIndex = array.length - 1;
            sortSubArray(array, startElementIndex, endElementIndex);
        }

        private void validateArray(final T[] array) {
            if (array == null) {
                throw new IllegalArgumentException("Array should be initialized");
            }

            if (array.length < MIN_ARRAY_ELEMENTS_COUNT) {
                throw new IllegalArgumentException("Array elements count should be positive");
            }

            for (int i = 0; i < array.length; ++i) {
                if (array[i] == null) {
                    throw new IllegalArgumentException("Array shouldn't contain null elements");
                }
            }
        }

        private void sortSubArray(final T[] array,
                                  final int startSubArrayIndex,
                                  final int endSubArrayIndex) {
            T bearingElement = calculateBearingElement(array, startSubArrayIndex, endSubArrayIndex);

            int currentLeftBound = startSubArrayIndex;
            int currentRightBound = endSubArrayIndex;
            while (currentLeftBound <= currentRightBound) {
                while (array[currentLeftBound].compareTo(bearingElement) < 0) {
                    ++currentLeftBound;
                }

                while (array[currentRightBound].compareTo(bearingElement) > 0) {
                    --currentRightBound;
                }

                if (currentLeftBound <= currentRightBound) {
                    swapTwoElementsInArray(array, currentLeftBound, currentRightBound);
                    ++currentLeftBound;
                    --currentRightBound;
                }
            }

            if (currentRightBound > startSubArrayIndex) {
                sortSubArray(array, startSubArrayIndex, currentRightBound);
            }
            if (currentLeftBound < endSubArrayIndex) {
                sortSubArray(array, currentLeftBound, endSubArrayIndex);
            }
        }

        private T calculateBearingElement(final T[] array,
                                          final int firstElementIndex,
                                          final int secondElementIndex) {
            return array[(firstElementIndex + secondElementIndex) / 2];
        }

        private void swapTwoElementsInArray(final T[] array,
                                            final int firstElementIndex,
                                            final int secondElementIndex) {
            T tmp = array[firstElementIndex];
            array[firstElementIndex] = array[secondElementIndex];
            array[secondElementIndex] = tmp;
        }
    }
}
