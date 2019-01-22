package ru.unn.agile.AVL.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.AVL.viewmodel.*;

public class AVLTree {
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
    }
}
