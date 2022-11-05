package com.example.calculatrice_0;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class InterfaceGraphique extends Application{//Interface Application
    //Création des objets nécéssaires pour la création de la calculatrice
    int largeur = 340; int longueur = 530;
    Controleur controleur = new Controleur(this);
    Input input = new Input(controleur);
    Label affichageResultat;
    Label affichageMessage;
    Label affichageHistorique_1; Label affichageHistorique_2; Label affichageHistorique_3;
    String resultat = "0";
    String message = "";
    String historique_1 = ""; String historique_2 = ""; String historique_3 = "";
    List<Button> buttons;
    double yOffset;
    double xOffset;

    PauseTransition transition = new PauseTransition(Duration.seconds(0.05));


    //Ajout du PropertyChangeSupport
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    //création de la Vbox
    VBox box = new VBox();
    //Création de la StackPane
    StackPane stackPane = new StackPane();

    /**
     * Méthode de création de la fenêtre
     * @param stage fenêtre
     */
    @Override
    public void start(Stage stage) {
        //Ajout du listener controleur
        support.addPropertyChangeListener(controleur);
        //creation de nouveaux objets
        createLabels(stackPane);
        createButtons(stackPane);

        init(stage, stackPane);

        //configuration par défaut
        box.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        //Coordonnées du StackPane
        stackPane.setTranslateX(-140); stackPane.setTranslateY(10);

        //Ajout du StackPane à la Vbox
        box.getChildren().add(stackPane);

        //Création de la fenêtre scene
        Scene scene = new Scene (new StackPane(box), largeur, longueur);
        //Ajout des touches
        scene.addEventFilter(KeyEvent.KEY_PRESSED, input);

        //Fichier css seulement pour l'esthétique, ici les bordures de fenêtre arrondies
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(Objects.requireNonNull(InterfaceGraphique.class.getResource("Windows.css")).toExternalForm());

        //Initialisation de stage
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);

        //Déplacement de la fenêtre en maintenant sur un endroit de la calculatrice puis en déplaçant la souris
        scene.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });
        stage.show();

    }

    public void init(Stage stage, StackPane sp) {
        Button buttonClose = new Button("x");
        Button buttonMinimize = new Button("-");
        Label titre = new Label("Calculator");
        Image image = new Image("Calculatrice.jpg");
        Label background = new Label();

        int x = 2; int y = 2;
        int b_x = largeur; int b_y = -30;

        background.setPrefSize(largeur, 40);
        background.setTranslateX(140);
        background.setTranslateY(b_y-7);
        background.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        sp.getChildren().add(background);

        buttonClose.setPrefSize(x,y);
        buttonClose.setTranslateX(b_x-47);
        buttonClose.setTranslateY(b_y);
        buttonClose.setShape(new Circle(0.5));
        buttonClose.setStyle("-fx-background-color: #FF0000");//couleur grise
        buttonClose.setFont(Font.font("Courier New", FontWeight.NORMAL, 10));
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setOnMouseClicked(mouseEvent -> stage.close());
        sp.getChildren().add(buttonClose);

        buttonMinimize.setPrefSize(x,y);
        buttonMinimize.setTranslateX(b_x-70);
        buttonMinimize.setTranslateY(b_y);
        buttonMinimize.setShape(new Circle(0.5));
        buttonMinimize.setStyle("-fx-background-color: #808080");//couleur grise
        buttonMinimize.setFont(Font.font("Courier New", FontWeight.NORMAL, 10));
        buttonMinimize.setTextFill(Color.WHITE);
        buttonMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
        sp.getChildren().add(buttonMinimize);

        titre.setTranslateX(30);
        titre.setTranslateY(b_y);
        titre.setTextFill(Color.AQUA);
        titre.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
        sp.getChildren().add(titre);

        ImageView imageView = new ImageView(image);
        //Setting the position of the image
        imageView.setTranslateX(-15);
        imageView.setTranslateY(b_y);
        imageView.setFitHeight(17);
        imageView.setFitWidth(17);
        imageView.setPreserveRatio(true);

        sp.getChildren().add(imageView);
    }

    //Méthode de création des Label
    public void createLabels(StackPane sp){
        double centre = largeur /2.5;
        double coord_y_resultat = 75;

        //Label affichage de résultat ou des nombres entrés dans la calculatrice
        affichageResultat = new Label(resultat);
        affichageResultat.setTranslateY(coord_y_resultat); affichageResultat.setTranslateX(centre);
        affichageResultat.setTextFill(Color.WHITE);
        affichageResultat.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
        sp.getChildren().add(affichageResultat);

        //Label affichage des messages pour la gestion d'erreurs
        affichageMessage = new Label(message);
        affichageMessage.setTranslateY(coord_y_resultat-20); affichageMessage.setTranslateX(centre);
        affichageMessage.setTextFill(Color.WHITE);
        affichageMessage.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
        sp.getChildren().add(affichageMessage);

        //Labels pour l'affichage de l'historique des 3 dernières valeurs enregistrées par la calculatrice
        affichageHistorique_1 = new Label(historique_1);
        affichageHistorique_1.setTranslateY(coord_y_resultat-40); affichageHistorique_1.setTranslateX(centre);
        affichageHistorique_1.setTextFill(Color.WHITE);
        affichageHistorique_1.setFont(Font.font("Calibri", FontWeight.NORMAL, 18));
        sp.getChildren().add(affichageHistorique_1);
        affichageHistorique_2 = new Label(historique_2);
        affichageHistorique_2.setTranslateY(coord_y_resultat-60); affichageHistorique_2.setTranslateX(centre);
        affichageHistorique_2.setTextFill(Color.WHITE);
        affichageHistorique_2.setFont(Font.font("Calibri", FontWeight.NORMAL, 18));
        sp.getChildren().add(affichageHistorique_2);
        affichageHistorique_3 = new Label(historique_3);
        affichageHistorique_3.setTranslateY(coord_y_resultat-80); affichageHistorique_3.setTranslateX(centre);
        affichageHistorique_3.setTextFill(Color.WHITE);
        affichageHistorique_3.setFont(Font.font("Calibri", FontWeight.NORMAL, 18));
        sp.getChildren().add(affichageHistorique_3);
    }


    //Méthode de création des boutons
    public void createButtons(StackPane sp){
        //Liste des noms des boutons
        String[] nomBoutons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "C",
                                "push", "+", "-", "x", "/", "_", ",", "%", "<-"};

        //Initialisation des coordonnées des boutons
        int b_x = 20; int b_y = 140;
        int b_x_1 = b_x + 80; int b_y_1 = b_y + 75;
        int b_x_2 = b_x_1 + 80; int b_y_2 = b_y_1 + 75;
        int b_x_3 = b_x_2 + 80; int b_y_3 = b_y_2 + 75;
        int b_y_4 = b_y_3 + 75;

        //Liste des coordonnées des boutons sur la calculatrice
        int[][] pos = {{b_x,b_y_4},{b_x,b_y_3},{b_x_1,b_y_3},{b_x_2,b_y_3},//0-3
                {b_x,b_y_2},{b_x_1,b_y_2},{b_x_2,b_y_2},//4-6
                {b_x,b_y_1},{b_x_1,b_y_1},{b_x_2,b_y_1},//7-9
                {b_x,b_y},{b_x_3,b_y_4},{b_x_3,b_y_3},{b_x_3,b_y_2},{b_x_3,b_y_1},{b_x_3,b_y},{b_x_1,b_y},{b_x_2,b_y_4}, {b_x_2, b_y},
                {b_x_1, b_y_4}};

        //initialisation de la taille des boutons
        double x = 70; double y = 70;

        List<Button> buttons = new ArrayList<>();

        //Création des boutons
        for(int i = 0; i < nomBoutons.length; i++){
            Button button = new Button(nomBoutons[i]);
            button.setPrefSize(x,y);
            button.setTranslateX(pos[i][0]);
            button.setTranslateY(pos[i][1]);
            button.setShape(new Circle(1.5));
            sp.getChildren().add(button);//Ajout des boutons à StackPane
            button.setTextFill(Color.WHITE);

            //Liste boutons
            buttons.add(button);


            //Ajout des méthodes au bouton
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, controleur);

            //Cas particulier des boutons (couleurs, textes, polices, tailles, formes, etc...)
            String button_i = nomBoutons[i];
            switch(button_i){
                case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9","," -> {
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    button.setStyle("-fx-background-color: #5A5A5A");//couleur grise
                    button.setTextFill(Color.WHITE);//texte blanc
                    updateButtonOnClick(button, "-fx-background-color: #5A5A5A");
                }
                case "C", "%", "_" -> {
                    button.setStyle("-fx-background-color: #bcbcbc");//couleur grise
                    button.setTextFill(Color.WHITE);
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    updateButtonOnClick(button, "-fx-background-color: #bcbcbc");
                }
                case "/", "x", "-", "+" -> {
                    button.setStyle("-fx-background-color: #EC9706");//couleur orange
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    updateButtonOnClick(button, "-fx-background-color: #EC9706");
                }
                case "push" ->{
                    button.setStyle("-fx-background-color: #EC9706");//couleur orange
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
                    updateButtonOnClick(button, "-fx-background-color: #EC9706");
                }
                case "<-" ->{
                    button.setStyle("-fx-background-color: #bcbcbc");//couleur grise
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
                    updateButtonOnClick(button, "-fx-background-color: #bcbcbc");
                }
            }
        }
        //Ajout de la liste des boutons à la variable buttons
        this.buttons = buttons;
    }


    /**
     * Methode de mise a jour du Label AffichageResultat
     */
    public void updateAffichageResultat(){
        affichageResultat.setText(resultat);
    }

    /**
     * Methode de mise a jour du Label AffichageMessage
     */
    public void updateAffichageMessage(){affichageMessage.setText(message);}

    /**
     * Methode de mise a jour de l'historique
     */
    public void updateHistorique(){
        historique_3 = historique_2;
        historique_2 = historique_1;
        historique_1 = resultat;
        affichageHistorique_1.setText(historique_1);
        affichageHistorique_2.setText(historique_2);
        affichageHistorique_3.setText(historique_3);
    }

    public void resetHistorique(){
        historique_1 = "";
        historique_2 = "";
        historique_3 = "";
        affichageHistorique_1.setText(historique_1);
        affichageHistorique_2.setText(historique_2);
        affichageHistorique_3.setText(historique_3);
    }

    public void updateButtonOnClick(Button button, String couleur){
        PauseTransition transition = new PauseTransition(Duration.seconds(0.1));
        transition.setOnFinished(event -> button.setStyle(couleur));
        button.setOnMouseClicked(event -> {
            event.consume();
            button.setStyle("-fx-background-color: #00FF00");
            transition.playFromStart();
        });
    }

    public void updateButtonOnKey(Button button, String couleur){
        transition.setOnFinished(event -> button.setStyle(couleur));
        button.setStyle("-fx-background-color: #00FF00");
        transition.playFromStart();
    }

    //Lancement de la calculatrice
    public static void main(String[] args) {
        launch();
    }
}
