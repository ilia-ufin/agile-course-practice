package ru.unn.agile.polynomial.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ru.unn.agile.polynomial.viewmodel.ViewModel;

public class PolynomialCalculator {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField firstPolynomial;
    @FXML
    private TextField secondPolynomial;

    @FXML
    void initialize() {
        firstPolynomial.textProperty().bindBidirectional(viewModel.firstPolynomialStrProperty());
        secondPolynomial.textProperty().bindBidirectional(viewModel.secondPolynomialStrProperty());
    }

    @FXML
    public void handleAddButtonAction(final ActionEvent event) {
        viewModel.add();
    }

    @FXML
    public void handleSubtractButtonAction(final ActionEvent event) {
        viewModel.subtract();
    }

    @FXML
    public void handleMultiplyButtonAction(final ActionEvent event) {
        viewModel.multiply();
    }
}
