package com.example.calculatrice;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


public class InterfaceGraphique extends Application{//Interface Application
    //Création des objets nécéssaires pour la création de la calculatrice
    String version = "3.1.0"; String date =  new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    int largeur = 380; int longueur = 680;
    Controleur controleur = new Controleur(this);
    Input input = new Input(controleur);
    Label affichageResultat; Label affichageMessage; Label affichagePile;
    Label affichageHistorique_1; Label affichageHistorique_2; Label affichageHistorique_3;
    Label titre = new Label("Calculator");
    String resultat = "0";String message = "";
    String historique_1 = ""; String historique_2 = ""; String historique_3 = "";
    List<Button> buttons;
    double xOffset; double yOffset;
    Color couleur_texte = Color.WHITE; String couleur_updateButtonOnClick_Key = "-fx-background-color: #00FF00";
    PauseTransition transition = new PauseTransition(Duration.seconds(0.05));
    //Ajout du PropertyChangeSupport
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

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
        easter_egg_affichage(stage, stackPane);


        //Coordonnées du StackPane
        stackPane.setTranslateX(0); stackPane.setTranslateY(0);
        stackPane.setPrefSize(longueur, largeur);

        Scene scene = new Scene (stackPane, largeur, longueur);
        //Ajout des touches
        scene.addEventFilter(KeyEvent.KEY_PRESSED, input);

        //Fichier css seulement pour l'esthétique, ici les bordures de fenêtre arrondies
        scene.setFill(Color.TRANSPARENT);

        scene.getStylesheets().add(Objects.requireNonNull(InterfaceGraphique.class.getResource("Windows.css")).toExternalForm());

        //Initialisation de stage
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.getIcons().add(new Image("Calculatrice.jpg"));
        stage.setTitle("Calculator");

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

    /**
     *
     * @param stage
     * @param sp
     */
    public void init(Stage stage, StackPane sp) {
        Button buttonClose = new Button("x");
        Button buttonMinimize = new Button("-");
        Image image = new Image("Calculatrice.jpg");

        int x = 2; int y = 2;
        int b_x = 5*largeur/11; int b_y = -10*longueur/21;

        buttonClose.setPrefSize(x,y);
        buttonClose.setTranslateX(b_x);
        buttonClose.setTranslateY(b_y);
        buttonClose.setShape(new Circle(0.5));
        buttonClose.setStyle("-fx-background-color: #FF0000");//couleur rouge
        buttonClose.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
        buttonClose.setTextFill(Color.WHITE);
        buttonClose.setOnMouseClicked(mouseEvent -> stage.close());
        sp.getChildren().add(buttonClose);

        buttonMinimize.setPrefSize(x,y);
        buttonMinimize.setTranslateX(b_x-25);
        buttonMinimize.setTranslateY(b_y);
        buttonMinimize.setShape(new Circle(0.5));
        buttonMinimize.setStyle("-fx-background-color: #808080");//couleur grise
        buttonMinimize.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
        buttonMinimize.setTextFill(Color.WHITE);
        buttonMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
        sp.getChildren().add(buttonMinimize);

        titre.setTranslateX(-b_x + 50);
        titre.setTranslateY(b_y);
        titre.setTextFill(Color.WHITE);
        titre.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
        sp.getChildren().add(titre);

        ImageView imageView = new ImageView(image);
        //Setting the position of the image
        imageView.setTranslateX(-b_x+2);
        imageView.setTranslateY(b_y-1);
        imageView.setFitHeight(18);
        imageView.setFitWidth(14);
        imageView.setPreserveRatio(true);
        sp.getChildren().add(imageView);
    }

