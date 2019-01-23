package ru.unn.agile.stringcalculator.infrastructure;

import ru.unn.agile.stringcalculator.viewmodel.ViewModel;
import ru.unn.agile.stringcalculator.viewmodel.ViewModelWithLoggerTest;

public class ViewModelWithTxtLoggerTest extends ViewModelWithLoggerTest {
    @Override
    public void setUp() {
        TxtLogger logger =
            new TxtLogger("./StringCalculatorLogTest.log");
        super.setExternalViewModel(new ViewModel(logger));
    }
}
