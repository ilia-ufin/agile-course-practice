package ru.unn.agile.mathstatistics.viewmodel.validators;

public class InputDistributionUnitValidator {
    public boolean validate(final String valueText, final String probabilityText) {
        try {
            if (valueText.isEmpty() || probabilityText.isEmpty()) {
                return false;
            }

            Double.parseDouble(valueText);
            Double.parseDouble(probabilityText);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
