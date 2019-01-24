package ru.unn.agile.MyHashMap.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int WIEGHT = 300;
    public static final int HIEGHT = 275;
    @Override
    public void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MyHashMapView.fxml"));
        primaryStage.setTitle("HashMap using");
        primaryStage.setScene(new Scene(root, WIEGHT, HIEGHT));
        primaryStage.show();
    }


    public static void main(final String[] args) {
        launch(args);
    }
}
