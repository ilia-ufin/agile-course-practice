package ru.unn.agile.BookStore.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.BookStore.ViewModel.ViewModel;

public class BookStore {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField books1;
    @FXML
    private TextField books2;
    @FXML
    private TextField books3;
    @FXML
    private TextField books4;
    @FXML
    private TextField books5;
    @FXML
    private Button btnCalculate;

    @FXML
    void initialize() {

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        books1.textProperty().bindBidirectional(viewModel.books1Property());
        books2.textProperty().bindBidirectional(viewModel.books2Property());
        books3.textProperty().bindBidirectional(viewModel.books3Property());
        books4.textProperty().bindBidirectional(viewModel.books4Property());
        books5.textProperty().bindBidirectional(viewModel.books5Property());

        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
