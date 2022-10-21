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
        double x = 70; double y = 70;
        button0.setPrefSize(x, y); button1.setPrefSize(x, y); button2.setPrefSize(x, y); button3.setPrefSize(x, y); button4.setPrefSize(x, y);
        button5.setPrefSize(x, y); button6.setPrefSize(x, y); button7.setPrefSize(x, y); button8.setPrefSize(x, y); button9.setPrefSize(x, y);
        btn_clear.setPrefSize(x, y); btn_push.setPrefSize(x, y); btn_div.setPrefSize(x, y); btn_sub.setPrefSize(x, y); btn_add.setPrefSize(x, y);
        btn_mult.setPrefSize(x, y); btn_neg.setPrefSize(x, y); btn_virgule.setPrefSize(x, y);
        resultat.setPrefSize(50,150);
        //resultat.setFont(new Font(30.0));



        //positionnement
        button0.setTranslateX(2.0); button0.setTranslateY(399);
        button1.setTranslateX(2.0); button1.setTranslateY(323);
        button2.setTranslateX(92); button2.setTranslateY(323);
        button3.setTranslateX(180); button3.setTranslateY(323);
        button4.setTranslateX(2.0); button4.setTranslateY(248);
        button5.setTranslateX(92); button5.setTranslateY(248);
        button6.setTranslateX(180); button6.setTranslateY(247);
        button7.setTranslateX(2.0); button7.setTranslateY(172);
        button8.setTranslateX(92); button8.setTranslateY(172);
        button9.setTranslateX(180); button9.setTranslateY(172);

        btn_add.setTranslateX(268); btn_add.setTranslateY(322);
        btn_div.setTranslateX(268); btn_div.setTranslateY(98);
        btn_neg.setTranslateX(92); btn_neg.setTranslateY(399);
        btn_clear.setTranslateX(2); btn_clear.setTranslateY(98);
        btn_mult.setTranslateX(268); btn_mult.setTranslateY(173);
        btn_push.setTranslateX(267); btn_push.setTranslateY(399);
        btn_virgule.setTranslateX(180); btn_virgule.setTranslateY(399);
        btn_sub.setTranslateX(268); btn_sub.setTranslateY(248);



        //configuration par défaut
        box.setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY, Insets.EMPTY)));


        //gridpane




        stackPane.getChildren().addAll(resultat, button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, btn_add, btn_clear, btn_div, btn_mult, btn_neg, btn_push, btn_sub, btn_virgule);


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




    public static void main(String[] args) {
        launch();
    }

}
