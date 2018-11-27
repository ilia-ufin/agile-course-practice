package ru.unn.agile.romannumberconverter.viewmodel;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

public class ViewModelTest {
    private ViewModel viewModel;

    @Before
    public void createViewModel() {
        viewModel = new ViewModel();
    }

    @After
    public void deleteViewModel() {
        viewModel = null;
    }

    @Test
    public void initViewModelWithDefaultValues() {
        assertEquals("", viewModel.getArabicValueStr());
        assertEquals("", viewModel.getRomanValueStr());
    }

    @Test
    public void canArabicNumberFieldBeSetted() {
        viewModel.setArabicValueStr("1");

        assertEquals("1", viewModel.getArabicValueStr());
    }

    @Test
    public void canRomanNumberFieldBeSetted() {
        viewModel.setRomanValueStr("1");

        assertEquals("1", viewModel.getRomanValueStr());
    }

    @Test
    public void canConvertArabicToRomanValidValue() {
        viewModel.setArabicValueStr("11");

        viewModel.convertArabicToRoman();

        assertEquals("XI", viewModel.getRomanValueStr());
    }

    @Test
    public void canConvertArabicToRomanInvalidValue() {
        viewModel.setArabicValueStr("test");

        viewModel.convertArabicToRoman();

        assertEquals("Арабское число введено неверно!",
                viewModel.getConvertStatus());
    }

    @Test
    public void canConvertRomanToArabicValidValue() {
        viewModel.setRomanValueStr("IX");

        viewModel.convertRomanToArabic();

        assertEquals("9", viewModel.getArabicValueStr());
    }

    @Test
    public void canConvertRomanToArabicInvalidValue() {
        viewModel.setRomanValueStr("test");

        viewModel.convertRomanToArabic();

        assertEquals("Римское число введено неверно!",
                viewModel.getConvertStatus());
    }

    @Test
    public void canConvertOutOfRangeTopArabicValue() {
        viewModel.setArabicValueStr("4025");

        viewModel.convertArabicToRoman();

        assertEquals("Введено число не из интервала от 1 до 3999!",
                viewModel.getConvertStatus());
    }

    @Test
    public void canConvertOutOfRangeBottomArabicValue() {
        viewModel.setArabicValueStr("-65");

        viewModel.convertArabicToRoman();

        assertEquals("Введено число не из интервала от 1 до 3999!",
                viewModel.getConvertStatus());
    }

    @Test
    public void canConvertEmptyArabicValue() {
        viewModel.setArabicValueStr("");

        viewModel.convertArabicToRoman();

        assertEquals("Арабское число введено неверно!",
                viewModel.getConvertStatus());
    }

    @Test
    public void canConvertEmptyRomanValue() {
        viewModel.setRomanValueStr("");

        viewModel.convertRomanToArabic();

        assertEquals("Римское число введено неверно!",
                viewModel.getConvertStatus());
    }
}
