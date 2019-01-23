package ru.unn.agile.romannumberconverter.viewmodel;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTest {
    private ViewModel viewModel;

    protected void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private String getFormStateLogMessage() {
        return String.format(
                ViewModel.LogMessages.CURRENT_STATE,
                viewModel.arabicValueStrProperty().get(),
                viewModel.romanValueStrProperty().get(),
                viewModel.getConvertStatus()
        );
    }

    private String getArabicConvertededStateLogMessage() {
        return ViewModel.LogMessages.TO_ARABIC_PRESSED + " "
                + getFormStateLogMessage();
    }

    private String getRomanConvertededStateLogMessage() {
        return ViewModel.LogMessages.TO_ROMAN_PRESSED + " "
                + getFormStateLogMessage();
    }

    @Before
    public void createViewModel() {
        viewModel = new ViewModel(new FakeLogger());
    }

    @After
    public void deleteViewModel() {
        viewModel = null;
    }

    @Test
    public void initViewModelWithDefaultValues() {
        assertEquals("", viewModel.getArabicValueStr());
        assertEquals("", viewModel.getRomanValueStr());
        assertEquals("", viewModel.logProperty().get());
    }

    @Test
    public void canCreateViewModelWithoutLogger() {
        ViewModel vm = new ViewModel();

        assertNotNull(vm);
    }

    @Test(expected = IllegalArgumentException.class)
    public void viewModelConstructorThrowExceptionIfNullLoggerInArguments() {
        new ViewModel(null);
    }

    @Test
    public void isLogEmptyAfterInit() {
        List<String> logList = viewModel.getLogList();

        assertEquals(0, logList.size());
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

    @Test
    public void canCreateLogMessages() {
        ViewModel.LogMessages logMessages = new ViewModel.LogMessages();

        assertNotNull(logMessages);
    }

    @Test
    public void logContainsMessageWhenConvertedToArabic() {
        viewModel.setArabicValueStr("10");
        viewModel.convertArabicToRoman();

        List<String> logList = viewModel.getLogList();
        String message = getRomanConvertededStateLogMessage();

        assertTrue(logList.get(0).contains(message));
    }

    @Test
    public void logContainsMessageWhenConvertedToRoman() {
        viewModel.setRomanValueStr("X");
        viewModel.convertRomanToArabic();

        List<String> logList = viewModel.getLogList();
        String message = getArabicConvertededStateLogMessage();

        assertTrue(logList.get(0).contains(message));
    }

    @Test
    public void logNotChangedIfValueChanged() {
        viewModel.setRomanValueStr("X");
        viewModel.setArabicValueStr("1");

        List<String> logList = viewModel.getLogList();

        assertEquals(0, logList.size());
    }

    @Test
    public void logContainsMessageWhenRomanConverted() {
        viewModel.setRomanValueStr("III");
        viewModel.convertRomanToArabic();

        List<String> logList = viewModel.getLogList();
        String message = getArabicConvertededStateLogMessage();

        assertTrue(logList.get(logList.size() - 1).contains(message));
    }

    @Test
    public void logContainsMessageWhenArabicConverted() {
        viewModel.setArabicValueStr("3");
        viewModel.convertArabicToRoman();

        List<String> logList = viewModel.getLogList();
        String message = getRomanConvertededStateLogMessage();

        assertTrue(logList.get(logList.size() - 1).contains(message));
    }

    @Test
    public void logPropertySameAsRealLog() {
        viewModel.setRomanValueStr("V");
        viewModel.setArabicValueStr("10");
        viewModel.convertRomanToArabic();

        List<String> logList = viewModel.getLogList();
        StringBuilder stringLogMessages = new StringBuilder();
        for (String line : logList) {
            stringLogMessages.append(line).append("\n");
        }

        assertEquals(stringLogMessages.toString(), viewModel.getLog());
    }
}
