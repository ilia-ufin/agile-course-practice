package ru.unn.agile.myhashmap.infrastructure;

import ru.unn.agile.myhashmap.viewmodel.ViewModel;
import ru.unn.agile.myhashmap.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