    /**
     * Méthode de création des Label
     * @param sp
     */
    public void createLabels(StackPane sp){
        double centre = -10;
        double coord_y_resultat = -5*largeur/12.;

        //Label affichage de résultat ou des nombres entrés dans la calculatrice
        affichageResultat = new Label(resultat);
        affichageResultat.setTranslateY(coord_y_resultat); affichageResultat.setTranslateX(centre);
        affichageResultat.setTextFill(couleur_texte);
        affichageResultat.setFont(Font.font("Calibri", FontWeight.NORMAL, 60));
        sp.getChildren().add(affichageResultat);
        StackPane.setAlignment(affichageResultat, Pos.CENTER_RIGHT);


        affichageResultat.setAlignment(Pos.CENTER);

        //Label affichage des messages pour la gestion d'erreurs
        affichageMessage = new Label(message);
        affichageMessage.setTranslateY(coord_y_resultat-40); affichageMessage.setTranslateX(centre);
        affichageMessage.setTextFill(couleur_texte);
        affichageMessage.setFont(Font.font("Calibri", FontWeight.BOLD, 18));
        sp.getChildren().add(affichageMessage);
        StackPane.setAlignment(affichageMessage, Pos.CENTER);


        //Labels pour l'affichage de l'historique des 3 dernières valeurs enregistrées par la calculatrice
        affichageHistorique_1 = new Label(historique_1);
        affichageHistorique_1.setTranslateY(coord_y_resultat-60); affichageHistorique_1.setTranslateX(centre);
        affichageHistorique_1.setTextFill(couleur_texte);
        affichageHistorique_1.setFont(Font.font("Calibri", FontWeight.NORMAL, 18));
        sp.getChildren().add(affichageHistorique_1);
        StackPane.setAlignment(affichageHistorique_1, Pos.CENTER_RIGHT);

        affichageHistorique_2 = new Label(historique_2);
        affichageHistorique_2.setTranslateY(coord_y_resultat-80); affichageHistorique_2.setTranslateX(centre);
        affichageHistorique_2.setTextFill(couleur_texte);
        affichageHistorique_2.setFont(Font.font("Calibri", FontWeight.NORMAL, 18));
        sp.getChildren().add(affichageHistorique_2);
        StackPane.setAlignment(affichageHistorique_2, Pos.CENTER_RIGHT);

        affichageHistorique_3 = new Label(historique_3);
        affichageHistorique_3.setTranslateY(coord_y_resultat-100); affichageHistorique_3.setTranslateX(centre);
        affichageHistorique_3.setTextFill(couleur_texte);
        affichageHistorique_3.setFont(Font.font("Calibri", FontWeight.NORMAL, 18));
        sp.getChildren().add(affichageHistorique_3);
        StackPane.setAlignment(affichageHistorique_3, Pos.CENTER_RIGHT);



        //Label affichage de l'état de la pile
        affichagePile = new Label();
        affichagePile.setTranslateY(coord_y_resultat+40); affichagePile.setTranslateX(centre);
        affichagePile.setTextFill(couleur_texte);
        affichagePile.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
        sp.getChildren().add(affichagePile);
    }


    /**
     * Méthode de création des boutons
     * @param sp
     */
    public void createButtons(StackPane sp){
        //Liste des noms des boutons
        String[] nomBoutons = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "C",
                                "=", "+", "-", "x", "/", "±", ",", "%", "←"};

        //Initialisation des coordonnées des boutons
        int separation_1 = 90; int separation_2 = 85;

        int b_x = -14*largeur/39; int b_y = -largeur/8;
        int b_x_1 = b_x + separation_1; int b_y_1 = b_y + separation_2;
        int b_x_2 = b_x_1 + separation_1; int b_y_2 = b_y_1 + separation_2;
        int b_x_3 = b_x_2 + separation_1; int b_y_3 = b_y_2 + separation_2;
        int b_y_4 = b_y_3 + separation_2;

        //Liste des coordonnées des boutons sur la calculatrice
        int[][] pos = {{b_x,b_y_4},{b_x,b_y_3},{b_x_1,b_y_3},{b_x_2,b_y_3},//0-3
                {b_x,b_y_2},{b_x_1,b_y_2},{b_x_2,b_y_2},//4-6
                {b_x,b_y_1},{b_x_1,b_y_1},{b_x_2,b_y_1},//7-9
                {b_x,b_y},{b_x_3,b_y_4},{b_x_3,b_y_3},{b_x_3,b_y_2},{b_x_3,b_y_1},{b_x_3,b_y},{b_x_1,b_y},{b_x_2,b_y_4}, {b_x_2, b_y},
                {b_x_1, b_y_4}};

