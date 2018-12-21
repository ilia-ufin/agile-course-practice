package ru.unn.agile.shapevolume.viewmodel;

public enum LogMessages {
    FIRST_ARGUMENT_INPUTED("Первый аргумент введен, значение - "),
    SECOND_ARGUMENT_INPUTED("Второй аргумент введен, значение - "),
    THIRD_ARGUMENT_INPUTED("Третий аргумент введен, значение - "),
    SHAPE_CHANGED("Фигура была изменена на "),
    CALCULATION_PERFORMED("Вычисления выполнены, результат - "),
    SOMETHING_WENT_WRONG("Во время записи логов"
            + " произошла неизвестная проблема");


    private final String name;

    LogMessages(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
