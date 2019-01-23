package ru.unn.agile.primenumber.viewModel;

public enum LogMessage {
    APPLICATION_STARTED("Application is Started"),
    STATE_OF_OPERANDS_MESSAGE("Current Sate: left bound = %s, right bound = %s"),
    CALCULATION_IS_FINISHED_MESSAGE("Calculation is finished with: "
            + "status: %s"
            + " and result: %s"),
    CALCULATION_IS_STARTED_MESSAGE("Calculation is started with parameters:"
            + " left bound = %s ,"
            + " right bound = %s");

    private String message;

    LogMessage(final String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
