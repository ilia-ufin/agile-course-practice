package ru.unn.agile.triangle.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.triangle.viewmodel.ViewModel;
import ru.unn.agile.triangle.infrastructure.TxtLogger;


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
        viewModel.setLogger(new TxtLogger("./TxtLogger-lab3.log"));

        final ChangeListener<Boolean> booleanChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValueAttribute, final Boolean newValueAttribute) {
                viewModel.checkOnFocusChanged(oldValueAttribute, newValueAttribute);
            }
        };

        aX.textProperty().bindBidirectional(viewModel.aXProperty());
        aX.focusedProperty().addListener(booleanChangeListener);
        aY.textProperty().bindBidirectional(viewModel.aYProperty());
        aY.focusedProperty().addListener(booleanChangeListener);
        bX.textProperty().bindBidirectional(viewModel.bXProperty());
        bX.focusedProperty().addListener(booleanChangeListener);
        bY.textProperty().bindBidirectional(viewModel.bYProperty());
        bY.focusedProperty().addListener(booleanChangeListener);
        cX.textProperty().bindBidirectional(viewModel.cXProperty());
        cX.focusedProperty().addListener(booleanChangeListener);
        cY.textProperty().bindBidirectional(viewModel.cYProperty());
        cY.focusedProperty().addListener(booleanChangeListener);


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
