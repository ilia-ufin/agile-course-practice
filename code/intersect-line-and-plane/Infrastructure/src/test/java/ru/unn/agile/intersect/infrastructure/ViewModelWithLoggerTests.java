package ru.unn.agile.intersect.infrastructure;

import ru.unn.agile.intersect.viewmodel.ViewModel;
import ru.unn.agile.intersect.viewmodel.ViewModelTest;

public class ViewModelWithLoggerTests extends ViewModelTest {
    @Override
    public void createViewModel() {
        Logger realLogger =
            new Logger("./ViewModelWithTxtLoggerTests-lab3-legacy.log");
        super.setViewModel(new ViewModel(realLogger));
    }
}
