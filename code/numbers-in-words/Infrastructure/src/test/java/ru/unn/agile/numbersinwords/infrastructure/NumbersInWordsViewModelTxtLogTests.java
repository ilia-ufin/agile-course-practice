package ru.unn.agile.numbersinwords.infrastructure;

import ru.unn.agile.numbersinwords.viewmodel.NumbersInWordsViewModelLogTests;
import java.io.IOException;

public class NumbersInWordsViewModelTxtLogTests extends NumbersInWordsViewModelLogTests {
    private static final String LOG_NAME = "NumbersInWordsViewModelTxtLogTests.log";

    @Override
    public void setUp() throws IOException {
        TxtLogger realLogger = new TxtLogger(LOG_NAME);
        this.setLogger(realLogger);
    }

}
