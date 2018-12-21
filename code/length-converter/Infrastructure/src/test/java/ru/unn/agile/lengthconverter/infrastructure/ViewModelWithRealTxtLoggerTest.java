package ru.unn.agile.lengthconverter.infrastructure;

import ru.unn.agile.lengthconverter.viewmodel.ViewModel;
import ru.unn.agile.lengthconverter.viewmodel.ViewModelTest;

public class ViewModelWithRealTxtLoggerTest extends ViewModelTest {
    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("ViewModelWithTxtLoggerTests.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
