package com.example.calculatrice_0;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class InterfaceGraphique extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(InterfaceGraphique.class.getResource("Windows.fxml"));
        //settings
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Windows.fxml")));
        stage.setScene(scene);
        stage.setTitle("Calculatrice v0.1 alpha");
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}