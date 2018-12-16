package ru.unn.agile.mathstatistics.infrastructure;

import ru.unn.agile.mathstatistics.viewmodel.ViewModel;
import ru.unn.agile.mathstatistics.viewmodel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {

    @Override
    public void setUp() {
        TxtLogger realLogger = new TxtLogger("./ViewModelIncludingTestsTextLogger.txt");

        super.setViewModel(new ViewModel(realLogger));
    }

}
