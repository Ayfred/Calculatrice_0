package com.example.calculatrice_0;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.beans.PropertyChangeSupport;


public class Modele extends Application {

    int largeur = 340; int longueur = 500;
    Controleur controleur = new Controleur(this);
    Input input = new Input(controleur);
    Label affichageResultat;
    Label affichageMessage;
    Label affichageHistorique_1; Label affichageHistorique_2; Label affichageHistorique_3;
    String resultat = "0";
    String message = "";
    String historique_1 = ""; String historique_2 = ""; String historique_3 = "";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    VBox box = new VBox();
    StackPane stackPane = new StackPane();

    @Override
    public void start(Stage stage) {
        support.addPropertyChangeListener(controleur);
        //creation de nouveaux objets

        createLabels(stackPane);
        createButtons(stackPane);

        //configuration par défaut
        box.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        //Gridpane centered
        stackPane.setTranslateX(-140); stackPane.setTranslateY(10);

        //Vbox setting up
        box.getChildren().add(stackPane);


        //création
        Scene scene = new Scene (new StackPane(box), largeur, longueur);
        stage.setResizable(false);
        stage.setTitle("Calculatrice v1.15");
        stage.getIcons().add(new Image("Calculatrice.jpg"));
        stage.setScene(scene);
        stage.show();

    }

    public void createLabels(StackPane sp){
        double centre = largeur /2.5;
        double coord_y_resultat = 45;

        affichageResultat = new Label(resultat);
        affichageResultat.setTranslateY(coord_y_resultat); affichageResultat.setTranslateX(centre);
        affichageResultat.setTextFill(Color.WHITE);
        affichageResultat.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
        sp.getChildren().addAll(affichageResultat);

        affichageMessage = new Label(message);
        affichageMessage.setTranslateY(coord_y_resultat-20); affichageMessage.setTranslateX(centre);
        affichageMessage.setTextFill(Color.WHITE);
        affichageMessage.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
        sp.getChildren().addAll(affichageMessage);

        affichageHistorique_1 = new Label(historique_1);
        affichageHistorique_1.setTranslateY(coord_y_resultat-40); affichageHistorique_1.setTranslateX(centre);
        affichageHistorique_1.setTextFill(Color.WHITE);
        affichageHistorique_1.setFont(Font.font("Courier New", FontWeight.NORMAL, 18));
        sp.getChildren().addAll(affichageHistorique_1);
        affichageHistorique_2 = new Label(historique_2);
        affichageHistorique_2.setTranslateY(coord_y_resultat-60); affichageHistorique_2.setTranslateX(centre);
        affichageHistorique_2.setTextFill(Color.WHITE);
        affichageHistorique_2.setFont(Font.font("Courier New", FontWeight.NORMAL, 18));
        sp.getChildren().addAll(affichageHistorique_2);
        affichageHistorique_3 = new Label(historique_3);
        affichageHistorique_3.setTranslateY(coord_y_resultat-80); affichageHistorique_3.setTranslateX(centre);
        affichageHistorique_3.setTextFill(Color.WHITE);
        affichageHistorique_3.setFont(Font.font("Courier New", FontWeight.NORMAL, 18));
        sp.getChildren().addAll(affichageHistorique_3);
    }



    public void createButtons(StackPane sp){
        //noms des boutons
        String[] nomBoutons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "C",
                                "push", "+", "-", "x", "/", "_", ",", "%"};

        //coordonnées des boutons
        int b_x = 20; int b_y = 110;
        int b_x_1 = b_x + 80; int b_y_1 = b_y + 75;
        int b_x_2 = b_x_1 + 80; int b_y_2 = b_y_1 + 75;
        int b_x_3 = b_x_2 + 80; int b_y_3 = b_y_2 + 75;
        int b_y_4 = b_y_3 + 75;

        int[][] pos = {{b_x+40,b_y_4},{b_x,b_y_3},{b_x_1,b_y_3},{b_x_2,b_y_3},//0-3
                {b_x,b_y_2},{b_x_1,b_y_2},{b_x_2,b_y_2},//4-6
                {b_x,b_y_1},{b_x_1,b_y_1},{b_x_2,b_y_1},//7-9
                {b_x,b_y},{b_x_3,b_y_4},{b_x_3,b_y_3},{b_x_3,b_y_2},{b_x_3,b_y_1},{b_x_3,b_y},{b_x_1,b_y},{b_x_2,b_y_4}, {b_x_2, b_y}};

        //tailles des boutons
        double x = 70; double y = 70;


        for(int i = 0; i < nomBoutons.length; i++){
            Button button = new Button(nomBoutons[i]);
            button.setPrefSize(x,y);
            button.setTranslateX(pos[i][0]);
            button.setTranslateY(pos[i][1]);
            button.setShape(new Circle(1.5));
            sp.getChildren().add(button);
            button.setTextFill(Color.WHITE);

            String button_i = nomBoutons[i];
            switch(button_i){
                case "0" ->{
                    button.setShape(new Rectangle(x+80, y));
                    button.setPrefSize(x+80,y);
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    //problème avec le border radius
                    button.setStyle("-fx-background-color: #5A5A5A; -fx-background-radius: 25px;" +//couleur grise
                            " -fx-border-radius: 10px;-fx-border-width: 5px;-fx-border-color: red;");
                    button.setTextFill(Color.WHITE);//texte blanc
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
                    button.addEventHandler(KeyEvent.KEY_PRESSED, input);

                }

                case "1", "2", "3", "4", "5", "6", "7", "8", "9","," -> {
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    button.setStyle("-fx-background-color: #5A5A5A");//couleur grise
                    button.setTextFill(Color.WHITE);//texte blanc
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
                    button.addEventHandler(KeyEvent.KEY_PRESSED, input);
                }
                case "C", "%", "_" -> {
                    button.setStyle("-fx-background-color: #bcbcbc");
                    button.setTextFill(Color.WHITE);
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
                    button.addEventHandler(KeyEvent.KEY_PRESSED, input);
                }
                case "/", "x", "-", "+" -> {
                    button.setStyle("-fx-background-color: #EC9706");//couleur orange
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
                    button.addEventHandler(KeyEvent.KEY_PRESSED, input);
                }
                case "push" ->{
                    button.setStyle("-fx-background-color: #EC9706");//couleur orange
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
                    button.addEventHandler(KeyEvent.KEY_PRESSED, input);
                }
            }
        }
    }

    public void updateAffichageResultat(){
        affichageResultat.setText(resultat);
    }

    public void updateAffichageMessage(){affichageMessage.setText(message);}


    public static void main(String[] args) {
        launch();
    }

}
