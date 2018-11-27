package ru.unn.agile.calculator.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.calculator.model.RadixCalculator;
import ru.unn.agile.calculator.model.NumeralSystem;

import static org.junit.Assert.assertEquals;


public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
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

        assertEquals(expectedResult, viewModel.getResult());
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

        assertEquals(expectedResult, viewModel.getResult());
    }


    @Test
    public void testCalculationDisabledWithEmptyInput() {
        String a = "";
        String b = "b10";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);
        viewModel.outputNumberSystemProperty().setValue(NumeralSystem.OCTAL);

        assertEquals(true, viewModel.isCalculationDisabled());
        assertEquals(UserMessages.WAIT_FOR_INPUT.toString(), viewModel.getUserMessage());
    }

    @Test
    public void testCalculationEnabledWithRightInput() {
        String a = "x10";
        String b = "b1111";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);
        viewModel.outputNumberSystemProperty().setValue(NumeralSystem.OCTAL);

        assertEquals(false, viewModel.isCalculationDisabled());
        assertEquals(UserMessages.READY.toString(), viewModel.getUserMessage());
    }


    @Test
    public void testCalculationEnabledWithTrashInput() {
        String a = "fasfdasdfasf";
        String b = "bfafssafdadfd";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);

        assertEquals(true, viewModel.isCalculationDisabled());
        assertEquals(UserMessages.INPUT_INVALID.toString(), viewModel.getUserMessage());
    }


    @Test
    public void testStatusAfterCalculate() {
        String a = "xc";
        String b = "o5";

        viewModel.number1Property().setValue(a);
        viewModel.number2Property().setValue(b);
        viewModel.calculate();

        assertEquals(false, viewModel.isCalculationDisabled());
        assertEquals(UserMessages.SUCCESS.toString(), viewModel.getUserMessage());
    }


}
