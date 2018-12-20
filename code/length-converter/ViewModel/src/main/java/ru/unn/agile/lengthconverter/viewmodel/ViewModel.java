package ru.unn.agile.lengthconverter.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.lengthconverter.model.LengthConverter;
import ru.unn.agile.lengthconverter.model.LengthConverterExceptions;
import ru.unn.agile.lengthconverter.model.LengthUnit;

import java.util.List;

public class ViewModel {
    private static final String EMPTY_MESSAGE = "";

    private final ObjectProperty<ObservableList<LengthUnit>> units =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(LengthUnit.values()));
    private final StringProperty convertFrom = new SimpleStringProperty();
    private final StringProperty convertTo = new SimpleStringProperty();
    private final ObjectProperty<LengthUnit> unitFrom = new SimpleObjectProperty<LengthUnit>();
    private final ObjectProperty<LengthUnit> unitTo = new SimpleObjectProperty<LengthUnit>();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty log = new SimpleStringProperty();

    private ILogger logger;

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Error: Logger is null");
        }
        this.logger = logger;
    }

    public ViewModel() {
        init();
    }

    public ViewModel(final ILogger logger) {
        setLogger(logger);
        init();
    }

    private void init() {
        convertFrom.set("");
        convertTo.set("");
        unitFrom.set(LengthUnit.MILLIMETERS);
        unitTo.set(LengthUnit.METERS);
        status.set(Status.READY.toString());
        log.set(EMPTY_MESSAGE);
    }

    private void writeLog(final String s) {
        logger.log(s);
        StringBuilder sbLog = new StringBuilder();
        for (String line : logger.getLog()) {
            sbLog.append(line).append("\n");
        }
        log.set(sbLog.toString());
    }

    public ObjectProperty<ObservableList<LengthUnit>> unitsProperty() {
        return units;
    }

    public final ObservableList<LengthUnit> getUnits() {
        return units.get();
    }

    public StringProperty convertFromProperty() {
        return convertFrom;
    }

    public StringProperty convertToProperty() {
        return convertTo;
    }

    public LengthUnit getUnitFrom() {
        return unitFrom.get();
    }

    public LengthUnit getUnitTo() {
        return unitTo.get();
    }

    public final String getConvertTo() {
        return convertTo.get();
    }

    public final String getConvertFrom() {
        return convertFrom.get();
    }

    public final String getStatus() {
        return status.get();
    }

    public List<String> getLogList() {
        return logger.getLog();
    }

    public String getLog() {
        return log.get();
    }

    public StringProperty logProperty() {
        return log;
    }

    public boolean checkReady() {
        try {
            if (!getConvertFrom().isEmpty()) {
                Double.parseDouble(getConvertFrom());
                status.set(Status.READY.toString());
                return true;
            } else {
                status.set(Status.WAITING.toString());
                return false;
            }
        } catch (NumberFormatException e) {
            writeLog(String.format(LogMessages.INPUT_VALUE_IS_INCORRECT, getConvertFrom()));
            status.set(Status.INCORRECT_FORMAT.toString());
            return false;
        }

    }

    public void convert() {
        try {
            if (checkReady()) {
                double valueToConvert = Double.parseDouble(getConvertFrom());
                double result =
                        LengthConverter.convert(getUnitFrom(), valueToConvert, getUnitTo());
                convertTo.set(String.valueOf(result));
                writeLog(String.format(LogMessages.CONVERT_IS_PRESSED,
                        valueToConvert, getUnitFrom(), result, getUnitTo()));
                status.set(Status.SUCCESS.toString());
            }
        } catch (LengthConverterExceptions e) {
            writeLog(String.format(LogMessages.INPUT_VALUE_IS_INCORRECT, getConvertFrom()));
            status.set(Status.ERROR.toString());
        }
    }

    public ObjectProperty<LengthUnit> unitPropertyFrom() {
        return unitFrom;
    }

    public ObjectProperty<LengthUnit> unitPropertyTo() {
        return unitTo;
    }

    public static final class LogMessages {
        public static final String CONVERT_IS_PRESSED = "Convert %s %s -> %s %s";
        public static final String INPUT_VALUE_IS_INCORRECT = "Value %s is incorrect";
    }
}
