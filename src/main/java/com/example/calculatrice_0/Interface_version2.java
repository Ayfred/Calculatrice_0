package com.example.calculatrice_0;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.swing.text.Position;
import java.awt.*;
import java.util.Stack;


public class Interface_version2 extends Application {


    @Override
    public void start(Stage stage) {

        //instanciation
        String version = System.getProperty("java.version");

        //creation de nouveaux objets
        Label l = new Label ("Hello, JavaFX 13, running on Java version " + version);
        Label label1 = new Label ( "Bonjour ! ");
        VBox box = new VBox();
        StackPane stackPane = new StackPane();
        Pane pane = new Pane();

        createButtons(stackPane);

        //configuration par défaut
        box.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        //Gridpane centered
        //gridpane.setAlignment(Pos.BOTTOM_CENTER);
        stackPane.setTranslateX(-140); stackPane.setTranslateY(-30);

        //Vbox setting up
        //box.getChildren().add(pane);
        //box.getChildren().add(button0);
        //box.getChildren().add(l);
        box.getChildren().add(stackPane);

        //création
        Scene scene = new Scene (new StackPane(box),372, 500);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

    }


    public void createButtons(StackPane sp){
        String[] nomBoutons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "clear",
                                "push", "+", "-", "x", "/", "-", ","};
        int[][] pos = {{2,399},{2,323},{92,323},{180,323},{2,248},{92,248},{180,248},{2,172},{92,172},{180,172},
                            {2,98},{268,399},{268,322},{268,248},{268,173},{268,98},{92,399},{180,399}};
        double x = 70; double y = 70;

        Label resultat = new Label("0.0");
        resultat.setTextFill(Color.WHITE);

        for(int i = 0; i < nomBoutons.length; i++){
            Button button = new Button(nomBoutons[i]);
            button.setPrefSize(x,y);
            button.setTranslateX(pos[i][0]);
            button.setTranslateY(pos[i][1]);
            button.setShape(new Circle(1.5));
            sp.getChildren().add(button);
            button.setTextFill(Color.WHITE);
            if(i<10 || i == 16){
                button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                if(i==0){
                    button.setStyle("-fx-background-color: #FFFFFF");
                    button.setTextFill(Color.BLACK);
                }
                else{
                    button. setStyle("-fx-background-color: #5A5A5A");
                }
            }
            else{
                if(i==10){
                    button. setStyle("-fx-background-color: #808080");
                    button.setTextFill(Color.BLACK);
                }
                else{
                    if(i==17){
                        button. setStyle("-fx-background-color: #5A5A5A");
                        button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    }
                    else{
                        button. setStyle("-fx-background-color: #EC9706");
                        if(i != 11) {
                            button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                        }
                    }
                }
            }
        }
        sp.getChildren().addAll(resultat);
    }

    public static void main(String[] args) {
        launch();
    }

}
