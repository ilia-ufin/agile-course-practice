package ru.unn.agile.mortgagecalculator.infrastructure;

import ru.unn.agile.mortgagecalculator.viewmodel.legacy.ViewModelTests;
import ru.unn.agile.mortgagecalculator.viewmodel.legacy.ViewModel;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {

    @Override
    public void setUpEmptyExample() {
        TxtLogger realLogger = new TxtLogger("./ViewModelIncludingTestsTextLogger.txt");

        setViewModel(new ViewModel(realLogger));

        setDefaultSettings();
    }

}
