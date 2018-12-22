package ru.unn.agile.huffman.infrastructure;

import ru.unn.agile.huffman.viewmodel.ViewModelTests;
import ru.unn.agile.huffman.viewmodel.ViewModel;

public class ViewModelTestWithTextLogger extends ViewModelTests {
    @Override
    public void setUp() {
        TextFileLogger logger = new TextFileLogger("./textLog.log");
        super.setViewModel(new ViewModel(logger));
    }
}
