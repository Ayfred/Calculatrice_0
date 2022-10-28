package com.example.calculatrice_0;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.beans.PropertyChangeSupport;


public class Modele extends Application {

    int coord_x = 372; int coord_y = 500;
    Controleur controleur = new Controleur(this);
    Label affichageResultat;
    Label affichageMessage;
    Label affichageHistorique_1; Label affichageHistorique_2; Label affichageHistorique_3;
    String resultat = "0";
    String message = "";
    String historique_1 = ""; String historique_2 = ""; String historique_3 = "";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void start(Stage stage) {
        support.addPropertyChangeListener(controleur);
        //creation de nouveaux objets
        VBox box = new VBox();
        StackPane stackPane = new StackPane();

        createLabels(stackPane);
        createButtons(stackPane);

        //configuration par défaut
        box.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        //Gridpane centered
        stackPane.setTranslateX(-140); stackPane.setTranslateY(10);

        //Vbox setting up
        box.getChildren().add(stackPane);

        //création
        Scene scene = new Scene (new StackPane(box),coord_x, coord_y);
        stage.setResizable(false);
        stage.setTitle("Calculatrice v1.0 Beta");
        stage.getIcons().add(new Image("Calculatrice.jpg"));
        stage.setScene(scene);
        stage.show();

    }

    public void createLabels(StackPane sp){
        double centre = coord_x/2.5;
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
        String[] nomBoutons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "C",
                                "push", "+", "-", "x", "/", "_", ","};
        int[][] pos = {{2,399},{2,323},{92,323},{180,323},{2,248},{92,248},{180,248},{2,172},{92,172},{180,172},
                            {2,98},{268,399},{268,322},{268,248},{268,173},{268,98},{92,399},{180,399}};
        double x = 70; double y = 70;


        for(int i = 0; i < nomBoutons.length; i++){
            Button button = new Button(nomBoutons[i]);
            button.setPrefSize(x,y);
            button.setTranslateX(pos[i][0]);
            button.setTranslateY(pos[i][1]);
            button.setShape(new Circle(1.5));
            sp.getChildren().add(button);
            button.setTextFill(Color.WHITE);

            if(i<=10 || i == 16 || i == 17){
                button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                button.setStyle("-fx-background-color: #5A5A5A");//couleur grise
                button.setTextFill(Color.WHITE);//texte blanc
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);

            }
            else if(nomBoutons[i].equals("C")){
                    button.setStyle("-fx-background-color: #bcbcbc");
                    button.setTextFill(Color.BLACK);
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
                }
            else if(nomBoutons[i].equals("push")){
                button.setStyle("-fx-background-color: #EC9706");//couleur orange
                button.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
            }
            else{
                button.setStyle("-fx-background-color: #EC9706");//couleur orange
                button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);
            }
        }
    }

    public void updateAffichageResultat(){
        affichageResultat.setText(resultat);
    }

    public void updateAffichageMessage(){affichageMessage.setText(message);}

    public void updateResultat(String newResultat){
        if((resultat.equals("0.0") || resultat.equals("0")) && !newResultat.equals(".")){
            resultat = newResultat;
        }
        else{
        resultat = resultat + newResultat;}
    }

    public void updateHistorique(){
        historique_3 = historique_2;
        historique_2 = historique_1;
        historique_1 = resultat;
        affichageHistorique_1.setText(historique_1);
        affichageHistorique_2.setText(historique_2);
        affichageHistorique_3.setText(historique_3);
    }


    public static void main(String[] args) {
        launch();
    }

}
