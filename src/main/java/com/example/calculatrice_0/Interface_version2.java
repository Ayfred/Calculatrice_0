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
import javafx.stage.Stage;

import javax.swing.text.Position;
import java.awt.*;


public class Interface_version2 extends Application {


    @Override
    public void start(Stage stage) {

        //instanciation
        String version = System.getProperty("java.version");

        //creation de nouveaux objets
        Label l = new Label ("Hello, JavaFX 13, running on Java version " + version);
        Label label1 = new Label ( "Bonjour ! ");
        VBox box = new VBox();
        GridPane gridpane = new GridPane();
        Button button0 = new Button(); Button button1 = new Button(); Button button2 = new Button(); Button button3 = new Button(); Button button4 = new Button();
        Button button5 = new Button(); Button button6 = new Button(); Button button7 = new Button(); Button button8 = new Button(); Button button9 = new Button();
        Button btn_clear = new Button(); Button btn_push = new Button();
        Button btn_add = new Button(); Button btn_mult = new Button(); Button btn_div = new Button(); Button btn_sub = new Button();
        Button btn_neg = new Button(); Button btn_virgule = new Button();
        Label resultat = new Label("0.0");
        Pane pane = new Pane();

        //attribution des noms
        button0.setText("0"); button1.setText("1"); button2.setText("2"); button3.setText("3"); button4.setText("4"); button5.setText("5");
        button6.setText("6"); button7.setText("7"); button8.setText("8"); button9.setText("9");
        btn_clear.setText("C"); btn_push.setText("Push");
        btn_add.setText("+"); btn_mult.setText("x"); btn_div.setText("/"); btn_sub.setText("-");
        btn_neg.setText("Moins"); btn_virgule.setText(",");

        //config de tailles
        double x = 60; double y = 60;
        button0.setPrefSize(x, y); button1.setPrefSize(x, y); button2.setPrefSize(x, y); button3.setPrefSize(x, y); button4.setPrefSize(x, y);
        button5.setPrefSize(x, y); button6.setPrefSize(x, y); button7.setPrefSize(x, y); button8.setPrefSize(x, y); button9.setPrefSize(x, y);
        btn_clear.setPrefSize(x, y); btn_push.setPrefSize(x, y); btn_div.setPrefSize(x, y); btn_sub.setPrefSize(x, y); btn_add.setPrefSize(x, y);
        btn_mult.setPrefSize(x, y); btn_neg.setPrefSize(x, y); btn_virgule.setPrefSize(x, y);
        resultat.setPrefSize(300,100);
        //resultat.setFont(new Font(30.0));


        //positionnement
        button0.setLayoutX(2.0); button0.setLayoutY(399.0);

        //configuration par défaut
        box.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY, Insets.EMPTY)));


        //gridpane
        GridPane.setRowIndex(button0, 4); GridPane.setColumnIndex(button0, 1);
        GridPane.setRowIndex(button1, 3); GridPane.setColumnIndex(button1, 1);
        GridPane.setRowIndex(button2, 3); GridPane.setColumnIndex(button2, 2);
        GridPane.setRowIndex(button3, 3); GridPane.setColumnIndex(button3, 3);
        GridPane.setRowIndex(button4, 2); GridPane.setColumnIndex(button4, 1);
        GridPane.setRowIndex(button5, 2); GridPane.setColumnIndex(button5, 2);
        GridPane.setRowIndex(button6, 2); GridPane.setColumnIndex(button6, 3);
        GridPane.setRowIndex(button7, 1); GridPane.setColumnIndex(button7, 1);
        GridPane.setRowIndex(button8, 1); GridPane.setColumnIndex(button8, 2);
        GridPane.setRowIndex(button9, 1); GridPane.setColumnIndex(button9, 3);

        GridPane.setRowIndex(btn_div, 0); GridPane.setColumnIndex(btn_div, 4);
        GridPane.setRowIndex(btn_mult, 1); GridPane.setColumnIndex(btn_mult, 4);
        GridPane.setRowIndex(btn_sub, 2); GridPane.setColumnIndex(btn_sub, 4);
        GridPane.setRowIndex(btn_add, 3); GridPane.setColumnIndex(btn_add, 4);
        GridPane.setRowIndex(btn_clear, 0); GridPane.setColumnIndex(btn_clear, 1);
        GridPane.setRowIndex(btn_push, 4); GridPane.setColumnIndex(btn_push, 4);
        GridPane.setRowIndex(btn_virgule, 4); GridPane.setColumnIndex(btn_virgule, 3);
        GridPane.setRowIndex(btn_neg, 4); GridPane.setColumnIndex(btn_neg, 2);



        GridPane.setConstraints(label1, 3, 0);
        gridpane.getChildren().addAll(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, btn_add, btn_clear, btn_div, btn_mult, btn_neg, btn_push, btn_sub, btn_virgule);


        //Gridpane centered
        gridpane.setVgap(5); gridpane.setHgap(5);
        //gridpane.setAlignment(Pos.BOTTOM_CENTER);
        gridpane.setLayoutX(100); gridpane.setLayoutY(100);


        //Vbox setting up
        box.getChildren().add(pane);
        box.getChildren().add(button0);
        //box.getChildren().add(l);
        box.getChildren().add(gridpane);

        //création
        Scene scene = new Scene (new StackPane(box),372, 550);

        stage.setScene(scene);
        stage.show();

    }




    public static void main(String[] args) {
        launch();
    }

}
