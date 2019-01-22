package ru.unn.agile.LcdDigits.Infrastructure;

import ru.unn.agile.LcdDigits.viewmodel.ViewModel;
import ru.unn.agile.LcdDigits.viewmodel.ViewModelTests;


public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./LcdDigits-ViewModelWithTxtLoggerTest.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
