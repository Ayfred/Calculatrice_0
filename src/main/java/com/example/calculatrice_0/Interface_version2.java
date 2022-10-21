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

        button0.setShape(new Circle(1.5));
        button1.setShape(new Circle(1.5));
        button2.setShape(new Circle(1.5));
        button3.setShape(new Circle(1.5));
        button4.setShape(new Circle(1.5));
        button5.setShape(new Circle(1.5));
        button6.setShape(new Circle(1.5));
        button7.setShape(new Circle(1.5));
        button8.setShape(new Circle(1.5));
        button9.setShape(new Circle(1.5));
        btn_add.setShape(new Circle(1.5));
        btn_div.setShape(new Circle(1.5));
        btn_sub.setShape(new Circle(1.5));
        btn_mult.setShape(new Circle(1.5));
        btn_clear.setShape(new Circle(1.5));
        btn_virgule.setShape(new Circle(1.5));
        btn_neg.setShape(new Circle(1.5));
        btn_push.setShape(new Circle(1.5));




        //positionnement
        button0.setTranslateX(2.0); button0.setTranslateY(399);
        button1.setTranslateX(2.0); button1.setTranslateY(323);
        button2.setTranslateX(92); button2.setTranslateY(323);
        button3.setTranslateX(180); button3.setTranslateY(323);
        button4.setTranslateX(2.0); button4.setTranslateY(248);
        button5.setTranslateX(92); button5.setTranslateY(248);
        button6.setTranslateX(180); button6.setTranslateY(248);
        button7.setTranslateX(2.0); button7.setTranslateY(172);
        button8.setTranslateX(92); button8.setTranslateY(172);
        button9.setTranslateX(180); button9.setTranslateY(172);

        btn_add.setTranslateX(268); btn_add.setTranslateY(322);
        btn_div.setTranslateX(268); btn_div.setTranslateY(98);
        btn_neg.setTranslateX(92); btn_neg.setTranslateY(399);
        btn_clear.setTranslateX(2); btn_clear.setTranslateY(98);
        btn_mult.setTranslateX(268); btn_mult.setTranslateY(173);
        btn_push.setTranslateX(268); btn_push.setTranslateY(399);
        btn_virgule.setTranslateX(180); btn_virgule.setTranslateY(399);
        btn_sub.setTranslateX(268); btn_sub.setTranslateY(248);

        resultat.setTranslateX(2); resultat.setTranslateY(20);


        //configuration par défaut
        box.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));



        //button font
        button0.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        button1.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        button2.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        button3.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        button4.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        button5.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        button6.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        button7.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        button8.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        button9.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        btn_add.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        btn_div.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        btn_clear.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        btn_sub.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        //btn_neg.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        //btn_push.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        btn_mult.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        btn_virgule.setFont(Font.font("Courier New", FontWeight.BOLD, 36));

        resultat.setFont(Font.font("Courier New", FontWeight.BOLD, 36));


        //button color
        resultat.setTextFill(Color.WHITE);

        button0. setStyle("-fx-background-color: #5A5A5A");
        button1. setStyle("-fx-background-color: #5A5A5A");
        button2. setStyle("-fx-background-color: #5A5A5A");
        button3. setStyle("-fx-background-color: #5A5A5A");
        button4. setStyle("-fx-background-color: #5A5A5A");
        button5. setStyle("-fx-background-color: #5A5A5A");
        button6. setStyle("-fx-background-color: #5A5A5A");
        button7. setStyle("-fx-background-color: #5A5A5A");
        button8. setStyle("-fx-background-color: #5A5A5A");
        button9. setStyle("-fx-background-color: #5A5A5A");
        btn_neg. setStyle("-fx-background-color: #5A5A5A");
        btn_virgule. setStyle("-fx-background-color: #5A5A5A");
        btn_add. setStyle("-fx-background-color: #EC9706");
        btn_div. setStyle("-fx-background-color: #EC9706");
        btn_mult. setStyle("-fx-background-color: #EC9706");
        btn_sub. setStyle("-fx-background-color: #EC9706");
        btn_push. setStyle("-fx-background-color: #EC9706");
        btn_clear.setStyle("-fx-background-color: #C0C0C0");


        button0.setTextFill(Color.WHITE);
        button1.setTextFill(Color.WHITE);
        button2.setTextFill(Color.WHITE);
        button3.setTextFill(Color.WHITE);
        button4.setTextFill(Color.WHITE);
        button5.setTextFill(Color.WHITE);
        button6.setTextFill(Color.WHITE);
        button7.setTextFill(Color.WHITE);
        button8.setTextFill(Color.WHITE);
        button9.setTextFill(Color.WHITE);
        btn_clear.setTextFill(Color.BLACK);
        btn_virgule.setTextFill(Color.WHITE);
        btn_add.setTextFill(Color.WHITE);
        btn_div.setTextFill(Color.WHITE);
        btn_sub.setTextFill(Color.WHITE);
        btn_mult.setTextFill(Color.WHITE);
        btn_push.setTextFill(Color.WHITE);
        btn_neg.setTextFill(Color.WHITE);




        stackPane.getChildren().addAll(resultat, button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, btn_add, btn_clear, btn_div, btn_mult, btn_neg, btn_push, btn_sub, btn_virgule);


        //Gridpane centered
        //gridpane.setAlignment(Pos.BOTTOM_CENTER);
        stackPane.setTranslateX(-140); stackPane.setTranslateY(-30);


        //Vbox setting up
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
