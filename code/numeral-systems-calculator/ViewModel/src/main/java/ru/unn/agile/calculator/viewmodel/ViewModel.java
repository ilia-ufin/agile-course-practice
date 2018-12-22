package ru.unn.agile.calculator.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import ru.unn.agile.calculator.model.NumeralSystem;
import ru.unn.agile.calculator.model.NumeralSystemConverter;
import ru.unn.agile.calculator.model.RadixCalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ViewModel {
    static final String LOG_CALCULATED = "Calculation success";
    static final String LOG_VALUES_CHANGED = "Values changed: a = %s, b = %s";

    private final ObjectProperty<NumeralSystem> outputNumberSystem = new SimpleObjectProperty<>();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty userMessage = new SimpleStringProperty();
    private final StringProperty number1 = new SimpleStringProperty();
    private final StringProperty number2 = new SimpleStringProperty();
    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();
    private final List<NumeralSystem> numeralSystems =
            FXCollections.observableList(Arrays.stream(NumeralSystem.values())
                    .filter(s -> !NumeralSystem.UNKNOWN.equals(s))
                    .collect(Collectors.toList()));
    private final StringProperty log = new SimpleStringProperty();

    private ILogger logger;

    public ViewModel() {
        init();
    }

    private void init() {
        outputNumberSystem.setValue(NumeralSystem.BINARY);
        result.set("");
        userMessage.set(UserMessages.WAIT_FOR_INPUT.toString());
        calculationDisabled.set(true);
        number1.set("");
        number2.set("");

        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(number1, number2);
            }

            @Override
            protected boolean computeValue() {
                return checkInput() == UserMessages.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());

        final List<StringProperty> fields = Arrays.asList(number1, number2);

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
        }
    }

    public void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger must be mot null");
        }
        this.logger = logger;
        updateLog();
    }

    private void updateLog() {
        log.set(logger.getLog());
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public final boolean isCalculationDisabled() {
        return calculationDisabledProperty().get();
    }

    public StringProperty number1Property() {
        return number1;
    }

    public StringProperty number2Property() {
        return number2;
    }

    public final List<NumeralSystem> getNumeralSystems() {
        return numeralSystems;
    }

    public void calculate() {
        NumeralSystem currentSystem = getOutputNumberSystem();
        String a = number1.get();
        String b = number2.get();

        String composedResult = buildSumResult(currentSystem, a, b)
                + buildMultResult(currentSystem, a, b)
                + buildUnaryMinusResult(currentSystem, a, b);
        result.set(composedResult);
        userMessage.set(UserMessages.SUCCESS.toString());

        logger.log(LOG_CALCULATED);
        updateLog();
    }

    public NumeralSystem getOutputNumberSystem() {
        return outputNumberSystem.get();
    }

    public ObjectProperty<NumeralSystem> outputNumberSystemProperty() {
        return outputNumberSystem;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty userMessageProperty() {
        return userMessage;
    }

    public StringProperty logProperty() {
        return log;
    }

    public String getResult() {
        return result.get();
    }

    public String getUserMessage() {
        return userMessage.get();
    }

    public String getLog() {
        return log.get();
    }

    private String buildUnaryMinusResult(final NumeralSystem currentSystem,
                                         final String a,
                                         final String b) {
        return "Minus: -a = "
                + RadixCalculator.unaryMinus(a, currentSystem)
                + ", -b = "
                + RadixCalculator.unaryMinus(b, currentSystem)
                + "\n";
    }

    private String buildMultResult(final NumeralSystem currentSystem,
                                   final String a,
                                   final String b) {
        return "Mult: a*b = "
                + RadixCalculator.multiply(a, b, currentSystem)
                + "\n";
    }

    private String buildSumResult(final NumeralSystem currentSystem,
                                  final String a,
                                  final String b) {
        return "Sum: a+b = "
                + RadixCalculator.add(a, b, currentSystem)
                + "\n";
    }

    private UserMessages checkInput() {
        String a = number1.get();
        String b = number2.get();
        if ("".equals(a) || "".equals(b)) {
            return UserMessages.WAIT_FOR_INPUT;
        }

        Integer parsedA = NumeralSystemConverter.tryParse(a);
        Integer parsedB = NumeralSystemConverter.tryParse(b);
        if ((parsedA == null) || (parsedB == null)) {
            return UserMessages.INPUT_INVALID;
        }

        return UserMessages.READY;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            userMessage.set(checkInput().toString());

            logger.log(String.format(LOG_VALUES_CHANGED, number1.get(), number2.get()));
            updateLog();
        }
    }
}
