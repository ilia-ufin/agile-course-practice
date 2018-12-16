package ru.unn.agile.stack.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import ru.unn.agile.stack.viewmodel.ViewModel;

public class StackView {
    @FXML
    private ViewModel viewModel;

    @FXML
    private Button popElementButton;
    @FXML
    private Button addElementButton;
    @FXML
    private TextField inputNewElemField;

    @FXML
    void initialize() {
        inputNewElemField.textProperty().bindBidirectional(viewModel.addingElementProperty());

        addElementButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.addElement();
            }
        });

        popElementButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.popElement();
            }
        });
    }
}
