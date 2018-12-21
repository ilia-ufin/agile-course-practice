package ru.unn.agile.calculator.infrastructure;

import ru.unn.agile.calculator.viewmodel.ViewModel;
import ru.unn.agile.calculator.viewmodel.ViewModelTests;

public class ViewModelTestsWithFileLogger extends ViewModelTests {
    @Override
    public void setUp() {
        FileLogger realLogger =
                new FileLogger("./numeral-systems-ViewModelTestsWithFileLogger.log");
        ViewModel viewModel = new ViewModel();
        viewModel.setLogger(realLogger);
        super.setExternalViewModel(viewModel);
    }
}
