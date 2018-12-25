package ru.unn.agile.BookStore.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.BookStore.ViewModel.ViewModel;
import ru.unn.agile.BookStore.Infrastructure.*;

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
        viewModel.setLogger(new TxtLogger("./BookStore.log"));

        final ChangeListener<Boolean> focusChangesListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChanged(oldValue, newValue);
            }
        };

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        books1.textProperty().bindBidirectional(viewModel.books1Property());
        books1.focusedProperty().addListener(focusChangesListener);

        books2.textProperty().bindBidirectional(viewModel.books2Property());
        books2.focusedProperty().addListener(focusChangesListener);

        books3.textProperty().bindBidirectional(viewModel.books3Property());
        books3.focusedProperty().addListener(focusChangesListener);

        books4.textProperty().bindBidirectional(viewModel.books4Property());
        books4.focusedProperty().addListener(focusChangesListener);

        books5.textProperty().bindBidirectional(viewModel.books5Property());
        books5.focusedProperty().addListener(focusChangesListener);

        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
