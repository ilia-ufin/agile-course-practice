package ru.unn.agile.quadraticequation.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.quadraticequation.viewmodel.ViewModel;

public class QuadraticEquation {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private TextField roots;
    @FXML
    private Button btnSolve;

    @FXML
    void initialize() {
        a.textProperty().bindBidirectional(viewModel.aProperty());
        b.textProperty().bindBidirectional(viewModel.bProperty());
        c.textProperty().bindBidirectional(viewModel.cProperty());
        roots.textProperty().bindBidirectional(viewModel.rootsProperty());

        btnSolve.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.solve();
            }
        });
    }
}
