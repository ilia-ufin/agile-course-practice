package ru.unn.agile.currencyconverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ViewModelTest {
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
    public void initViewModelWithDefaultValues() {
        assertEquals("", viewModel.getAddSrcCode());
        assertEquals("", viewModel.getAddTgtCode());
        assertEquals("", viewModel.getAddRate());
        assertEquals("", viewModel.getConvSrcCode());
        assertEquals("", viewModel.getConvTgtCode());
        assertEquals("", viewModel.getConvAmount());
        assertEquals("", viewModel.getCurrPairs());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void viewModelPropertiesAreInitializedUponStart() {
        assertNotEquals(null, viewModel.addSrcCodeProperty().get());
        assertNotEquals(null, viewModel.addTgtCodeProperty().get());
        assertNotEquals(null, viewModel.addRateProperty().get());
        assertNotEquals(null, viewModel.convSrcCodeProperty().get());
        assertNotEquals(null, viewModel.convTgtCodeProperty().get());
        assertNotEquals(null, viewModel.convAmountProperty().get());
        assertNotEquals(null, viewModel.currPairsProperty().get());
        assertNotEquals(null, viewModel.resultProperty().get());
    }

    @Test
    public void canAddCurrencyPair() {
        setAddCurrencyInput("USD", "RUB", "30");
        viewModel.addCurrencyPair();

        assertEquals("USD/RUB\n", viewModel.getCurrPairs());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void canAddMultipleCurrencyPairs() {
        setAddCurrencyInput("USD", "RUB", "30");
        viewModel.addCurrencyPair();

        setAddCurrencyInput("EUR", "RUB", "40");
        viewModel.addCurrencyPair();

        String[] pairs = viewModel.getCurrPairs().split("\\r?\\n");
        assertEquals(2, pairs.length);
        assertTrue(Arrays.asList(pairs).contains("USD/RUB"));
        assertTrue(Arrays.asList(pairs).contains("EUR/RUB"));
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void addingExistingCurrPairDoesntCreateDuplicate() {
        setAddCurrencyInput("USD", "RUB", "30");
        viewModel.addCurrencyPair();

        setAddCurrencyInput("USD", "RUB", "40");
        viewModel.addCurrencyPair();

        assertEquals("USD/RUB\n", viewModel.getCurrPairs());
        assertEquals("", viewModel.getResult());
    }

    @Test
    public void canPrintDoubleConverterErrorOnAdd() {
        setAddCurrencyInput("USD", "RUB", "40.B");
        viewModel.addCurrencyPair();

        assertEquals("Не удалось распознать число: \"40.B\"",
                viewModel.getResult());
    }

    @Test
    public void canPrintDoubleConverterErrorWithEmptyFieldOnAdd() {
        setAddCurrencyInput("USD", "RUB", "");
        viewModel.addCurrencyPair();

        assertEquals("Не удалось распознать число: \"\"",
                viewModel.getResult());
    }

    @Test
    public void canPrintWrongPairErrorOnAdd() {
        setAddCurrencyInput("USD", "RB", "40");
        viewModel.addCurrencyPair();

        assertEquals("Ошибка: Currency Codes don't meet the pattern", viewModel.getResult());
    }

    @Test
    public void canPrintWrongPairErrorWithEmptyFieldOnAdd() {
        setAddCurrencyInput("", "RUB", "40");
        viewModel.addCurrencyPair();

        assertEquals("Ошибка: Currency Codes don't meet the pattern", viewModel.getResult());
    }

    @Test
    public void canConvertExistingPair() {
        setAddCurrencyInput("USD", "RUB", "30");
        viewModel.addCurrencyPair();

        setConvertCurrencyInput("USD", "RUB", "2");
        viewModel.convertCurrency();

        assertEquals("2.0 USD = 60.0 RUB", viewModel.getResult());
    }

    @Test
    public void canConvertInverseExistingPair() {
        setAddCurrencyInput("USD", "RUB", "30");
        viewModel.addCurrencyPair();

        setConvertCurrencyInput("RUB", "USD", "180");
        viewModel.convertCurrency();

        assertEquals("180.0 RUB = 6.0 USD", viewModel.getResult());
    }

    @Test
    public void canPrintDoubleConverterErrorOnConvert() {
        setAddCurrencyInput("USD", "RUB", "30");
        viewModel.addCurrencyPair();

        setConvertCurrencyInput("RUB", "USD", "abc");
        viewModel.convertCurrency();

        assertEquals("Не удалось распознать число: \"abc\"",
                viewModel.getResult());
    }

    @Test
    public void canPrintDoubleConverterErrorWithEmptyFieldOnConvert() {
        setAddCurrencyInput("USD", "RUB", "30");
        viewModel.addCurrencyPair();

        setConvertCurrencyInput("RUB", "USD", "");
        viewModel.convertCurrency();

        assertEquals("Не удалось распознать число: \"\"",
                viewModel.getResult());
    }

    @Test
    public void canPrintWrongPairErrorOnConvert() {
        setAddCurrencyInput("USD", "RUB", "30");
        viewModel.addCurrencyPair();

        setConvertCurrencyInput("RUB", "UD", "2");
        viewModel.convertCurrency();

        assertEquals("Ошибка: Currency Codes don't meet the pattern", viewModel.getResult());
    }

    @Test
    public void canPrintWrongPairErrorWithEmptyFieldOnConvert() {
        setAddCurrencyInput("USD", "RUB", "30");
        viewModel.addCurrencyPair();

        setConvertCurrencyInput("", "USD", "2");
        viewModel.convertCurrency();

        assertEquals("Ошибка: Currency Codes don't meet the pattern", viewModel.getResult());
    }

    @Test
    public void canPrintNonExistentPairErrorOnConvert() {
        setConvertCurrencyInput("RUB", "USD", "2");
        viewModel.convertCurrency();

        assertEquals("Ошибка: Can't convert currency. Rate is not found",
                viewModel.getResult());
    }

    @Test
    public void canModifyExistingCurrencyPair() {
        setAddCurrencyInput("USD", "RUB", "30");
        viewModel.addCurrencyPair();

        setAddCurrencyInput("USD", "RUB", "40");
        viewModel.addCurrencyPair();

        setConvertCurrencyInput("USD", "RUB", "1");
        viewModel.convertCurrency();

        assertEquals("1.0 USD = 40.0 RUB", viewModel.getResult());
    }

    private void setAddCurrencyInput(final String srcCodeStr, final String tgtCodeStr,
                                     final String rateStr) {
        viewModel.setAddSrcCode(srcCodeStr);
        viewModel.setAddTgtCode(tgtCodeStr);
        viewModel.setAddRate(rateStr);
    }

    private void setConvertCurrencyInput(final String srcCodeStr, final String tgtCodeStr,
                                         final String amountStr) {
        viewModel.setConvSrcCode(srcCodeStr);
        viewModel.setConvTgtCode(tgtCodeStr);
        viewModel.setConvAmount(amountStr);
    }
}
