package ru.unn.agile.numerical_integration.ViewModel.legacy;

import ru.unn.agile.numerical_integration.Model.BaseDefinition;
import ru.unn.agile.numerical_integration.Model.Expression;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;

public class ViewModel {
    private static final String FLOAT_NUMBER_REGEX =
            "[+-]?((\\d+\\.?\\d*)|(\\d*\\.\\d+))";

    public static final String ERROR_WRONG_LEFT_BORDER =
            "Wrong left border value";
    public static final String ERROR_WRONG_RIGHT_BORDER =
            "Wrong right border value";
    public static final String ERROR_WRONG_SPLITS_NUMBER =
            "Wrong splits number value";
    public static final String ERROR_WRONG_FUNCTION_TEXT =
            "Wrong function";
    private static final String ERROR_COMPUTATION_FAILURE =
            "Error during computation";
    private static final String ERROR_EXPECTED_FLOAT_NUMBER =
            "Expected float number";
    private static final String ERROR_EXPECTED_INTEGER_NUMBER =
            "Expected integer number";
    private static final String ERROR_EXPECTED_POSITIVE_NUMBER =
            "Expected positive number";
    private static final String STATUS_HEADER_TEXT =
            "Unable to compute due to the following errors:";

    private String functionText;
    private String leftBorderText;
    private String rightBorderText;
    private String splitsNumberText;
    private String outputMessage;
    private AbstractMap<ErrorKind, String> errorsList;


    public ViewModel() {
        functionText = "x";
        leftBorderText = "0.0";
        rightBorderText = "1.0";
        splitsNumberText = "1";
        outputMessage = "";
        errorsList = new HashMap<>();
        checkErrors();
    }

    public String getFunctionText() {
        return functionText;
    }

    public String getLeftBorderValue() {
        return leftBorderText;
    }

    public String getRightBorderValue() {
        return rightBorderText;
    }

    public String getSplitsNumber() {
        return splitsNumberText;
    }

    public String getOutputMessage() {
        return outputMessage;
    }

    public void setLeftBorderValue(final String value) {
        leftBorderText = value;
        checkErrors();
    }

    public void setRightBorderValue(final String value) {
        rightBorderText = value;
        checkErrors();
    }

    public void setSplitsNumber(final String value) {
        splitsNumberText = value;
        checkErrors();
    }

    public void setFunction(final String value) {
        functionText = value;
        checkErrors();
    }

    public boolean canComputeFunction() {
        return errorsList.isEmpty();
    }

    public void compute() {
        if (!canComputeFunction()) {
            return;
        }

        MathExpression parser = new MathExpression(functionText);
        final Expression f = x -> {
            final boolean success = parser.eval(x);
            return success ? parser.getResult() : 0.0;
        };

        final double a = Double.parseDouble(leftBorderText);
        final double b = Double.parseDouble(rightBorderText);
        final int splitsNumber = Integer.parseInt(splitsNumberText);

        try {
            double result = BaseDefinition.calculate(f, a, b, splitsNumber);
            outputMessage = Double.toString(result);
        } catch (Exception e) {
            addError(ErrorKind.Computation, e.toString());
            checkErrors();
        }
    }

    private void checkErrors() {
        checkFunctionText();
        checkLeftBorderValue();
        checkRightBorderValue();
        checkSplitsNumber();

        outputMessage = createErrorMessage();
    }

    private void checkFunctionText() {
        final MathExpression parser = new MathExpression(functionText);
        final boolean success = parser.eval(0);
        if (!success) {
            addError(ErrorKind.Function, parser.getError());
            return;
        }
        removeError(ErrorKind.Function);
    }

    private void checkLeftBorderValue() {
        if (leftBorderText.matches(FLOAT_NUMBER_REGEX)) {
            removeError(ErrorKind.LeftBorderValue);
        } else {
            addError(ErrorKind.LeftBorderValue, ERROR_EXPECTED_FLOAT_NUMBER);
        }
    }

    private void checkRightBorderValue() {
        if (rightBorderText.matches(FLOAT_NUMBER_REGEX)) {
            removeError(ErrorKind.RightBorderValue);
        } else {
            addError(ErrorKind.RightBorderValue, ERROR_EXPECTED_FLOAT_NUMBER);
        }
    }

    private void checkSplitsNumber() {
        if (!splitsNumberText.matches("[+-]?\\d+")) {
            addError(ErrorKind.SplitsNumber, ERROR_EXPECTED_INTEGER_NUMBER);
            return;
        }

        if (Integer.parseInt(splitsNumberText) < 1) {
            addError(ErrorKind.SplitsNumber, ERROR_EXPECTED_POSITIVE_NUMBER);
            return;
        }

        removeError(ErrorKind.SplitsNumber);
    }

    private void addError(final ErrorKind kind, final String message) {
        errorsList.put(kind, message);
    }

    private void removeError(final ErrorKind kind) {
        errorsList.remove(kind);
    }

    private String createErrorMessage() {
        final int variableToMakeLinterHappy = 100;
        StringBuilder builder = new StringBuilder(variableToMakeLinterHappy);

        Iterator it = errorsList.entrySet().iterator();

        if (it.hasNext()) {
            builder.append(STATUS_HEADER_TEXT).append("\n");
        }

        while (it.hasNext()) {
            final HashMap.Entry pair = (HashMap.Entry) it.next();
            final ErrorKind errorKind = (ErrorKind) pair.getKey();
            final String errorMessage = (String) pair.getValue();
            builder.append(getErrorMessageBase(errorKind)).append(": ");
            builder.append(errorMessage).append(";");
            if (it.hasNext()) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    private static String getErrorMessageBase(final ErrorKind errorKind) {
        switch (errorKind) {
            case LeftBorderValue:
                return ERROR_WRONG_LEFT_BORDER;
            case RightBorderValue:
                return ERROR_WRONG_RIGHT_BORDER;
            case SplitsNumber:
                return ERROR_WRONG_SPLITS_NUMBER;
            case Function:
                return ERROR_WRONG_FUNCTION_TEXT;
            case Computation:
                return ERROR_COMPUTATION_FAILURE;
            default:
                throw new RuntimeException("Unexpected error kind");
        }
    }
}
