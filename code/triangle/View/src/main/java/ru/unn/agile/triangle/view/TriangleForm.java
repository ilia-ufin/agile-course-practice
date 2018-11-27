package ru.unn.agile.triangle.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.triangle.viewmodel.ViewModel;

public class TriangleForm {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField aX;
    @FXML
    private TextField bX;
    @FXML
    private TextField cX;
    @FXML
    private TextField aY;
    @FXML
    private TextField bY;
    @FXML
    private TextField cY;
    @FXML
    private Button btnCalcPerimeter;
    @FXML
    private Button btnCalcSquare;
    @FXML
    private Button btnCalcLengthAB;
    @FXML
    private Button btnCalcLengthBC;
    @FXML
    private Button btnCalcLengthCA;
    @FXML
    private Button btnCalcAngleCAB;
    @FXML
    private Button btnCalcAngleABC;
    @FXML
    private Button btnCalcAngleBCA;

    @FXML
    void initialize() {

        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        aX.textProperty().bindBidirectional(viewModel.aXProperty());
        bX.textProperty().bindBidirectional(viewModel.bXProperty());
        cX.textProperty().bindBidirectional(viewModel.cXProperty());
        aY.textProperty().bindBidirectional(viewModel.aYProperty());
        bY.textProperty().bindBidirectional(viewModel.bYProperty());
        cY.textProperty().bindBidirectional(viewModel.cYProperty());


        btnCalcPerimeter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.perimeter();
            }
        });

        btnCalcSquare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.square();
            }
        });

        btnCalcLengthAB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getLengthAB();
            }
        });

        btnCalcLengthBC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getLengthBC();
            }
        });

        btnCalcLengthCA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getLengthCA();
            }
        });

        btnCalcAngleCAB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getCABAngle();
            }
        });

        btnCalcAngleABC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getABCAngle();
            }
        });

        btnCalcAngleBCA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.getBCAAngle();
            }
        });
    }
}
