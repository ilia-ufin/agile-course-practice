package ru.unn.agile.modifideStack.infrastructure;

import ru.unn.agile.modifideStack.viewmodel.ViewModel;
import ru.unn.agile.modifideStack.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUpModel() {
        TxtLogger realLogger = new TxtLogger("./ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
