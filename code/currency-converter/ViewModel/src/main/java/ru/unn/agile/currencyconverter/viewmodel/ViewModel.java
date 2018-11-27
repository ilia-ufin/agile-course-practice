package ru.unn.agile.currencyconverter.viewmodel;

import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import ru.unn.agile.currencyconverter.model.CurrencyConverter;
import ru.unn.agile.currencyconverter.model.errorhandling.CurrencyConverterException;

public class ViewModel {
    private static final String ERR_WRONG_NUMBER_TMPL =
            "Не удалось распознать число: \"%s\"";
    private static final String ERR_CURR_CONV_EXC_TMPL = "Ошибка: %s";

    private StringProperty addSrcCode = new SimpleStringProperty();
    private StringProperty addTgtCode = new SimpleStringProperty();
    private StringProperty addRate = new SimpleStringProperty();

    private StringProperty convSrcCode = new SimpleStringProperty();
    private StringProperty convTgtCode = new SimpleStringProperty();
    private StringProperty convAmount = new SimpleStringProperty();

    private StringProperty currPairs = new SimpleStringProperty();
    private StringProperty result = new SimpleStringProperty();

    private CurrencyConverter currencyConverter = new CurrencyConverter();

    public String getAddSrcCode() {
        return addSrcCode.get();
    }

    public void setAddSrcCode(final String newAddSrcCode) {
        addSrcCode.set(newAddSrcCode);
    }

    public StringProperty addSrcCodeProperty() {
        return addSrcCode;
    }

    public String getAddTgtCode() {
        return addTgtCode.get();
    }

    public void setAddTgtCode(final String newAddTgtCode) {
        addTgtCode.set(newAddTgtCode);
    }

    public StringProperty addTgtCodeProperty() {
        return addTgtCode;
    }

    public String getAddRate() {
        return addRate.get();
    }

    public void setAddRate(final String newAddRate) {
        addRate.set(newAddRate);
    }

    public StringProperty addRateProperty() {
        return addRate;
    }

    public String getConvSrcCode() {
        return convSrcCode.get();
    }

    public void setConvSrcCode(final String newConvSrcCode) {
        convSrcCode.set(newConvSrcCode);
    }

    public StringProperty convSrcCodeProperty() {
        return convSrcCode;
    }

    public String getConvTgtCode() {
        return convTgtCode.get();
    }

    public void setConvTgtCode(final String newConvTgtCode) {
        convTgtCode.set(newConvTgtCode);
    }

    public StringProperty convTgtCodeProperty() {
        return convTgtCode;
    }

    public String getConvAmount() {
        return convAmount.get();
    }

    public void setConvAmount(final String newConvAmount) {
        convAmount.set(newConvAmount);
    }

    public StringProperty convAmountProperty() {
        return convAmount;
    }

    public String getCurrPairs() {
        return currPairs.get();
    }

    public StringProperty currPairsProperty() {
        return currPairs;
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public ViewModel() {
        initDefaultFields();
    }

    private void initDefaultFields() {
        addSrcCode.set("");
        addTgtCode.set("");
        addRate.set("");
        convSrcCode.set("");
        convTgtCode.set("");
        convAmount.set("");
        currPairs.set("");
        result.set("");
    }

    public void addCurrencyPair() {
        try {
            currencyConverter.addCurrencyPair(
                    addSrcCode.get(),
                    addTgtCode.get(),
                    Double.parseDouble(addRate.get())
            );
        } catch (NumberFormatException exc) {
            result.set(String.format(ERR_WRONG_NUMBER_TMPL, addRate.get()));
            return;
        } catch (CurrencyConverterException exc) {
            result.set(String.format(ERR_CURR_CONV_EXC_TMPL, exc.getMessage()));
            return;
        }

        Map<String, Double> currencyPairs = currencyConverter.getCurrencyPairs();
        currPairs.set("");
        for (String pair : currencyPairs.keySet()) {
            currPairs.set(currPairs.get() + pair + "\n");
        }

        result.set("");
    }

    public void convertCurrency() {
        Double amount, res;

        try {
            amount = Double.parseDouble(convAmount.get());
        } catch (NumberFormatException exc) {
            result.set(String.format(ERR_WRONG_NUMBER_TMPL, convAmount.get()));
            return;
        }

        try {
            res = currencyConverter.convert(
                    convSrcCode.get(),
                    convTgtCode.get(),
                    amount
            );
        } catch (CurrencyConverterException exc) {
            result.set(String.format(ERR_CURR_CONV_EXC_TMPL, exc.getMessage()));
            return;
        }

        result.set(String.format("%s %s = %s %s",
                amount,
                convSrcCode.get(),
                res,
                convTgtCode.get()));
    }
}
