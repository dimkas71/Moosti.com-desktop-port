package com.moosti;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("new-moosti-com.fxml"));

        primaryStage.setTitle("Moosti.com (Pomodoro technique)");
        Scene scene = new Scene(root, 600, 250);


        primaryStage.setScene(scene);
        scene.setFill(null);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
