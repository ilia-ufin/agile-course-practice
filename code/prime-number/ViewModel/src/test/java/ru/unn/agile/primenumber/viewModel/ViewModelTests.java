package ru.unn.agile.primenumber.viewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.primenumber.model.PrimeNumber;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

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
    public void isSearchButtonDisableByDefault() {
        assertEquals(true, viewModel.searchDisabledProperty().get());
    }

    @Test
    public void isSearchButtonEnabledWhenInputOnlyLeftBound() {
        viewModel.setLeftBound("1");

        assertEquals(true, viewModel.searchDisabledProperty().get());
    }

    @Test
    public void isSearchButtonEnabledWhenInputOnlyRightBound() {
        viewModel.setRightBound("2");

        assertEquals(true, viewModel.searchDisabledProperty().get());
    }

    @Test
    public void isSearchButtonEnabledWhenInputNotNumberInLeftBound() {
        viewModel.setLeftBound("a");
        viewModel.setRightBound("2");

        assertEquals(true, viewModel.searchDisabledProperty().get());
    }

    @Test
    public void isStatusOfEmptyValueRight() {
        viewModel.setRightBound("");

        viewModel.searchPrimeNumber();

        assertEquals("empty String", viewModel.getStatus());
    }

    @Test
    public void isStatusOfEmptyValueLeft() {
        viewModel.searchPrimeNumber();

        assertEquals("empty String", viewModel.getStatus());
    }

    @Test
    public void isSearchButtonEnabledWhenInputNotNumberInRightBound() {
        viewModel.setLeftBound("1");
        viewModel.setRightBound("b");

        assertEquals(true, viewModel.searchDisabledProperty().get());
    }

    @Test
    public void isStatusCorrectOnInvalidRightValue() {
        viewModel.setLeftBound("1");
        viewModel.setRightBound("b");

        viewModel.searchDisabledProperty().get();

        assertEquals("Input values are invalid", viewModel.getStatus());
    }

    @Test
    public void isStatusCorrectOnInvalidLeftValue() {
        viewModel.setLeftBound("zxc");
        viewModel.setRightBound("12");

        viewModel.searchDisabledProperty().get();

        assertEquals("Input values are invalid", viewModel.getStatus());
    }

    @Test
    public void isStatusCorrectOnInvalidBothValue() {
        viewModel.setLeftBound("a");
        viewModel.setRightBound("b");

        viewModel.searchDisabledProperty().get();

        assertEquals("Input values are invalid", viewModel.getStatus());
    }

    @Test
    public void testConstructorIsPrivate()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<PrimeNumber> constructor = PrimeNumber.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void isSearchButtonEnabledWhenBoundersInput() {
        viewModel.setLeftBound("1");
        viewModel.setRightBound("2");

        assertEquals(false, viewModel.searchDisabledProperty().get());
    }

    @Test
    public void isResultOfSearchCorrect() {
        viewModel.setLeftBound("1");
        viewModel.setRightBound("10");

        viewModel.searchPrimeNumber();

        assertEquals("Success", viewModel.getStatus());
    }

    @Test
    public void isStatusOfSearchCorrect() {
        viewModel.setLeftBound("1");
        viewModel.setRightBound("10");

        viewModel.searchPrimeNumber();

        assertEquals("2, 3, 5, 7", viewModel.getResult());
    }

    @Test
    public void isStatusInvalidValueCorrect() {
        viewModel.setLeftBound("10");
        viewModel.setRightBound("1");

        viewModel.searchPrimeNumber();

        assertEquals("Last value must be more or equal to first value!", viewModel.getStatus());
    }

    @Test
    public void isResultOfEqualsValueCorrect() {
        viewModel.setLeftBound("2");
        viewModel.setRightBound("2");

        viewModel.searchPrimeNumber();

        assertEquals("2", viewModel.getResult());
    }

    @Test
    public void isStatusOfNotFoundNumbersCorrect() {
        viewModel.setLeftBound("14");
        viewModel.setRightBound("16");

        viewModel.searchPrimeNumber();

        assertEquals("Primary numbers are not found", viewModel.getResult());
    }

    @Test
    public void isStatusCorrectOnNegativeValue() {
        viewModel.setLeftBound("-1");
        viewModel.setRightBound("10");

        viewModel.searchPrimeNumber();

        assertEquals("2, 3, 5, 7", viewModel.getResult());
    }

    @Test
    public void isSetStatusCorrect() {
        viewModel.setStatus("Success");

        assertEquals("Success", viewModel.getStatus());

    }

    @Test
    public void isResultPropertyCorrect() {
        viewModel.setLeftBound("1");
        viewModel.setRightBound("2");

        viewModel.searchPrimeNumber();

        assertEquals("2", viewModel.resultProperty().get());
    }

    @Test
    public void isGetRightBoundCorrect() {
        viewModel.setRightBound("2");

        viewModel.searchPrimeNumber();

        assertEquals("2", viewModel.getRightBound());
    }

    @Test
    public void isGetLeftBoundCorrect() {
        viewModel.setLeftBound("2");

        viewModel.searchPrimeNumber();

        assertEquals("2", viewModel.getLeftBound());
    }

}
