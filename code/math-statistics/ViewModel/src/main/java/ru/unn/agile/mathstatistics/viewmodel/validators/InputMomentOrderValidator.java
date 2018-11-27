package ru.unn.agile.mathstatistics.viewmodel.validators;

public class InputMomentOrderValidator {
    public boolean validate(final String momentOrderText) {
        try {
            if (momentOrderText.isEmpty()) {
                return false;
            }

            Integer.parseInt(momentOrderText);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
