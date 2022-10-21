package com.example.calculatrice_0;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class Controleur implements PropertyChangeListener, EventHandler<MouseEvent> {

    public String result = "0.0";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Controleur() {
        support.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        String valeur = ""; String oldvaleur = valeur;
        switch(((Button)mouseEvent.getSource()).getText()){
            case "0" : valeur = "0"; support.firePropertyChange("valeur", oldvaleur, valeur); break;
            case "1" : valeur = "1"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "2" : valeur = "2"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "3" : valeur = "3"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "4" : valeur = "4"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "5" : valeur = "5"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "6" : valeur = "6"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "7" : valeur = "7"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "8" : valeur = "8"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "9" : valeur = "9"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "+" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "*" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "-" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "/" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "push" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "C" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
        }
        if(Objects.equals(result, "0.0") || Objects.equals(result, "0")){
            result = valeur;}
        else{result += valeur;}
        //resultat.setText(result);
        System.out.println("hiihi");
    }
}
