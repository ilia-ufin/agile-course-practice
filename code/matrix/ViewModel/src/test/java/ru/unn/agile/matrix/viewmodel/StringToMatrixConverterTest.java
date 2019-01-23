package ru.unn.agile.matrix.viewmodel;

import org.junit.Test;
import ru.unn.agile.matrix.model.Matrix;

import static org.junit.Assert.*;

public class StringToMatrixConverterTest {
    @Test
    public void emptyStringIsInvalidMatrix() {
        String empty = "";

        assertFalse(StringToMatrixConverter.isValidMatrix(empty));
    }

    @Test
    public void emptyBracketsIsInvalidMatrix() {
        String brackets = "[]";

        assertFalse(StringToMatrixConverter.isValidMatrix(brackets));
    }

    @Test
    public void positiveNumberWithinBracketsIsValidMatrix() {
        String matrix = "[1]";

        assertTrue(StringToMatrixConverter.isValidMatrix(matrix));
    }

    @Test
    public void negativeNumberWithinBracketsIsValidMatrix() {
        String matrix = "[-1]";

        assertTrue(StringToMatrixConverter.isValidMatrix(matrix));
    }

    @Test
    public void stringWithWrongNumberOfBracketsIsInvalidMatrix() {
        String matrix = "[1][";

        assertFalse(StringToMatrixConverter.isValidMatrix(matrix));
    }

    @Test
    public void matrixWithSameNumberOfElementsInRowsIsValid() {
        String matrix = "[1][1]";

        assertTrue(StringToMatrixConverter.isValidMatrix(matrix));
    }

    @Test
    public void matrixWithDifferentNumberOfElementsInRowsIsInvalid() {
        String matrix = "[1][1 1]";

        assertFalse(StringToMatrixConverter.isValidMatrix(matrix));
    }

    @Test
    public void oneRowMatrixWithTwoElementsIsValid() {
        String matrix = "[1 2]";

        assertTrue(StringToMatrixConverter.isValidMatrix(matrix));
    }

    @Test
    public void twoRowsMatrixWithTwoElementsIsValid() {
        String matrix = "[1 2][3 4]";

        assertTrue(StringToMatrixConverter.isValidMatrix(matrix));
    }

    @Test
    public void canExtractOnePositiveIntegerElementMatrixFromString() {
        String str = "[1]";

        Matrix matrix = StringToMatrixConverter.convertToMatrix(str);

        assertEquals("[1.0]", matrix.toString());
    }

    @Test
    public void canExtractOneNegativeIntegerElementMatrixFromString() {
        String str = "[-1]";

        Matrix matrix = StringToMatrixConverter.convertToMatrix(str);

        assertEquals("[-1.0]", matrix.toString());
    }

    @Test
    public void canExtractOneFloatElementMatrixFromString() {
        String str = "[1.0]";

        Matrix matrix = StringToMatrixConverter.convertToMatrix(str);

        assertEquals("[1.0]", matrix.toString());
    }

    @Test
    public void canExtractOneRowWithTwoElementsMatrixFromString() {
        String str = "[1 2]";

        Matrix matrix = StringToMatrixConverter.convertToMatrix(str);

        assertEquals("[1.0 2.0]", matrix.toString());
    }

    @Test
    public void canExtractTwoRowsWithTwoElementsMatrixFromString() {
        String str = "[1 2][3 4]";

        Matrix matrix = StringToMatrixConverter.convertToMatrix(str);

        assertEquals("[1.0 2.0][3.0 4.0]", matrix.toString());
    }

    @Test
    public void canReportSameSizedMatrices() {
        String str1 = "[1]";
        String str2 = "[2]";

        assertTrue(StringToMatrixConverter.areSameSized(str1, str2));
    }

    @Test
    public void canReportDifferentSizedMatrices() {
        String str1 = "[1 2]";
        String str2 = "[3]";

        assertFalse(StringToMatrixConverter.areSameSized(str1, str2));
    }

    @Test
    public void twoOneElementMatricesAreSizeCompatibleForMultiplication() {
        String str1 = "[1]";
        String str2 = "[2]";

        assertTrue(StringToMatrixConverter.areSizeCompatibleForMultiplication(str1, str2));
    }

    @Test
    public void twoColumnMatrixAndTwoRowsMatrixAreSizeCompatibleForMultiplication() {
        String str1 = "[1 2]";
        String str2 = "[3][4]";

        assertTrue(StringToMatrixConverter.areSizeCompatibleForMultiplication(str1, str2));
    }

    @Test
    public void twoColumnMatrixAndOneElementMatrixAreSizeIncompatibleForMultiplication() {
        String str1 = "[1 2]";
        String str2 = "[3]";

        assertFalse(StringToMatrixConverter.areSizeCompatibleForMultiplication(str1, str2));
    }
}
