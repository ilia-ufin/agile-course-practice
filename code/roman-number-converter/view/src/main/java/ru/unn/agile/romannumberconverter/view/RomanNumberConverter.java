package ru.unn.agile.romannumberconverter.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.romannumberconverter.viewmodel.ViewModel;
import ru.unn.agile.romannumberconverter.infrastructure.TxtLogger;

public class RomanNumberConverter {
    @FXML
    private ViewModel viewModel;

    @FXML
    private TextField romanNumber;

    @FXML
    private TextField arabicNumber;

    @FXML
    private Button buttonConvertToArabic;

    @FXML
    private Button buttonConvertToRoman;

    @FXML
    void initialize() {
        viewModel.setLogger(new TxtLogger("./RomanNumberConverter.log"));

        romanNumber.textProperty().bindBidirectional(viewModel.romanValueStrProperty());
        arabicNumber.textProperty().bindBidirectional(viewModel.arabicValueStrProperty());

        buttonConvertToArabic.setOnAction(event -> viewModel.convertRomanToArabic());
        buttonConvertToRoman.setOnAction(event -> viewModel.convertArabicToRoman());
    }
}
