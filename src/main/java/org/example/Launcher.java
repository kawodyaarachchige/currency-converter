package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Launcher  extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainController currencyConverterUI = new MainController();
        Scene scene = new Scene(currencyConverterUI, 250, 300);
        primaryStage.setTitle("Currency Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


