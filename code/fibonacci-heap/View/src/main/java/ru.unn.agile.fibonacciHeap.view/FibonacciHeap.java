package ru.unn.agile.fibonacciHeap.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.unn.agile.fibonacciHeap.viewModel.ViewModel;

public class FibonacciHeap {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField inputValue;
    @FXML
    private Label rootAmount;
    @FXML
    private Label minElem;
    @FXML
    private Label alert;
    @FXML
    private Button btnPut;

    @FXML
    void initialize() {
        inputValue.textProperty().bindBidirectional(viewModel.inputValueProperty());
        rootAmount.textProperty().bind(viewModel.heapLengthProperty());
        minElem.textProperty().bind(viewModel.minElemProperty());
        alert.textProperty().bind(viewModel.errorProperty());

        btnPut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.addElem();
            }
        });
    }
}
