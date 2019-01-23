package ru.unn.agile.depositconverter.model;

public enum FrequencyOfCapitalization {
    onceMonth(1),
    onceTwoMonth(2),
    quarterly(3),
    halfYear(6);
    private int month;

    FrequencyOfCapitalization(final int month) {
        this.month = month;
    }

    public boolean useCharges(final int term) {
        return term % this.month == 0;
    }
}
