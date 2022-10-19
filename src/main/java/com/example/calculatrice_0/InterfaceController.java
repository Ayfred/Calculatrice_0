package com.example.calculatrice_0;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.beans.PropertyChangeSupport;

public class InterfaceController {
    @FXML
    private Pane titlePane;
    @FXML
    private Label Resultat;
    public Pile pile = new Pile();
    public Accumulateur accumulateur = new Accumulateur(pile);
    private double x, y;
    private String result = "0";

    Controleur controleur = new Controleur();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public void init(Stage stage) {
        Controleur controleur = new Controleur();
        support.addPropertyChangeListener(controleur);

        support.addPropertyChangeListener(controleur);
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        String valeur = "";
        switch(((Pane)event.getSource()).getId()){
            case "Numero0" : valeur = "0"; break;
            case "Numero1" : valeur = "1"; break;
            case "Numero2" : valeur = "2"; break;
            case "Numero3" : valeur = "3"; break;
            case "Numero4" : valeur = "4"; break;
            case "Numero5" : valeur = "5"; break;
            case "Numero6" : valeur = "6"; break;
            case "Numero7" : valeur = "7"; break;
            case "Numero8" : valeur = "8"; break;
            case "Numero9" : valeur = "9"; break;
        }
        if(result == "0.0" || result == "0"){ result = valeur;}
        else{result += valeur;}
        Resultat.setText(result);
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        switch(((Pane)event.getSource()).getId()){
            case "Add" : support.firePropertyChange("Add","",""); /*accumulateur.add();*/ Resultat.setText(String.valueOf(accumulateur.pile.getLast())); break;
            case "Mult" : accumulateur.mult(); Resultat.setText(String.valueOf(accumulateur.pile.getLast())); break;
            case "Sub" : accumulateur.sub(); Resultat.setText(String.valueOf(accumulateur.pile.getLast())); break;
            case "Div" : accumulateur.div(); Resultat.setText(String.valueOf(accumulateur.pile.getLast())); break;
            case "Entrer" : accumulateur.pile.push(Double.parseDouble(result)); result = "0.0"; break;
            case "Clear" : accumulateur.pile.clear(); result = "0.0"; Resultat.setText(result);  break;
            //case "Push" : accumulateur.pile.push(Double.parseDouble(result)); result = "0.0"; break;
        }
    }
}