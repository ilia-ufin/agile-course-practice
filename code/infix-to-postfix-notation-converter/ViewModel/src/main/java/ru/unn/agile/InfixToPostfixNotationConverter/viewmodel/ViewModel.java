package ru.unn.agile.InfixToPostfixNotationConverter.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.*;

import ru.unn.agile.InfixToPostfixNotationConverter.model.InfixToPostfixNotationConverter;

import java.util.Arrays;

public class ViewModel {
    private final StringProperty infixExpression = new SimpleStringProperty();
    private final StringProperty postfixExpression = new SimpleStringProperty();
    private final StringProperty expressionResult = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final BooleanProperty convertButtonDisabled = new SimpleBooleanProperty();
    private final ValueChangeListener valueChangeListener = new ValueChangeListener();

    public ViewModel() {
        infixExpression.set("");
        postfixExpression.set("");
        expressionResult.set("");
        status.set(Status.WAITING.toString());
        convertButtonDisabled.set(true);

        infixExpression.addListener(valueChangeListener);
    }

    public StringProperty infixExpressionProperty() {
        return infixExpression;
    }

    public StringProperty postfixExpressionProperty() {
        return postfixExpression;
    }

    public StringProperty expressionResultProperty() {
        return expressionResult;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public BooleanProperty convertButtonDisabledProperty() {
        return convertButtonDisabled;
    }

    public Status getInputStatus() {
        if (infixExpression.get().isEmpty()) {
            return Status.WAITING;
        }

        return InfixToPostfixNotationConverter.isValidExpression(infixExpression.get())
                ? Status.READY : Status.BAD_FORMAT;
    }

    public void convertAndCalculate() {
        String[] converted =
                InfixToPostfixNotationConverter.convert(infixExpression.get());
        Integer result =
                InfixToPostfixNotationConverter.calculateResult(infixExpression.get());

        postfixExpression.set(getConversionResultAsString(converted));
        expressionResult.set(result.toString());
        status.set(Status.SUCCESS.toString());
    }

    private String getConversionResultAsString(final String[] conversionResult) {
        return Arrays.toString(conversionResult);
    }

    private boolean canConvert() {
        return getInputStatus() == Status.READY;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
            convertButtonDisabled.set(!canConvert());
        }
    }
}

enum Status {
    WAITING("Waiting for input"),
    READY("Press 'Convert & Calculate' button"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

