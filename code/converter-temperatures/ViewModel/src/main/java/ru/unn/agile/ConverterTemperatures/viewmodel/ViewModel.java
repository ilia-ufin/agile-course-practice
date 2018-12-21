package ru.unn.agile.ConverterTemperatures.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.ConverterTemperatures.model.*;

import java.util.List;

public class ViewModel {
    public static final String CELSIUS_SYMBOL = "°C";

    private static final String EMPTY_STRING = "";
    private final StringProperty convertFrom = new SimpleStringProperty();
    private final StringProperty convertTo = new SimpleStringProperty();
    private final StringProperty log = new SimpleStringProperty();

    private ILogger logger;

    private final ObjectProperty<ObservableList<TemperaturesUnit>> units =
            new SimpleObjectProperty<>(
                    FXCollections.observableArrayList(TemperaturesUnit.values()));
    private final ObjectProperty<TemperaturesUnit> unit =
            new SimpleObjectProperty<TemperaturesUnit>();

    private final StringProperty status = new SimpleStringProperty();

    public List<String> getLogList() {
        return logger.getLog();
    }

    public String getLog() {
        return log.get();
    }

    public StringProperty logProperty() {
        return log;
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
        unit.set(TemperaturesUnit.FAHRENHEIT);
        status.set(Status.READY.toString());
        log.set(EMPTY_STRING);
    }

    public StringProperty convertFromProperty() {
        return convertFrom;
    }

    public StringProperty convertToProperty() {
        return convertTo;
    }

    public final String getConvertTo() {
        return convertTo.get();
    }

    public final String getConvertFrom() {
        return convertFrom.get();
    }

    public ObjectProperty<ObservableList<TemperaturesUnit>> unitsProperty() {
        return units;
    }

    public final ObservableList<TemperaturesUnit> getUnits() {
        return units.get();
    }

    public ObjectProperty<TemperaturesUnit> unitProperty() {
        return unit;
    }

    public TemperaturesUnit getUnit() {
        return unit.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    public void checkInputValues() {
        if (convertFrom.get().isEmpty()) {
            status.set(Status.WAITING.toString());
        }
        try {
            if (!convertFrom.get().isEmpty()) {
                Double.parseDouble(convertFrom.get());
                status.set(Status.READY.toString());
            }
        } catch (NumberFormatException nfe) {
            addLog(String.format(LogMessage.VALUE_FROM_IS_NOT_CORRECT, convertFrom.get()));
            status.set(Status.BAD_FORMAT.toString());
        }
    }

    public void convert() {
        checkInputValues();
        if (status.get() != Status.READY.toString()) {
            return;
        }

        try {
            double valueToConvert = Double.parseDouble(convertFrom.get());
            double result = TemperaturesConverter.convert(valueToConvert, unit.get());

            addLog(String.format(LogMessage.CONVERT_WAS_PRESSED,
                    valueToConvert, CELSIUS_SYMBOL, result, unit.get()));

            convertTo.set(String.valueOf(result));
            status.set(Status.SUCCESS.toString());
        } catch (TemperaturesConverterExceptions ex) {
            addLog(String.format(LogMessage.VALUE_FROM_IS_NOT_CORRECT, convertFrom.get()));
            status.set(Status.ERROR.toString());
        }
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger can't be null");
        }
        this.logger = logger;
    }

    private void addLog(final String message) {
        logger.log(message);
        StringBuilder logMsg = new StringBuilder();
        for (String line : logger.getLog()) {
            logMsg.append(line).append("\n");
        }
        log.set(logMsg.toString());
    }
}

