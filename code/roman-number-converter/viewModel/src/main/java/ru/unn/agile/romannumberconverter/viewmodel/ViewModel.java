package ru.unn.agile.romannumberconverter.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.romannumberconverter.model.RomanNumberConverter;
import ru.unn.agile.romannumberconverter.model.errorhandling.ArabicOutOfRangeException;
import ru.unn.agile.romannumberconverter.model.errorhandling.RomanIncorrectValueExeption;

import java.util.List;

public class ViewModel {
    private static final String ERR_WRONG_ROMAN_VALUE =
            "Римское число введено неверно!";
    private static final String ERR_WRONG_ARABIC_VALUE =
            "Арабское число введено неверно!";
    private static final String SUCCESSFUL_STATUS =
            "Успешно";
    private static final String ERR_OUT_OF_RANGE =
            "Введено число не из интервала от 1 до 3999!";

    private StringProperty arabicValueStr = new SimpleStringProperty();
    private StringProperty romanValueStr = new SimpleStringProperty();
    private StringProperty convertStatus = new SimpleStringProperty();
    private StringProperty log = new SimpleStringProperty();

    private ILogger logger;

    public StringProperty arabicValueStrProperty() {
        return arabicValueStr;
    }

    public String getArabicValueStr() {
        return arabicValueStr.get();
    }

    public void setArabicValueStr(final String arabicNewVal) {
        arabicValueStr.set(arabicNewVal);
    }

    public StringProperty romanValueStrProperty() {
        return romanValueStr;
    }

    public String getRomanValueStr() {
        return romanValueStr.get();
    }

    public void setRomanValueStr(final String romanNewVal) {
        romanValueStr.set(romanNewVal);
    }

    public String getConvertStatus() {
        return convertStatus.get();
    }

    public StringProperty logProperty() {
        return log;
    }

    public String getLog() {
        return log.get();
    }

    public ViewModel() {
        initialize();
    }

    public ViewModel(final ILogger logger) {
        initialize();
        setLogger(logger);
    }

    private void initialize() {
        arabicValueStr.set("");
        romanValueStr.set("");
        convertStatus.set("");
        log.set("");
    }

    public void convertArabicToRoman() {
        try {
            int intArabic = Integer.parseInt(getArabicValueStr());
            setRomanValueStr(RomanNumberConverter.convertToRoman(intArabic));
            convertStatus.set(SUCCESSFUL_STATUS);
        } catch (ArabicOutOfRangeException ex) {
            convertStatus.set(ERR_OUT_OF_RANGE);
        } catch (NumberFormatException ex) {
            convertStatus.set(ERR_WRONG_ARABIC_VALUE);
        } finally {
            writeToLog(getRomanConvertededStateLogMessage());
        }
    }

    public void convertRomanToArabic() {
        try {
            int intArabic = RomanNumberConverter.convertToArabic(getRomanValueStr());
            setArabicValueStr(Integer.toString(intArabic));
            convertStatus.set(SUCCESSFUL_STATUS);
        } catch (RomanIncorrectValueExeption ex) {
            convertStatus.set(ERR_WRONG_ROMAN_VALUE);
        } finally {
            writeToLog(getArabicConvertededStateLogMessage());
        }
    }

    public final void setLogger(final ILogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("Logger must be set!");
        }
        this.logger = logger;
    }

    public List<String> getLogList() {
        return logger.getLog();
    }

    private void writeToLog(final String message) {
        logger.log(message);
        StringBuilder stringLogMessages = new StringBuilder();

        for (String line : getLogList()) {
            stringLogMessages.append(line).append("\n");
        }

        log.set(stringLogMessages.toString());
    }

    private String getFormStateLogMessage() {
        return String.format(
                LogMessages.CURRENT_STATE,
                arabicValueStr.get(),
                romanValueStr.get(),
                convertStatus.get()
        );
    }

    private String getArabicConvertededStateLogMessage() {
        return LogMessages.TO_ARABIC_PRESSED + " "
                + getFormStateLogMessage();
    }

    private String getRomanConvertededStateLogMessage() {
        return LogMessages.TO_ROMAN_PRESSED + " "
                + getFormStateLogMessage();
    }

    public static final class LogMessages {
        public static final String CURRENT_STATE =
                "Current state: "
                        + "Arabic number (%s), "
                        + "Roman number (%s), "
                        + "Status (%s).";
        public static final String TO_ROMAN_PRESSED =
                "ToRoman button was pressed.";
        public static final String TO_ARABIC_PRESSED =
                "ToArabic button was pressed.";
    }
}
