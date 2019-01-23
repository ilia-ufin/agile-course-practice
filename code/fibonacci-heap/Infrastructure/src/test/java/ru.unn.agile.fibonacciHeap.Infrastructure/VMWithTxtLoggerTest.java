package ru.unn.agile.fibonacciHeap.Infrastructure;

import ru.unn.agile.fibonacciHeap.viewModel.ViewModel;
import ru.unn.agile.fibonacciHeap.viewModel.ViewModelTest;

public class VMWithTxtLoggerTest extends ViewModelTest {

    @Override
    public void setUp() {
        DummyLogger logger = new DummyLogger("./DummyLogger.txt");

        super.setViewModel(new ViewModel(logger));
    }
}
