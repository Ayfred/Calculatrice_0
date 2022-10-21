package com.example.calculatrice_0;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Interface_version2 extends Application {


    @Override
    public void start(Stage stage) {

        //creation de nouveaux objets
        VBox box = new VBox();
        StackPane stackPane = new StackPane();

        createButtons(stackPane);

        //configuration par défaut
        box.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        //Gridpane centered
        stackPane.setTranslateX(-140); stackPane.setTranslateY(10);

        //Vbox setting up
        box.getChildren().add(stackPane);

        //création
        Scene scene = new Scene (new StackPane(box),372, 500);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

    }




    public void createButtons(StackPane sp){
        String[] nomBoutons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "C",
                                "push", "+", "-", "x", "/", "-", ","};
        int[][] pos = {{2,399},{2,323},{92,323},{180,323},{2,248},{92,248},{180,248},{2,172},{92,172},{180,172},
                            {2,98},{268,399},{268,322},{268,248},{268,173},{268,98},{92,399},{180,399}};
        double x = 70; double y = 70;

        Label resultat = new Label("0.0");
        resultat.setTranslateY(20); resultat.setTranslateX(10);
        resultat.setTextFill(Color.WHITE);
        resultat.setFont(Font.font("Courier New", FontWeight.BOLD, 36));

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
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
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

    public void onNumberClicked(MouseEvent event) {
        String valeur = switch (((Pane) event.getSource()).getId()) {
            case "0" -> "0";
            case "1" -> "1";
            case "2" -> "2";
            case "3" -> "3";
            case "4" -> "4";
            case "5" -> "5";
            case "6" -> "6";
            case "7" -> "7";
            case "8" -> "8";
            case "9" -> "9";
            default -> "";
        };
        if(result == "0.0" || result == "0"){ result = valeur;}
        else{result += valeur;}
        resultat.setText(result);
    }

    public static void main(String[] args) {
        launch();
    }

}
