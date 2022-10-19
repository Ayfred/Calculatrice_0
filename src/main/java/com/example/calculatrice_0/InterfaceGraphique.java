package com.example.calculatrice_0;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class InterfaceGraphique extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(InterfaceGraphique.class.getResource("Windows.fxml"));

        //settings
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Windows.fxml")));
        stage.setScene(scene);
        stage.setTitle("Calculatrice v1.0 beta");
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);




        stage.getIcons().add(new Image("D://maxim//EclipseJava//Calculatrice_0//src//main//resources//Calcultatrice.jpg"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}