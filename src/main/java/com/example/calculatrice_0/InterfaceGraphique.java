package com.example.calculatrice_0;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        //settings
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Calculatrice v0.1 alpha");
        stage.setScene(scene);



        stage.show();


    }

    public static void main(String[] args) {




        launch();
    }
}