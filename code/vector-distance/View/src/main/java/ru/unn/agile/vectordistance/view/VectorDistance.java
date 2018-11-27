package ru.unn.agile.vectordistance.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.unn.agile.vectordistance.model.VectorDistance.Distance;
import ru.unn.agile.vectordistance.viewmodel.ViewModel;

public class VectorDistance {
    @FXML
    private ViewModel viewModel;
    @FXML
    private ComboBox<Distance> cbDistances;
    @FXML
    private TextField txtVectorX;
    @FXML
    private TextField txtVectorY;
    @FXML
    private Button btnCalc;
    @FXML
    private Label lbResult;
    @FXML
    private Label lbStatus;

    @FXML
    void initialize() {
        txtVectorX.textProperty().bindBidirectional(viewModel.vectorXProperty());
        txtVectorY.textProperty().bindBidirectional(viewModel.vectorYProperty());
        lbResult.textProperty().bindBidirectional(viewModel.resultProperty());
        lbStatus.textProperty().bindBidirectional(viewModel.statusProperty());
        cbDistances.valueProperty().bindBidirectional(viewModel.distanceProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
