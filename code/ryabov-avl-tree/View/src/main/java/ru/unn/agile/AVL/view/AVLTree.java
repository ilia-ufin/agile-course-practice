package ru.unn.agile.AVL.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.AVL.viewmodel.*;

public class AVLTree {
    @FXML
    private ViewModel viewModel;

    @FXML
    private Button performAction;
    @FXML
    private ChoiceBox<Operation> actionType;
    @FXML
    private TextField key;
    @FXML
    private TextField value;

    @FXML
    void initialize() {
        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        actionType.valueProperty().bindBidirectional(viewModel.operationProperty());

        key.textProperty().bindBidirectional(viewModel.keyProperty());
        value.textProperty().bindBidirectional(viewModel.valueProperty());


        performAction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.doAction();
            }
        });
    }
}
