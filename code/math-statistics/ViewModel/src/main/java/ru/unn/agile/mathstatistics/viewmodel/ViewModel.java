package ru.unn.agile.mathstatistics.viewmodel;

import ru.unn.agile.mathstatistics.model.*;
import ru.unn.agile.mathstatistics.viewmodel.validators.*;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private List<Number> values;
    private List<Double> probabilities;
    private List<String> distributionUnits;

    private String valueText;
    private String probabilityText;
    private Operation operation;
    private String momentOrderText;
    private String momentOffsetText;
    private String resultText;
    private String statusMessageText;

    private boolean isOrderTextEnabled;
    private boolean isOffsetTextEnabled;

    private boolean isCalculateButtonEnabled;

    public ViewModel() {
        values = new ArrayList<Number>();
        probabilities = new ArrayList<Double>();
        distributionUnits = new ArrayList<String>();

        reset();
    }

    public boolean checkDistributionUnit() {
        InputDistributionUnitValidator validator = new InputDistributionUnitValidator();
        if (!validator.validate(valueText, probabilityText)) {
            statusMessageText = Status.BAD_DISTRIBUTION_UNIT_FORMAT;

            return false;
        }

        statusMessageText = Status.ADD_TO_DISTRIBUTION_READY;
        return true;
    }

    public boolean checkMomentOrder() {
        InputMomentOrderValidator validator = new InputMomentOrderValidator();
        if (!validator.validate(momentOrderText)) {
            statusMessageText = Status.BAD_MOMENT_ORDER_FORMAT;

            return false;
        }

        final int order = Integer.parseInt(momentOrderText);
        try {
            MomentOrderValidator.validate(order);
        } catch (IllegalArgumentException e) {
            statusMessageText = Status.BAD_MOMENT_ORDER_VALUE;

            return false;
        }

        return  true;
    }

    public boolean checkMomentOffset() {
        InputMomentOffsetValidator validator = new InputMomentOffsetValidator();
        if (!validator.validate(momentOffsetText)) {
            statusMessageText = Status.BAD_MOMENT_OFFSET_FORMAT;

            return false;
        }

        return  true;
    }

    public void addToDistributionProcess() {
        if (!checkDistributionUnit()) {
            return;
        }

        final Double value = Double.parseDouble(valueText);
        final Double probability = Double.parseDouble(probabilityText);

        processDistributionUnit(value, probability);
    }

    public void calculateProcess() {
        Number[] nativeValuesArray = new Number[values.size()];
        values.toArray(nativeValuesArray);
        Double[] nativeProbabilitiesArray = new Double[probabilities.size()];
        probabilities.toArray(nativeProbabilitiesArray);

        Double calculatedValue = 0.0;
        if (operation == Operation.EXPECTED_VALUE) {
            calculatedValue = MathStatistics.calculateExpectedValue(nativeValuesArray,
                    nativeProbabilitiesArray);
        } else if (operation == Operation.DISPERSION) {
            calculatedValue = MathStatistics.calculateDispersion(nativeValuesArray,
                    nativeProbabilitiesArray);
        } else if (operation == Operation.INITIAL_MOMENT) {
            if (!checkMomentOrder()) {
                return;
            }

            final int order = Integer.parseInt(momentOrderText);

            calculatedValue = MathStatistics.calculateInitialMoment(nativeValuesArray,
                    nativeProbabilitiesArray, order);
        } else if (operation == Operation.CENTRAL_MOMENT) {
            if (!checkMomentOrder()) {
                return;
            }
            if (!checkMomentOffset()) {
                return;
            }

            final int order = Integer.parseInt(momentOrderText);
            final Number offset = Double.parseDouble(momentOffsetText);

            calculatedValue = MathStatistics.calculateCentralMoment(nativeValuesArray,
                    nativeProbabilitiesArray, order, offset);
        }

        resultText = calculatedValue.toString();
        statusMessageText = Status.SUCCESS;
    }

    public void resetProcess() {
        reset();
    }

    public List<String> getDistributionUnits() {
        return distributionUnits;
    }

    public boolean isOrderTextEnabled() {
        return isOrderTextEnabled;
    }

    public boolean isOffsetTextEnabled() {
        return isOffsetTextEnabled;
    }

    public boolean isCalculateButtonEnabled() {
        return isCalculateButtonEnabled;
    }

    public String getValueText() {
        return valueText;
    }

    public void setValueText(final String valueText) {
        this.valueText = valueText;
    }

    public String getProbabilityText() {
        return probabilityText;
    }

    public void setProbabilityText(final String probabilityText) {
        this.probabilityText = probabilityText;
    }

    public String getMomentOrderText() {
        return momentOrderText;
    }

    public void setMomentOrderText(final String momentOrderText) {
        this.momentOrderText = momentOrderText;
    }

    public String getMomentOffsetText() {
        return momentOffsetText;
    }

    public void setMomentOffsetText(final String momentOffsetText) {
        this.momentOffsetText = momentOffsetText;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(final Operation operation) {
        this.operation = operation;

        if (isMomentOperation(operation)) {
            isOrderTextEnabled = true;
            if (operation == Operation.CENTRAL_MOMENT) {
                isOffsetTextEnabled = true;
            } else {
                isOffsetTextEnabled = false;
            }
        } else {
            isOrderTextEnabled = false;
            isOffsetTextEnabled = false;
        }
    }

    public String getResultText() {
        return resultText;
    }

    public String getStatusMessageText() {
        return statusMessageText;
    }

    private void processDistributionUnit(final Double value,
                                         final Double probability) {
        try {
            ProbabilityValidator.validate(probability);
        } catch (IllegalArgumentException e) {
            statusMessageText = Status.BAD_PROBABILITY_VALUE;
            return;
        }

        values.add(value);
        probabilities.add(probability);

        Double[] nativeProbabilitiesArray = new Double[probabilities.size()];
        NormalizationConditionChecker.Status status = NormalizationConditionChecker.check(
                        probabilities.toArray(nativeProbabilitiesArray));

        if (status == NormalizationConditionChecker.Status.CONDITION_IS_MET) {
            statusMessageText = Status.CALCULATE_READY;
            applyDistributionUnit(value, probability);
            isCalculateButtonEnabled = true;

            return;
        }
        if (status == NormalizationConditionChecker.Status.BETWEEN_ZERO_AND_ONE) {
            statusMessageText = Status.ADD_TO_DISTRIBUTION_SUCCESS;
            applyDistributionUnit(value, probability);

            return;
        }

        statusMessageText = Status.INCORRECT_PROBABILITIES_SUM;
        values.remove(values.size() - 1);
        probabilities.remove(probabilities.size() - 1);
    }

    private void applyDistributionUnit(final Double value, final Double probability) {
        final String distributionUnit = "(" + value.toString() + ", " + probability.toString()
                + ")";

        distributionUnits.add(distributionUnit);
    }

    private boolean isMomentOperation(final Operation operation) {
        return (operation == Operation.INITIAL_MOMENT) || (operation == Operation.CENTRAL_MOMENT);
    }

    private void reset() {
        values.clear();
        probabilities.clear();
        distributionUnits.clear();

        valueText = "";
        probabilityText = "";
        operation = Operation.EXPECTED_VALUE;
        momentOrderText = "";
        resultText = "";
        momentOffsetText = "";
        statusMessageText = Status.WAITING_INPUT_DATA;

        isOrderTextEnabled = false;
        isOffsetTextEnabled = false;

        isCalculateButtonEnabled = false;
    }
}