        //initialisation de la taille des boutons
        double x = 80; double y = 80;

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
                case "0" : case "1" : case "2" : case "3" : case "4" : case "5" : case "6" : case "7" : case "8" : case "9" :
                case "," :{
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    button.setStyle("-fx-background-color: #5A5A5A");//couleur grise
                    button.setTextFill(Color.WHITE);//texte blanc
                    updateButtonOnClick(button, "-fx-background-color: #5A5A5A");
                    break;
                }
                case "C" : case "%" :
                case "±" : {
                    button.setStyle("-fx-background-color: #bcbcbc");//couleur grise
                    button.setTextFill(Color.GRAY);
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    updateButtonOnClick(button, "-fx-background-color: #bcbcbc");
                    break;
                }
                case "/" : case "x" : case "-" : case "+" :
                case "=" :{
                    button.setStyle("-fx-background-color: #EC9706");//couleur orange
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 36));
                    updateButtonOnClick(button, "-fx-background-color: #EC9706");
                    break;
                }
                case "←" :{
                    button.setStyle("-fx-background-color: #5A5A5A");//couleur grise
                    button.setFont(Font.font("Courier New", FontWeight.BOLD, 38));
                    updateButtonOnClick(button, "-fx-background-color: #5A5A5A");
                    break;
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

    /**
     *
     */
    public void resetHistorique(){
        historique_1 = "";
        historique_2 = "";
        historique_3 = "";
        affichageHistorique_1.setText(historique_1);
        affichageHistorique_2.setText(historique_2);
        affichageHistorique_3.setText(historique_3);
    }

    /**
     *
     * @param button
     * @param couleur
     */
    public void updateButtonOnClick(Button button, String couleur){
        PauseTransition transition = new PauseTransition(Duration.seconds(0.1));
        transition.setOnFinished(event -> button.setStyle(couleur));
        button.setOnMouseClicked(event -> {
            event.consume();
            button.setStyle(couleur_updateButtonOnClick_Key);
            transition.playFromStart();
        });
    }

    /**
     *
     * @param button
     * @param couleur
     */
    public void updateButtonOnKey(Button button, String couleur){
        transition.setOnFinished(event -> button.setStyle(couleur));
        button.setStyle(couleur_updateButtonOnClick_Key);
        transition.playFromStart();
    }

    //Lancement de la calculatrice
    public static void main(String[] args) {
        launch();
    }

    /*--------------------------------------------------------Easter Egg-----------------------------------------------------------------------------*/

    Boolean stop = false; Boolean stop_flashing = false;
    int gameState;
    int darkModeState = 0; int whiteModeState = 1; int easterEggState = 2;
    AtomicReference<Boolean> creditState = new AtomicReference<>(true);

    /**
     *
     * @param stage
     * @param sp
     */
    public void easter_egg_affichage(Stage stage, StackPane sp){

        Button buttonInfo = new Button("i");
        Button buttonDark = new Button("D");
        Button buttonWhite = new Button("W");
        Button easterEgg = new Button();
        Label credits = new Label("Calculatrice développée par Ayfred & Smilaid, 2022Ⓒ\nVersion " + version + ", Mis à jour le " + date + "\nhttps://github.com/Ayfred/Calculatrice_polonaise\nProjet réalisé pour:\n\n\n\n ");
        Label moving_credits = new Label("Calculatrice développée par Ayfred & Smilaid");
        Image IMT_Mines_Ales = new Image("imt_mines_ales.jpg");

        int x = 2; int y = 2;
        int b_x = 5*largeur/11; int b_y = -10*longueur/21;

        credits.setPrefSize(largeur,200);
        credits.setTranslateX(0);
        credits.setTranslateY(-210);
        credits.setAlignment(Pos.CENTER);
        credits.setTextAlignment(TextAlignment.CENTER);
        credits.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        credits.setStyle("-fx-background-color: #444444");
        credits.setTextFill(couleur_texte);
        credits.setVisible(false);
        sp.getChildren().add(credits);

        moving_credits.setTranslateX(0);
        moving_credits.setTranslateY(-100);
        moving_credits.setTextFill(Color.DEEPPINK);
        moving_credits.setVisible(false);
        moving_credits.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        sp.getChildren().add(moving_credits);

        ImageView image_IMT_Mines_Ales = new ImageView(IMT_Mines_Ales);
        image_IMT_Mines_Ales.setTranslateX(0);
        image_IMT_Mines_Ales.setTranslateY(-160);
        image_IMT_Mines_Ales.setFitHeight(80);
        image_IMT_Mines_Ales.setFitWidth(180);
        image_IMT_Mines_Ales.setPreserveRatio(true);
        image_IMT_Mines_Ales.setVisible(false);
        sp.getChildren().add(image_IMT_Mines_Ales);


        buttonDark.setPrefSize(x,y);
        buttonDark.setTranslateX(b_x-50);
        buttonDark.setTranslateY(b_y);
        buttonDark.setShape(new Circle(0.5));
        buttonDark.setStyle("-fx-background-color: #000000");//couleur grise
        buttonDark.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
        buttonDark.setTextFill(Color.WHITE);
        buttonDark.setOnMouseClicked(mouseEvent ->{
            sp.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii( 0.05, 0.05, 0.05, 0.05, true), Insets.EMPTY)));
            couleur_texte = Color.WHITE;
            update_couleur_numero(Color.WHITE, Color.GRAY, Color.WHITE);
            update_dark_white_mode();
            update_couleur_bouton("-fx-background-color: #5A5A5A", "-fx-background-color: #bcbcbc" ,"-fx-background-color: #EC9706");
            buttonDark.setVisible(false);
            buttonWhite.setVisible(true);
            credits.setVisible(false);
            image_IMT_Mines_Ales.setVisible(false);
            titre.setTextFill(Color.WHITE);
            if(gameState == easterEggState){
                stop = true;
                titre.setTranslateX(-b_x + 50);
                titre.setTranslateY(b_y);
                gameState = darkModeState;
                moving_credits.setVisible(false);
                stage.getScene().setCursor(Cursor.DEFAULT);
            }
            creditState.set(true);
            gameState = darkModeState;
            easterEgg.setVisible(false);
            stop_flashing = true;
        });
        sp.getChildren().add(buttonDark);

        buttonDark.setVisible(false);

        buttonWhite.setPrefSize(x,y);
        buttonWhite.setTranslateX(b_x-50);
        buttonWhite.setTranslateY(b_y);
        buttonWhite.setShape(new Circle(0.5));
        buttonWhite.setStyle("-fx-background-color: #FFFFFF");//couleur grise
        buttonWhite.setFont(Font.font("Courier New", FontWeight.NORMAL, 12));
        buttonWhite.setTextFill(Color.BLACK);
        buttonWhite.setOnMouseClicked(mouseEvent ->{
            sp.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii( 0.05, 0.05, 0.05, 0.05, true), Insets.EMPTY)));
            couleur_texte = Color.BLACK;
            update_couleur_numero(Color.WHITE, Color.GRAY, Color.WHITE);
            update_dark_white_mode();
            update_couleur_bouton("-fx-background-color: #5A5A5A", "-fx-background-color: #bcbcbc" ,"-fx-background-color: #EC9706");
            buttonDark.setVisible(true);
            buttonWhite.setVisible(false);
            credits.setVisible(false);
            titre.setTextFill(Color.BLACK);
            image_IMT_Mines_Ales.setVisible(false);
            if(gameState == easterEggState){
                stop = true;
                titre.setTextFill(Color.BLACK);
                titre.setTranslateX(-b_x + 50);
                titre.setTranslateY(b_y);
                gameState = whiteModeState;
                moving_credits.setVisible(false);
                stage.getScene().setCursor(Cursor.DEFAULT);
            }
            creditState.set(true);
            gameState = whiteModeState;
            easterEgg.setVisible(false);
            stop_flashing = true;
        });
        sp.getChildren().add(buttonWhite);

        buttonInfo.setPrefSize(x,y);
        buttonInfo.setTranslateX(b_x-75);
        buttonInfo.setTranslateY(b_y);
        buttonInfo.setShape(new Circle(0.5));
        buttonInfo.setStyle("-fx-background-color: #FFFF00");//couleur blanche
        buttonInfo.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
        buttonInfo.setTextFill(Color.BLACK);
        buttonInfo.setOnMouseClicked(mouseEvent ->{
            if(creditState.get()){
                credits.setVisible(true);
                image_IMT_Mines_Ales.setVisible(true);
                if(gameState == whiteModeState || gameState == darkModeState){
                    stop_flashing = false;
                    flashing(easterEgg);
                    easterEgg.setVisible(true);
                }else{
                    stop_flashing = true;
                    easterEgg.setVisible(false);
                }
                creditState.set(false);
            }
            else{
                credits.setVisible(false);
                easterEgg.setVisible(false);
                image_IMT_Mines_Ales.setVisible(false);
                creditState.set(true);
                stop_flashing = true;
            }
        });

        sp.getChildren().add(buttonInfo);

        Image curseur = new Image("UnicornCursor.png");
        Image easter = new Image("easter.jpg");
        ImageView image_easter = new ImageView(easter);
        image_easter.setFitHeight(40);
        image_easter.setFitWidth(40);
        easterEgg.setGraphic(image_easter);
        easterEgg.setTranslateX(120);
        easterEgg.setTranslateY(-140);
        easterEgg.setBackground(null);
        easterEgg.setOnMouseClicked(mouseEvent ->{
            stage.getScene().setCursor(new ImageCursor(curseur));
            sp.setBackground(new Background(new BackgroundFill(Color.PINK, new CornerRadii( 0.05, 0.05, 0.05, 0.05, true), Insets.EMPTY)));
            couleur_texte = Color.PURPLE;
            update_dark_white_mode(); update_couleur_numero(Color.HOTPINK, Color.DARKBLUE, Color.MEDIUMPURPLE);
            update_couleur_bouton("-fx-background-color: #9702b0", "-fx-background-color: #9702b0" ,"-fx-background-color: #9702b0");
            couleur_updateButtonOnClick_Key = "-fx-background-color: #be2ed6";
            titre.setTextFill(Color.DEEPPINK);
            credits.setVisible(false);
            image_IMT_Mines_Ales.setVisible(false);
            moving_credits.setVisible(true);
            easterEgg.setVisible(false);
            stop = false; stop_flashing = true;
            movingLabel(titre, -110, 30, 0.5);
            movingLabel(moving_credits, -40, 40, 0.5);
            creditState.set(true);
            gameState = easterEggState;
        });
        easterEgg.setVisible(false);
        sp.getChildren().add(easterEgg);
    }

    /**
     *
     */
    public void update_dark_white_mode(){
        affichageResultat.setTextFill(couleur_texte);
        affichageMessage.setTextFill(couleur_texte);
        affichageHistorique_1.setTextFill(couleur_texte);
        affichageHistorique_2.setTextFill(couleur_texte);
        affichageHistorique_3.setTextFill(couleur_texte);
        affichagePile.setTextFill(couleur_texte);
    }

    /**
     *
     * @param color1
     * @param color2
     * @param color3
     */
    public void update_couleur_numero(Color color1, Color color2, Color color3){
        for (Button button : buttons) {
            switch (button.getText()) {
                case "0" : case "1" : case "2" :
                case "3" : case "4" : case "5" : case "6" : case "7" : case "8" : case "9" : case "," :
                case "←" : button.setTextFill(color1); break;
                case "C" : case "%" :
                case "±" : button.setTextFill(color2); break;
                case "/" : case "x" : case "-" : case "+" :
                case "=" : button.setTextFill(color3); break;
            }
        }
    }

    /**
     *
     * @param color1
     * @param color2
     * @param color3
     */
    public void update_couleur_bouton(String color1, String color2, String color3){
        for (Button button : buttons) {
            switch (button.getText()) {
                case "0" : case "1" : case "2" : case "3" : case "4" : case "5" :
                case "6" : case "7" : case "8" : case "9" : case "," :
                case  "←" : {
                    button.setStyle(color1);
                    updateButtonOnClick(button, color1);
                    break;
                }
                case "C" : case "%" :
                case "±" :{
                    button.setStyle(color2);
                    updateButtonOnClick(button, color2);
                    break;
                }
                case "/" : case "x" : case "-" : case "+" :
                case "=" :{
                    button.setStyle(color3);
                    updateButtonOnClick(button, color3);
                    break;
                }

            }
        }
    }

    /**
     *
     * @param label
     * @param debut
     * @param fin
     * @param speed
     */
    public void movingLabel(Label label, int debut, int fin, double speed){
        AtomicReference<Boolean> aller = new AtomicReference<>(true);
        Timeline clock = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.ZERO, event ->{
            if(aller.get() && !stop){
                label.setTranslateX(label.getTranslateX()+ speed);
                if(label.getTranslateX() == fin){
                    aller.set(false);
                }
            }
            else if(!aller.get() && !stop){
                label.setTranslateX(label.getTranslateX() - speed);
                if (label.getTranslateX() == debut) {
                    aller.set(true);
                }
            }
            else{
                clock.stop();
            }
        });
        clock.getKeyFrames().addAll(keyFrame, new KeyFrame(Duration.millis(50)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        if(stop){clock.stop();}
    }

    /**
     *
     * @param button
     */
    public void flashing(Button button){
        AtomicReference<Boolean> presence = new AtomicReference<>(true);
        Timeline clock = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.ZERO, event ->{
            if(presence.get() && !stop_flashing){
                button.setVisible(true);
                presence.set(false);
            }
            else if(!presence.get() && !stop_flashing){
                button.setVisible(false);
                presence.set(true);
            }
            else{
                clock.stop();
            }
        });
        clock.getKeyFrames().addAll(keyFrame, new KeyFrame(Duration.millis(800)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        if(stop_flashing){clock.stop();}
    }
}
