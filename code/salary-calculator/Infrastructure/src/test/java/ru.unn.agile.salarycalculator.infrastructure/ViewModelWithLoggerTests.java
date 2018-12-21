package ru.unn.agile.salarycalculator.infrastructure;

import ru.unn.agile.salarycalculator.viewmodel.legacy.ViewModel;
import ru.unn.agile.salarycalculator.viewmodel.legacy.ViewModelTests;

public class ViewModelWithLoggerTests extends ViewModelTests {

    public void setUp() {
        Logger realLogger =
                new Logger("./ViewModelWithLoggerTests-lab3.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
