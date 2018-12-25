package ru.unn.agile.BookStore.Infrastructure;

import ru.unn.agile.BookStore.ViewModel.ViewModel;
import ru.unn.agile.BookStore.ViewModel.ViewModelTests;

public class ViewModelWithTxtLoggerTests extends ViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./BookStore-ViewModelWithTxtLoggerTest.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
