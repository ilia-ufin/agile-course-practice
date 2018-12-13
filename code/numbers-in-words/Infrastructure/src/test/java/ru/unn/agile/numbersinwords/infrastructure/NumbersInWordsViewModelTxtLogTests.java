package ru.unn.agile.numbersinwords.infrastructure;

import ru.unn.agile.numbersinwords.viewmodel.NumbersInWordsViewModelLogTests;

import java.io.IOException;

public class NumbersInWordsViewModelTxtLogTests extends NumbersInWordsViewModelLogTests {
    @Override
    public void setUp() throws IOException {
        TxtLogger realLogger = new TxtLogger("NumbersInWordsViewModelTxtLogTests.log");
        this.setLogger(realLogger);
    }
}
