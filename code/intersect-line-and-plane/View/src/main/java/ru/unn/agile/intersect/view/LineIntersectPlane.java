package ru.unn.agile.intersect.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.intersect.infrastructure.Logger;

import ru.unn.agile.intersect.viewmodel.ViewModel;

public class LineIntersectPlane {
    @FXML
    private ViewModel viewModel;

    @FXML
    private TextField coordXFirstPlanePoint;
    @FXML
    private TextField coordYFirstPlanePoint;
    @FXML
    private TextField coordZFirstPlanePoint;

    @FXML
    private TextField coordXSecondPlanePoint;
    @FXML
    private TextField coordYSecondPlanePoint;
    @FXML
    private TextField coordZSecondPlanePoint;

    @FXML
    private TextField coordXThirdPlanePoint;
    @FXML
    private TextField coordYThirdPlanePoint;
    @FXML
    private TextField coordZThirdPlanePoint;

    @FXML
    private TextField coordXFirstLinePoint;
    @FXML
    private TextField coordYFirstLinePoint;
    @FXML
    private TextField coordZFirstLinePoint;

    @FXML
    private TextField coordXSecondLinePoint;
    @FXML
    private TextField coordYSecondLinePoint;
    @FXML
    private TextField coordZSecondLinePoint;

    @FXML
    private Button checkIntersectionButton;

    @FXML
    void initialize() {
        viewModel.setLogger(new Logger("./IntersectLogger.log"));

        coordXFirstPlanePoint.textProperty().bindBidirectional(
                viewModel.coordXFirstPlanePointProperty());
        coordYFirstPlanePoint.textProperty().bindBidirectional(
                viewModel.coordYFirstPlanePointProperty());
        coordZFirstPlanePoint.textProperty().bindBidirectional(
                viewModel.coordZFirstPlanePointProperty());

        coordXSecondPlanePoint.textProperty().bindBidirectional(
                viewModel.coordXSecondPlanePointProperty());
        coordYSecondPlanePoint.textProperty().bindBidirectional(
                viewModel.coordYSecondPlanePointProperty());
        coordZSecondPlanePoint.textProperty().bindBidirectional(
                viewModel.coordZSecondPlanePointProperty());

        coordXThirdPlanePoint.textProperty().bindBidirectional(
                viewModel.coordXThirdPlanePointProperty());
        coordYThirdPlanePoint.textProperty().bindBidirectional(
                viewModel.coordYThirdPlanePointProperty());
        coordZThirdPlanePoint.textProperty().bindBidirectional(
                viewModel.coordZThirdPlanePointProperty());

        coordXFirstLinePoint.textProperty().bindBidirectional(
                viewModel.coordXFirstLinePointProperty());
        coordYFirstLinePoint.textProperty().bindBidirectional(
                viewModel.coordYFirstLinePointProperty());
        coordZFirstLinePoint.textProperty().bindBidirectional(
                viewModel.coordZFirstLinePointProperty());

        coordXSecondLinePoint.textProperty().bindBidirectional(
                viewModel.coordXSecondLinePointProperty());
        coordYSecondLinePoint.textProperty().bindBidirectional(
                viewModel.coordYSecondLinePointProperty());
        coordZSecondLinePoint.textProperty().bindBidirectional(
                viewModel.coordZSecondLinePointProperty());

        checkIntersectionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.checkLineAndPlaneIntersection();
            }
        });
    }
}
