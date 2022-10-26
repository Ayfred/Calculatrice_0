package com.example.calculatrice_0;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

public class Controleur implements PropertyChangeListener, EventHandler<MouseEvent> {

    Accumulateur accumulateur = new Accumulateur(new Pile());

    public String result = "0.0";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Interface_version2 interface_version2;

    public Controleur(Interface_version2 interface_version2) {
        this.interface_version2 = interface_version2;
        support.addPropertyChangeListener(this);
        accumulateur.support.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){

            case "Push" : interface_version2.resultat.setText("0"); break;
        }
        System.out.println(evt.getPropertyName());
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        String valeur = ""; String oldvaleur = valeur;
        switch(((Button)mouseEvent.getSource()).getText()){
            case "0" : interface_version2.resultat.setText("0"); break;
            case "1" : interface_version2.resultat.setText("1"); break;
            case "2" : interface_version2.resultat.setText("2"); break;
            case "3" : interface_version2.resultat.setText("3");break;
            case "4" : interface_version2.resultat.setText("4");break;
            case "5" : interface_version2.resultat.setText("5");break;
            case "6" : interface_version2.resultat.setText("6");break;
            case "7" : valeur = "7"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "8" : valeur = "8"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "9" : valeur = "9"; support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "+" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "*" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "-" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "/" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
            case "push" : accumulateur.push(Double.parseDouble((interface_version2.resultat.getText())));
            case "C" : support.firePropertyChange("valeur", oldvaleur, valeur);break;
        }
        if(Objects.equals(result, "0.0") || Objects.equals(result, "0")){
            result = valeur;}
        else{result += valeur;}
        //resultat.setText(result);
        System.out.println("hiihi");
    }
}
