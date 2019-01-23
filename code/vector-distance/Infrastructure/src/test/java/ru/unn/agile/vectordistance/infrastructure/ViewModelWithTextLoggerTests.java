package ru.unn.agile.vectordistance.infrastructure;

import ru.unn.agile.vectordistance.viewmodel.ViewModel;
import ru.unn.agile.vectordistance.viewmodel.ViewModelTests;

public class ViewModelWithTextLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TextLogger realLogger = new TextLogger("./ViewModelWithFileLoggerTests.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
