package ru.unn.agile.stack.infrastructure;

import ru.unn.agile.stack.viewmodel.ViewModel;
import ru.unn.agile.stack.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
