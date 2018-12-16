package ru.unn.agile.matrix.viewmodel;

import ru.unn.agile.matrix.model.Matrix;

import java.util.Arrays;

public final class StringToMatrixConverter {
    public static boolean isValidMatrix(final String str) {
        if (str.isEmpty()) {
            return false;
        }

        String numberPattern = "(\\d+|-\\d+)";
        String rowPattern = "\\[" + numberPattern + "( +" + numberPattern + ")*\\]";
        String matrixPattern = rowPattern + "(" + rowPattern + ")*";

        if (!str.replaceAll(matrixPattern, "").isEmpty()) {
            return false;
        }

        String[] rows = str.replaceAll("\\[", "").split("\\]");
        return Arrays.stream(rows)
                .map(s -> s.length() - s.replaceAll(" ", "").length())
                .distinct()
                .count() == 1;
    }

    public static Matrix convertToMatrix(final String str) {
        int rows = getRowsCount(str);
        int columns = getColumnsCount(str);
        Matrix matrix = new Matrix(rows, columns);

        int offset = 0;
        for (int i = 0; i < rows; ++i) {
            int begin = str.indexOf("[", offset);
            int end = str.indexOf("]", offset);
            String row = str.substring(begin + 1, end);

            String[] floatStrings = row.split(" ");
            for (int j = 0; j < floatStrings .length; j++) {
                matrix.setVal(i, j, Float.parseFloat(floatStrings[j]));
            }

            offset = end + 1;
        }

        return  matrix;
    }

    public static boolean areSameSized(final String matrix1, final String matrix2) {
        int rows1 = getRowsCount(matrix1);
        int rows2 = getRowsCount(matrix2);
        int columns1 = getColumnsCount(matrix1);
        int columns2 = getColumnsCount(matrix2);

        return (rows1 == rows2) && (columns1 == columns2);
    }

    public static boolean areSizeCompatibleForMultiplication(
            final String matrix1, final String matrix2) {
        int columns1 = getColumnsCount(matrix1);
        int rows2 = getRowsCount(matrix2);
        return columns1 == rows2;
    }

    private static int getRowsCount(final String matrix) {
        return matrix.length() - matrix.replace("[", "").length();
    }

    private static int getColumnsCount(final String matrix) {
        String[] rows = matrix.replaceAll("\\[", "").split("\\]");
        return rows[0].length() - rows[0].replaceAll(" ", "").length() + 1;
    }

    private StringToMatrixConverter() { }
}
