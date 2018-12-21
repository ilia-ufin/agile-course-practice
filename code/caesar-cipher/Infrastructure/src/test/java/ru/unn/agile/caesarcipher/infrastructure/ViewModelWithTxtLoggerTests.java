package ru.unn.agile.caesarcipher.infrastructure;

import ru.unn.agile.caesarcipher.viewmodel.ViewModel;
import ru.unn.agile.caesarcipher.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("ViewModelWithTxtLoggerTests.log");
        this.setViewModel(new ViewModel(realLogger));
    }
}
