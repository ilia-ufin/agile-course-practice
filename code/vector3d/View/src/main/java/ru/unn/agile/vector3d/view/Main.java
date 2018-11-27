package ru.unn.agile.vector3d.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Vector3DCalculator.fxml"));
        primaryStage.setTitle("Vector3D");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}
