package ru.unn.agile.caesarcipher.viewmodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ViewModelTests {
    @Test
    public void canRecognizeThatButtonIsDisabled() {
        ViewModel viewModel = new ViewModel();
        assertFalse(viewModel.isCodeButtonEnabled());
    }

    @Test
    public void canRecognizeThatButtonIsEnabledWhenEnter() {
        ViewModel viewModel = new ViewModel();

        viewModel.setTextBoxs("ABC", "2");

        assertTrue(viewModel.isCodeButtonEnabled());
    }

    @Test
    public void canDisableButtonIfAllDataHasBeenEntered() {
        ViewModel viewModel = new ViewModel();

        viewModel.setTextBoxs("ABC", "");

        assertFalse(viewModel.isCodeButtonEnabled());
    }

    @Test
    public void canEnableButtonWhenClearInputTextBox() {
        ViewModel viewModel = new ViewModel();

        viewModel.setTextBoxs("ABC", "");
        viewModel.setTextBoxs("", "");

        assertFalse(viewModel.isCodeButtonEnabled());
    }

    @Test
    public void canEnableButtonWhenClearOffsetTextBox() {
        ViewModel viewModel = new ViewModel();

        viewModel.setTextBoxs("", "1");
        viewModel.setTextBoxs("", "");

        assertFalse(viewModel.isCodeButtonEnabled());
    }

    @Test
    public void canOffsetByGivenNumber() {
        ViewModel viewModel = new ViewModel();

        viewModel.setTextBoxs("ABC", "1");
        viewModel.codeCaesar();

        assertEquals("BCD", viewModel.getCaesarCipher());
    }

    @Test
    public void canDisplayErrorWithIncompleteData() {
        ViewModel viewModel = new ViewModel();

        viewModel.setTextBoxs("ABC", "");

        assertEquals("Input correct value", viewModel.getStatus());
    }

    @Test
    public void canDisplayErrorWithIncorrectDatas() {
        ViewModel viewModel = new ViewModel();

        viewModel.setTextBoxs("ABC", "abc");

        assertEquals("Input correct value", viewModel.getStatus());
    }

    @Test
    public void canDisplayStatus() {
        ViewModel viewModel = new ViewModel();

        viewModel.setTextBoxs("ABC", "1");

        assertEquals("Correct", viewModel.getStatus());
    }
}
