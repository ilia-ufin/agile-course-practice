package ru.unn.agile.depositconverter.infrastructure;

import ru.unn.agile.depositconverter.viewmodel.DepositCalculatorViewModelTests;
import ru.unn.agile.depositconverter.viewmodel.DepositCalculatorViewModel;

public class ViewModelWithFileLoggerTests extends DepositCalculatorViewModelTests {
    @Override
    public void setUp() {
        FileLogger realLogger =
            new FileLogger("./ViewModelWithFileLoggerTests-deposit-calculator.log");
        super.setViewModel(new DepositCalculatorViewModel(realLogger));
    }
}
