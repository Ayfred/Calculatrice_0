package com.example.calculatrice_0;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Controleur implements PropertyChangeListener, EventHandler<MouseEvent> {

    Accumulateur accumulateur = new Accumulateur(new Pile());
    private final Modele modele;

    public Controleur(Modele modele) {
        this.modele = modele;
        PropertyChangeSupport support = new PropertyChangeSupport(this);
        support.addPropertyChangeListener(this);
        accumulateur.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "pushOperateur" -> {
                modele.affichageResultat.setText(Double.toString(accumulateur.pile.getLast()));
                System.out.println(accumulateur.pile);
            }
            case "pushNombre", "Clear" -> {
                modele.affichageResultat.setText("0");
                System.out.println(accumulateur.pile);
            }
        }
    }

    public void update(String nombre){
        modele.updateResultat(nombre);
        modele.updateAffichageResultat();
    }

    public void reset(){
        accumulateur.clear();
        modele.resultat = "0";
    }

    public void push(){
        accumulateur.push(Double.parseDouble((modele.affichageResultat.getText())),"nombre");
        modele.resultat = "0";
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        switch (((Button) mouseEvent.getSource()).getText()) {
            case "0" -> update("0");
            case "1" -> update("1");
            case "2" -> update("2");
            case "3" -> update("3");
            case "4" -> update("4");
            case "5" -> update("5");
            case "6" -> update("6");
            case "7" -> update("7");
            case "8" -> update("8");
            case "9" -> update("9");
            case "+" -> accumulateur.add();
            case "x" -> accumulateur.mult();
            case "-" -> accumulateur.sub();
            case "/" -> accumulateur.div();
            case "push" -> push();
            case "C" -> reset();
            case "'" -> update(",");
        }
    }
}
