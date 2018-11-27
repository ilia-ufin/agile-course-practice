package ru.unn.agile.currencyconverter.model;

import ru.unn.agile.currencyconverter.model.errorhandling.CurrencyConverterException;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private Map<String, Double> currencyPairs;

    private static final String CURRENCY_CODE_PATTERN = "[A-Z]{3}";
    private static final String CURRENCY_CODE_DELIMITER = "/";

    public CurrencyConverter() {
        this.currencyPairs = new HashMap<>();
    }

    public double convert(final String srcCode, final String tgtCode, final double amount) {
        validateCodes(srcCode, tgtCode);
        validateAmount(amount);

        double currencyRate = getCurrencyRateByCodes(srcCode, tgtCode);
        if (currencyRate != 0) {
            return amount * currencyRate;
        }

        double inverseCurrencyRate = getCurrencyRateByCodes(tgtCode, srcCode);
        if (inverseCurrencyRate != 0) {
            return amount / inverseCurrencyRate;
        }

        throw new CurrencyConverterException("Can't convert currency. Rate is not found");
    }


    public void addCurrencyPair(final String srcCode, final String tgtCode, final double rate) {
        validateCodes(srcCode, tgtCode);
        validateRate(rate);

        double inverseCurrencyRate = getCurrencyRateByCodes(tgtCode, srcCode);
        if (inverseCurrencyRate != 0) {
            String inversePair = tgtCode + CURRENCY_CODE_DELIMITER + srcCode;
            currencyPairs.put(inversePair, rate);
            return;
        }

        String currencyPair = srcCode + CURRENCY_CODE_DELIMITER + tgtCode;
        currencyPairs.put(currencyPair, rate);
    }

    public Map<String, Double> getCurrencyPairs() {
        return this.currencyPairs;
    }

    private double getCurrencyRateByCodes(final String srcCode, final String tgtCode) {
        String codesPair = srcCode + CURRENCY_CODE_DELIMITER + tgtCode;
        if (currencyPairs.containsKey(codesPair)) {
            return currencyPairs.get(codesPair);
        }

        return 0;
    }

    private void validateAmount(final double amount) {
        if (amount < 0) {
            throw new CurrencyConverterException("Can't covert negative number");
        }
    }

    private void validateRate(final double rate) {
        if (rate <= 0) {
            throw new CurrencyConverterException("Currency Rate should be positive");
        }
    }

    private void validateCodes(final String srcCode, final String tgtCode) {
        validateCode(srcCode);
        validateCode(tgtCode);
    }

    private void validateCode(final String currencyCode) {
        if (currencyCode == null) {
            throw new CurrencyConverterException("Currency Codes can't be null");
        }

        if (!currencyCode.matches(CURRENCY_CODE_PATTERN)) {
            throw new CurrencyConverterException("Currency Codes don't meet the pattern");
        }
    }
}
