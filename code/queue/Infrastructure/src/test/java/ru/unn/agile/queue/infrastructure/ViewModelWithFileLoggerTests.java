package ru.unn.agile.queue.infrastructure;

import ru.unn.agile.queue.viewmodel.ViewModel;
import ru.unn.agile.queue.viewmodel.ViewModelTests;

public class ViewModelWithFileLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        FileLogger realLogger = new FileLogger("./ViewModelWithFileLoggerTests.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
