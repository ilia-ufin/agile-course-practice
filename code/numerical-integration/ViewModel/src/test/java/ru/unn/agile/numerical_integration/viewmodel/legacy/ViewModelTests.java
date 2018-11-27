package ru.unn.agile.numerical_integration.ViewModel.legacy;

import org.junit.Test;


import static org.junit.Assert.*;

public class ViewModelTests {
    @Test
    public void canCreateDefaultModel() {
        ViewModel model = new ViewModel();

        assertEquals("x", model.getFunctionText());
        assertEquals("0.0", model.getLeftBorderValue());
        assertEquals("1.0", model.getRightBorderValue());
        assertEquals("1", model.getSplitsNumber());
        assertEquals("", model.getOutputMessage());
        assertEquals(true, model.canComputeFunction());
    }

    // Left border tests

    @Test
    public void canSetLeftBorderValueWithPositiveIntegerNumber() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("10");

        assertEquals("10", model.getLeftBorderValue());
    }

    @Test
    public void canSetLeftBorderValueWithExplicitlyPositiveIntegerNumber() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("+10");

        assertEquals("+10", model.getLeftBorderValue());
    }

    @Test
    public void canSetLeftBorderValueWithNegativeIntegerNumber() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("-10");

        assertEquals("-10", model.getLeftBorderValue());
    }

    @Test
    public void canSetLeftBorderValueWithFloatingPointNumber() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("-12.0432");

        assertEquals("-12.0432", model.getLeftBorderValue());
    }

    @Test
    public void canSetLeftBorderValueWithShortFloatingPointNumber() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("-.023897");

        assertEquals("-.023897", model.getLeftBorderValue());
    }

    @Test
    public void canSetLeftBorderValueWithWrongShortFloatingPointNumber() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("-.");

        assertEquals("-.", model.getLeftBorderValue());
    }

    @Test
    public void whenSetLeftBorderValueWithWrongShortFloatingPointNumberErrorIsShown() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("-.");

        assertTrue(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_LEFT_BORDER));
    }

    @Test
    public void whenSetLeftBorderValueWithWrongShortFloatingPointNumberCannotCompute() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("-.");

        assertFalse(model.canComputeFunction());
    }

    @Test
    public void canSetLeftBorderValueWithNotNumber() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("sak");

        assertEquals("sak", model.getLeftBorderValue());
    }

    @Test
    public void whenSetLeftBorderValueWithNotNumberErrorIsShown() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("sak");

        assertTrue(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_LEFT_BORDER));
    }

    @Test
    public void whenSetLeftBorderValueWithNotNumberCannotCompute() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("sak");

        assertFalse(model.canComputeFunction());
    }

    @Test
    public void whenLeftBorderValueIsFixedItsErrorLeaves() {
        ViewModel model = new ViewModel();

        model.setLeftBorderValue("sak");
        model.setLeftBorderValue("1");

        assertFalse(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_LEFT_BORDER));
    }

    // Right border

    @Test
    public void canSetRightBorderValueWithPositiveIntegerNumber() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("10");

        assertEquals("10", model.getRightBorderValue());
    }

    @Test
    public void canSetRightBorderValueWithExplicitlyPositiveIntegerNumber() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("+10");

        assertEquals("+10", model.getRightBorderValue());
    }

    @Test
    public void canSetRightBorderValueWithNegativeIntegerNumber() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("-10");

        assertEquals("-10", model.getRightBorderValue());
    }

    @Test
    public void canSetRightBorderValueWithFloatingPointNumber() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("-12.0432");

        assertEquals("-12.0432", model.getRightBorderValue());
    }

    @Test
    public void canSetRightBorderValueWithShortFloatingPointNumber() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("-.023897");

        assertEquals("-.023897", model.getRightBorderValue());
    }

    @Test
    public void canSetRightBorderValueWithWrongShortFloatingPointNumber() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("-.");

        assertEquals("-.", model.getRightBorderValue());
    }

    @Test
    public void whenSetRightBorderValueWithWrongShortFloatingPointNumberErrorIsShown() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("-.");

        assertTrue(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_RIGHT_BORDER));
    }

    @Test
    public void whenSetRightBorderValueWithWrongShortFloatingPointNumberCannotCompute() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("-.");

        assertFalse(model.canComputeFunction());
    }

    @Test
    public void canSetRightBorderValueWithNotNumber() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("sak");

        assertEquals("sak", model.getRightBorderValue());
    }

    @Test
    public void whenSetRightBorderValueWithNotNumberErrorIsShown() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("sak");

        assertTrue(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_RIGHT_BORDER));
    }

    @Test
    public void whenSetRightBorderValueWithNotNumberCannotCompute() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("sak");

        assertFalse(model.canComputeFunction());
    }

    @Test
    public void whenRightBorderValueIsFixedItsErrorLeaves() {
        ViewModel model = new ViewModel();

        model.setRightBorderValue("sak");
        model.setRightBorderValue("1");

        assertFalse(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_RIGHT_BORDER));
    }

    // Splits Number tests

    @Test
    public void canSetSplitsNumberToPositiveIntegerValue() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("100");

        assertEquals("100", model.getSplitsNumber());
    }

    @Test
    public void canSetSplitsNumberToNegativeIntegerValue() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("-100");

        assertEquals("-100", model.getSplitsNumber());
    }

    @Test
    public void whenTryingToSetSplitsNumberToNegativeIntegerValueErrorIsShown() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("-100");

        assertTrue(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_SPLITS_NUMBER));
    }

    @Test
    public void whenTryingToSetSplitsNumberToNegativeIntegerValueCannotCompute() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("-100");

        assertFalse(model.canComputeFunction());
    }

    @Test
    public void canSetSplitsNumberToZero() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("0");

        assertEquals("0", model.getSplitsNumber());
    }

    @Test
    public void whenTryingToSetSplitsNumberToZeroErrorIsShown() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("0");

        assertTrue(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_SPLITS_NUMBER));
    }

    @Test
    public void whenTryingToSetSplitsNumberToZeroCannotCompute() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("0");

        assertFalse(model.canComputeFunction());
    }

    @Test
    public void canSetSplitsNumberToNaN() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("df");

        assertEquals("df", model.getSplitsNumber());
    }

    @Test
    public void whenTryingToSetSplitsNumberToNaNErrorIsShown() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("fg");

        assertTrue(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_SPLITS_NUMBER));
    }

    @Test
    public void whenTryingToSetSplitsNumberToNaNCannotCompute() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("sfdg");

        assertFalse(model.canComputeFunction());
    }

    @Test
    public void whenSplitsNumberValueIsFixedErrorLeaves() {
        ViewModel model = new ViewModel();

        model.setSplitsNumber("sfdg");
        model.setSplitsNumber("10");

        assertTrue(model.canComputeFunction());
    }

    // Function tests

    @Test
    public void canSetFunctionWithCorrectValue() {
        ViewModel model = new ViewModel();

        model.setFunction("x");

        assertEquals("x", model.getFunctionText());
    }

    @Test
    public void canSetFunctionWithIncorrectValue() {
        ViewModel model = new ViewModel();

        model.setFunction("hello world");

        assertEquals("hello world", model.getFunctionText());
    }

    @Test
    public void whenTryingToSetFunctionWithIncorrectValueErrorIsShown() {
        ViewModel model = new ViewModel();

        model.setFunction("hello world");

        assertTrue(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_FUNCTION_TEXT));
    }

    @Test
    public void whenTryingToSetFunctionWithIncorrectValueCannotCompute() {
        ViewModel model = new ViewModel();

        model.setFunction("hello world");

        assertFalse(model.canComputeFunction());
    }

    @Test
    public void whenFunctionIsFixedErrorLeaves() {
        ViewModel model = new ViewModel();

        model.setFunction("hello world");
        model.setFunction("2 + x");

        assertFalse(model.getOutputMessage().contains(
            ViewModel.ERROR_WRONG_FUNCTION_TEXT));
    }

    // Compute button tests

    @Test
    public void canComputeWhenEverythingIsCorrect() {
        ViewModel model = new ViewModel();

        model.setFunction("x*x");
        model.setLeftBorderValue("-2");
        model.setRightBorderValue("2");
        model.setSplitsNumber("10");

        assertTrue(model.canComputeFunction());
    }

    @Test
    public void isComputationCorrectWhenEverythingIsCorrect() {
        ViewModel model = new ViewModel();
        model.setFunction("3*x*x");
        model.setLeftBorderValue("-2");
        model.setRightBorderValue("2");
        model.setSplitsNumber("10000");

        model.compute();
        double result = Double.parseDouble(model.getOutputMessage());

        assertEquals(16.0, result, 1e-5);
    }

    @Test
    public void whenCanNotComputeComputationDoesNothing() {
        ViewModel model = new ViewModel();
        model.setFunction(")");
        model.setLeftBorderValue("-2");
        model.setRightBorderValue("2");
        model.setSplitsNumber("1");

        String outputBeforeCompute = model.getOutputMessage();
        model.compute();
        String outputAfterCompute = model.getOutputMessage();

        assertTrue(outputBeforeCompute.equals(outputAfterCompute));
    }

    @Test
    public void whenHasMultipleErrorsTheyAreSeparatedByLines() {
        ViewModel model = new ViewModel();
        model.setLeftBorderValue("q");
        model.setRightBorderValue("q");

        assertTrue(model.getOutputMessage().contains("\n"));
    }
}
