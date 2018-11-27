package ru.unn.agile.numbersinwords.viewmodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NumbersInWordsViewModelTests {
    @Test
    public void isConvertButtonDisabledByDefault() {
        NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();

        assertFalse(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isConvertButtonEnabledWhenEnterNumber() {
        NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();

        viewModel.setNumber("1");

        assertTrue(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isConvertButtonDisabledAfterNumberIsCleared() {
        NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();

        viewModel.setNumber("1");
        viewModel.setNumber("");

        assertFalse(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void isOneDisplayedWhenConverting1() {
        NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();

        viewModel.setNumber("1");
        viewModel.convert();

        assertEquals("One", viewModel.getNumberInWords());
    }

    @Test
    public void isTextDeletedWhenChangingNumbers() {
        NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();

        viewModel.setNumber("1");
        viewModel.convert();
        viewModel.setNumber("2");

        assertEquals("", viewModel.getNumberInWords());
    }

    @Test
    public void isConvertButtonDisabledWhenInvalidNumberWasEntered() {
        NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();

        viewModel.setNumber("a");

        assertFalse(viewModel.isConvertButtonEnabled());
    }

    @Test
    public void canShowErrorMessageWhenInvalidNumberWasEntered() {
        NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();

        viewModel.setNumber("a");

        assertEquals("Only digits, please", viewModel.getErrorMessage());
    }

    @Test
    public void canHideErrorMessageWhenInvalidNumberWasCorrected() {
        NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();

        viewModel.setNumber("a");
        viewModel.setNumber("1");

        assertEquals("", viewModel.getErrorMessage());
    }

    @Test
    public void canHideErrorMessageWhenInvalidNumberWaCleaned() {
        NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();

        viewModel.setNumber("a");
        viewModel.setNumber("");

        assertEquals("", viewModel.getErrorMessage());
    }

    @Test
    public void isInputDigitsCountIsLimited() {
        NumbersInWordsViewModel viewModel = new NumbersInWordsViewModel();

        viewModel.setNumber("1111111111111");

        assertEquals("You can enter up to 12 digits", viewModel.getErrorMessage());
    }
}
