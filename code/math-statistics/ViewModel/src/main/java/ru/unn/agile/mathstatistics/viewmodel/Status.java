package ru.unn.agile.mathstatistics.viewmodel;

public final class Status {
    public static final String WAITING_INPUT_DATA = "Please provide input data";
    public static final String ADD_TO_DISTRIBUTION_READY = "Press 'Add to distribution'";
    public static final String BAD_DISTRIBUTION_UNIT_FORMAT = "Bad distribution unit format";
    public static final String BAD_PROBABILITY_VALUE =
            "Bad probability value. Should be 0 <= p <= 1";
    public static final String INCORRECT_PROBABILITIES_SUM =
            "Bad probabilities sum. Should be equal to 1";
    public static final String ADD_TO_DISTRIBUTION_SUCCESS = "Add to distribution successfully";
    public static final String BAD_MOMENT_ORDER_FORMAT = "Bad moment order format";
    public static final String BAD_MOMENT_ORDER_VALUE = "Moment order should be positive integer";
    public static final String BAD_MOMENT_OFFSET_FORMAT = "Bad moment offset format";
    public static final String CALCULATE_READY = "Press 'Calculate'";
    public static final String SUCCESS = "Success";

    private Status() {
    }
}
