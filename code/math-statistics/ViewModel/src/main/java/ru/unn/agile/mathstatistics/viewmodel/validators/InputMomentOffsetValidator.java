package ru.unn.agile.mathstatistics.viewmodel.validators;

public class InputMomentOffsetValidator {
    public boolean validate(final String momentOffsetText) {
        try {
            if (momentOffsetText.isEmpty()) {
                return false;
            }

            Double.parseDouble(momentOffsetText);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
