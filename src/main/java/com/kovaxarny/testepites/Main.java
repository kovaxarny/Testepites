package com.kovaxarny.testepites;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Launch a standalone application.
     *
     * @param primaryStage The Stage where the application will show up
     * @throws Exception if exception occurred
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BMIWindow.fxml"));
        primaryStage.setTitle("Workout Assistant");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * Main method for the Testepites application.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        launch(args);
    }
}
