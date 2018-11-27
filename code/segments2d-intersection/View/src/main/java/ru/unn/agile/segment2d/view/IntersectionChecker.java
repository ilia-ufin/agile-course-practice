package ru.unn.agile.segment2d.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import ru.unn.agile.segment2d.viewmodel.ViewModel;

public class IntersectionChecker {
    @FXML
    private ViewModel viewModel;

    @FXML
    private TextField firstSegmentFirstPointCoordX;
    @FXML
    private TextField firstSegmentFirstPointCoordY;
    @FXML
    private TextField firstSegmentSecondPointCoordX;
    @FXML
    private TextField firstSegmentSecondPointCoordY;

    @FXML
    private TextField secondSegmentFirstPointCoordX;
    @FXML
    private TextField secondSegmentFirstPointCoordY;
    @FXML
    private TextField secondSegmentSecondPointCoordX;
    @FXML
    private TextField secondSegmentSecondPointCoordY;

    @FXML
    private Button buttonCheckIntersection;

    @FXML
    void initialize() {
        firstSegmentFirstPointCoordX.textProperty().bindBidirectional(
                viewModel.firstSegmentFirstPointCoordXProperty());
        firstSegmentFirstPointCoordY.textProperty().bindBidirectional(
                viewModel.firstSegmentFirstPointCoordYProperty());
        firstSegmentSecondPointCoordX.textProperty().bindBidirectional(
                viewModel.firstSegmentSecondPointCoordXProperty());
        firstSegmentSecondPointCoordY.textProperty().bindBidirectional(
                viewModel.firstSegmentSecondPointCoordYProperty());

        secondSegmentFirstPointCoordX.textProperty().bindBidirectional(
                viewModel.secondSegmentFirstPointCoordXProperty());
        secondSegmentFirstPointCoordY.textProperty().bindBidirectional(
                viewModel.secondSegmentFirstPointCoordYProperty());
        secondSegmentSecondPointCoordX.textProperty().bindBidirectional(
                viewModel.secondSegmentSecondPointCoordXProperty());
        secondSegmentSecondPointCoordY.textProperty().bindBidirectional(
                viewModel.secondSegmentSecondPointCoordYProperty());

        buttonCheckIntersection.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.checkIntersection();
            }
        });
    }
}
