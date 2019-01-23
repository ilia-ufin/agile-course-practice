package ru.unn.agile.polynomial.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.polynomial.viewmodel.ViewModel;
import ru.unn.agile.polynomial.infrastructure.TextLogger;

public class PolynomialCalculator {
    @FXML
    private ViewModel viewModel;

    @FXML
    private TextField firstPolynomial;
    @FXML
    private TextField secondPolynomial;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnSub;
    @FXML
    private Button btnMult;

    @FXML
    void initialize() {
        viewModel.setLogger(new TextLogger("./PolynomialCalculator.log"));
        firstPolynomial.textProperty().bindBidirectional(viewModel.firstPolynomialStrProperty());
        secondPolynomial.textProperty().bindBidirectional(viewModel.secondPolynomialStrProperty());

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.add();
            }
        });

        btnSub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.subtract();
            }
        });

        btnMult.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.multiply();
            }
        });
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
