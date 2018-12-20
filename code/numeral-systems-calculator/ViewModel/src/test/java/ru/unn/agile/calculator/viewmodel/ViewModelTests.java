package ru.unn.agile.calculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.calculator.model.NumeralSystem;
import ru.unn.agile.calculator.model.RadixCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ViewModelTests {
    private ViewModel viewModel;

    protected void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        viewModel = new ViewModel();
        viewModel.setLogger(new DummyLogger());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals(NumeralSystem.BINARY, viewModel.getOutputNumberSystem());
        assertEquals("", viewModel.getResult());
        assertEquals(UserMessages.WAIT_FOR_INPUT.toString(), viewModel.getUserMessage());
        assertEquals(true, viewModel.isCalculationDisabled());
        assertEquals("", viewModel.number1Property().get());
        assertEquals("", viewModel.number2Property().get());
    }

    @Test
    public void testDefaultCalculate() {
        String a = "b11";
        String b = "b10";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);
        viewModel.calculate();

        String expectedResult = "Sum: a+b = "
                + RadixCalculator.add(a, b, NumeralSystem.BINARY) + "\n"
                + "Mult: a*b = "
                + RadixCalculator.multiply(a, b, NumeralSystem.BINARY) + "\n"
                + "Minus: -a = "
                + RadixCalculator.unaryMinus(a, NumeralSystem.BINARY)
                + ", -b = "
                + RadixCalculator.unaryMinus(b, NumeralSystem.BINARY) + "\n";

        assertEquals(expectedResult, viewModel.resultProperty().get());
    }


    @Test
    public void testCalculateWithOctalOutput() {
        String a = "x10";
        String b = "b1111";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);
        viewModel.outputNumberSystemProperty().setValue(NumeralSystem.OCTAL);
        viewModel.calculate();
        String expectedResult = "Sum: a+b = "
                + RadixCalculator.add(a, b, NumeralSystem.OCTAL) + "\n"
                + "Mult: a*b = "
                + RadixCalculator.multiply(a, b, NumeralSystem.OCTAL) + "\n"
                + "Minus: -a = "
                + RadixCalculator.unaryMinus(a, NumeralSystem.OCTAL)
                + ", -b = "
                + RadixCalculator.unaryMinus(b, NumeralSystem.OCTAL) + "\n";

        assertEquals(expectedResult, viewModel.resultProperty().get());
    }


    @Test
    public void testCalculationDisabledWithEmptyInput() {
        String a = "";
        String b = "b10";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);
        viewModel.outputNumberSystemProperty().setValue(NumeralSystem.OCTAL);

        assertEquals(true, viewModel.isCalculationDisabled());
        assertEquals(UserMessages.WAIT_FOR_INPUT.toString(), viewModel.userMessageProperty().get());
    }

    @Test
    public void testCalculationEnabledWithRightInput() {
        String a = "x10";
        String b = "b1111";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);
        viewModel.outputNumberSystemProperty().setValue(NumeralSystem.OCTAL);

        assertEquals(false, viewModel.isCalculationDisabled());
        assertEquals(UserMessages.READY.toString(), viewModel.userMessageProperty().get());
    }


    @Test
    public void testCalculationEnabledWithTrashInput() {
        String a = "fasfdasdfasf";
        String b = "bfafssafdadfd";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);

        assertEquals(true, viewModel.isCalculationDisabled());
        assertEquals(UserMessages.INPUT_INVALID.toString(), viewModel.userMessageProperty().get());
    }


    @Test
    public void testStatusAfterCalculate() {
        String a = "xc";
        String b = "o5";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);
        viewModel.calculate();

        assertEquals(false, viewModel.isCalculationDisabled());
        assertEquals(UserMessages.SUCCESS.toString(), viewModel.userMessageProperty().get());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSetNullLoggerThenThrowException() {
        viewModel.setLogger(null);
    }

    @Test
    public void whenGetLogThenLogIsEmpty() {
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void whenSetNumbersThenLogContainsMessage() {
        String a = "b11";
        String b = "b10";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);

        String log = viewModel.logProperty().get();
        assertTrue(log.contains(String.format(ViewModel.LOG_VALUES_CHANGED, a, b)));
    }

    @Test
    public void whenSetNumbersAndCalculateThenLogContainsMessage() {
        String a = "b11";
        String b = "b10";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);
        viewModel.calculate();

        assertTrue(viewModel.logProperty().get().contains(ViewModel.LOG_CALCULATED));
    }
}
