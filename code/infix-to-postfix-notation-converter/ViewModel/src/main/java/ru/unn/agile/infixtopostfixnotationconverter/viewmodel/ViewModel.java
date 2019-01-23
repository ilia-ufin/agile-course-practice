package ru.unn.agile.infixtopostfixnotationconverter.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.*;

import javafx.collections.FXCollections;
import ru.unn.agile.infixtopostfixnotationconverter.model.InfixToPostfixNotationConverter;

import java.util.Arrays;

public class ViewModel {
    public static final String LOG_MESSAGE_COMPUTE_BUTTON_CLICKED = "Compute button clicked";
    public static final String LOG_MESSAGE_INPUT_EXPRESSION_CHANGED = "Input expression changed";

    private final StringProperty infixExpression = new SimpleStringProperty();
    private final StringProperty postfixExpression = new SimpleStringProperty();
    private final StringProperty expressionResult = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final BooleanProperty convertButtonDisabled = new SimpleBooleanProperty();
    private final ValueChangeListener valueChangeListener = new ValueChangeListener();
    private final ListProperty<String> logLines = new SimpleListProperty<>();

    private ILogger logger;


    public ViewModel() {
        infixExpression.set("");
        postfixExpression.set("");
        expressionResult.set("");
        status.set(Status.WAITING.toString());
        convertButtonDisabled.set(true);
        logger = null;

        infixExpression.addListener(valueChangeListener);
    }

    public void setLogger(final ILogger logger) {
        this.logger = logger;
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

    public ListProperty<String> logLinesProperty() {
        return logLines;
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

        StringBuilder logMessage = new StringBuilder(LOG_MESSAGE_COMPUTE_BUTTON_CLICKED)
                .append(", expression: '").append(infixExpression.get()).append("'")
                .append(", converted: '").append(String.join(" ", converted)).append("'")
                .append(", result: '").append(expressionResult.get()).append("'");
        log(logMessage.toString());
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

            StringBuilder logMessage = new StringBuilder(LOG_MESSAGE_INPUT_EXPRESSION_CHANGED)
                    .append(", new value: '").append(newValue).append("'")
                    .append(", status: ").append(status.get());
            log(logMessage.toString());
        }
    }

    private void log(final String message) {
        if (logger != null) {
            logger.log(message);
            updateLog();
        }
    }

    private void updateLog() {
        logLines.setValue(FXCollections.observableArrayList(logger.getMessages()));
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
