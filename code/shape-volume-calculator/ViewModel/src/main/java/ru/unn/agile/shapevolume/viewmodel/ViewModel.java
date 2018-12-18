package ru.unn.agile.shapevolume.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.shapevolume.model.Cuboid;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.shapevolume.model.RegularPolygonPrism;
import sun.rmi.runtime.Log;

import java.util.*;

enum Status {
    WAITING("Ожидание ввода данных"),
    INVALID_ARGUMENTS("Некорректные входные данные");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

enum LogMessages {
    FIRST_ARGUMENT_INPUTED("Первый аргумент введен, значение - "),
    SECOND_ARGUMENT_INPUTED("Второй аргумент введен, значение - "),
    THIRD_ARGUMENT_INPUTED("Третий аргумент введен, значение - "),
    SHAPE_CHANGED("Фигура была изменена на "),
    CALCULATION_PERFORMED("Вычисления выполнены, результат - ");


    private final String name;

    LogMessages(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

public class ViewModel {
    private final StringProperty firstArgumentName = new SimpleStringProperty();
    private final StringProperty secondArgumentName = new SimpleStringProperty();
    private final StringProperty thirdArgumentName = new SimpleStringProperty();

    private final StringProperty firstArgumentValue = new SimpleStringProperty();
    private final StringProperty secondArgumentValue = new SimpleStringProperty();
    private final StringProperty thirdArgumentValue = new SimpleStringProperty();

    private final ObjectProperty<Shape> currentShape = new SimpleObjectProperty<>();
    private final StringProperty result = new SimpleStringProperty();


    private final ObjectProperty<ObservableList<Shape>> shapes =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Shape.values()));


    private ILogger logger;

    public static final Map<Shape, String[]> SHAPE_TO_PARAMETERS_NAMES =
            Collections.unmodifiableMap(new HashMap<Shape, String[]>() {{
                put(Shape.UNKNOWN, new String[]{"", "", ""});
                put(Shape.CUBE, new String[]{"a", "b", "c"});
                put(Shape.REGULAR_POLYGON_PRISM, new String[]{
                        "Количество сторон",
                        "Длина стороны",
                        "Высота призмы"});
            }});


    public static final String DEFAULT_VALUE = "";

    public ViewModel() {
        init();
    }

    public ViewModel(ILogger logger) {
        this.logger = logger;
        init();
    }

    private void init() {
        currentShape.set(Shape.UNKNOWN);
        updateArgumentsNames(Shape.UNKNOWN);
        firstArgumentValue.set(DEFAULT_VALUE);
        secondArgumentValue.set(DEFAULT_VALUE);
        thirdArgumentValue.set(DEFAULT_VALUE);

        result.set(Status.WAITING.toString());

        currentShape.addListener((ObservableValue<? extends Shape> observable,
                                  Shape oldValue, Shape newValue) -> {
            if (!oldValue.equals(newValue)) {
                updateArgumentsNames(newValue);
            }
        });

        setupChangeValueListeners();
    }

    private void setupChangeValueListeners() {
        final Map<String, StringProperty> arguments = new HashMap<>();
        arguments.put(LogMessages.FIRST_ARGUMENT_INPUTED.toString(), firstArgumentValue);
        arguments.put(LogMessages.SECOND_ARGUMENT_INPUTED.toString(), secondArgumentValue);
        arguments.put(LogMessages.THIRD_ARGUMENT_INPUTED.toString(), thirdArgumentValue);

        for (Map.Entry<String, StringProperty> argument : arguments.entrySet()) {
            argument.getValue().addListener((ObservableValue<? extends String> observable,
                                  String oldValue, String newValue) -> {
                logger.log(argument.getKey() + " " + newValue);
                if (!oldValue.equals(newValue)) {
                    calculate();
                }
            });
        }
    }


    private void updateArgumentsNames(final Shape shape) {
        String[] parametersNames = SHAPE_TO_PARAMETERS_NAMES.get(Shape.UNKNOWN);
        if (SHAPE_TO_PARAMETERS_NAMES.containsKey(shape)) {
            parametersNames = SHAPE_TO_PARAMETERS_NAMES.get(shape);
        }
        firstArgumentName.set(parametersNames[0]);
        secondArgumentName.set(parametersNames[1]);
        thirdArgumentName.set(parametersNames[2]);
        calculate();
    }

    private void calculate() {
        final String firstArgumentString = firstArgumentValue.get();
        final String secondArgumentString = secondArgumentValue.get();
        final String thirdArgumentString = thirdArgumentValue.get();
        if ("".equals(firstArgumentString) || "".equals(secondArgumentString)
                || "".equals(thirdArgumentString)) {
            result.set(Status.WAITING.toString());
            return;
        }

        int first, second, third;
        try {
            first = Integer.parseInt(firstArgumentString);
            second = Integer.parseInt(secondArgumentString);
            third = Integer.parseInt(thirdArgumentString);
        } catch (NumberFormatException nfe) {
            result.set(Status.INVALID_ARGUMENTS.toString());
            return;
        }

        try {
            Double volume = null;
            switch (currentShape.get()) {
                case CUBE:
                    volume = new Cuboid(first, second, third).getVolume();
                    break;
                case REGULAR_POLYGON_PRISM:
                    volume = new RegularPolygonPrism(first, second, third).getVolume();
                    break;
                default:
                    break;
            }
            if (volume != null) {
                String formattedVolume = String.format(Locale.US, "%.3f", volume);
                logger.log(LogMessages.CALCULATION_PERFORMED + " " + formattedVolume);
                result.set(formattedVolume);
            } else {
                result.set(Status.INVALID_ARGUMENTS.toString());
            }
        } catch (Exception ex) {
            result.set(Status.INVALID_ARGUMENTS.toString());
        }
    }

    public StringProperty firstArgumentValueProperty() {
        return firstArgumentValue;
    }

    public StringProperty secondArgumentValueProperty() {
        return secondArgumentValue;
    }

    public StringProperty thirdArgumentValueProperty() {
        return thirdArgumentValue;
    }

    public StringProperty firstArgumentNameProperty() {
        return firstArgumentName;
    }

    public StringProperty secondArgumentNameProperty() {
        return secondArgumentName;
    }

    public StringProperty thirdArgumentNameProperty() {
        return thirdArgumentName;
    }

    public String getFirstArgumentName() {
        return firstArgumentName.get();
    }

    public String getSecondArgumentName() {
        return secondArgumentName.get();
    }

    public String getThirdArgumentName() {
        return thirdArgumentName.get();
    }

    public ObjectProperty<Shape> currentShapeProperty() {
        return currentShape;
    }

    public final ObservableList<Shape> getShapes() {
        return shapes.get();
    }

    public String getResult() {
        return result.get();
    }

    public ILogger getLogger() {
        return logger;
    }

    public void setLogger(ILogger logger) {
        this.logger = logger;
    }


}


