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

    private boolean historique_resultat = false;

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
                //affichage du résultat après opération
                modele.resultat = String.valueOf(accumulateur.pile.getLast());
                modele.updateAffichageResultat();
                System.out.println(accumulateur.pile);
            }
            case "pushNombre", "Clear" -> {
                //Affichage du nombre 0
                modele.affichageResultat.setText("0"); //ne modifie pas resultat
                System.out.println(accumulateur.pile);
            }
        }
    }

    public void update(String nombre){

        //affichage du résultat dans l'historique
        if(historique_resultat){
            modele.updateHistorique();

            //reset pour écraser la donnée lorsqu'on entre un nouveau chiffre
            modele.resultat = "0";

            //ne pas mettre à jour l'historique lorsqu'on tape sur les chiffres sans les push
            historique_resultat = false;
        }

        //mettre à jour l'affichage du nombre sur la calculatrice
        modele.updateResultat(nombre);
        modele.updateAffichageResultat();

        //reset le message
        if(!modele.message.equals("")){
            modele.message = "";
            modele.updateAffichageMessage();
        }
    }

    public void reset(){
        accumulateur.clear();
        modele.resultat = "0";

        modele.message = "Effacement de la mémoire en cours...";
        modele.updateAffichageMessage();
    }

    public void push(){
        accumulateur.push(Double.parseDouble((modele.affichageResultat.getText())),"nombre");
        modele.updateHistorique();
        modele.resultat = "0";
        if(!modele.message.equals("")){
            modele.message = "";
            modele.updateAffichageMessage();
        }
    }

    public void negatif(){
        String text = modele.resultat;
        if(!String.valueOf(text.charAt(0)).equals("-")){
            modele.resultat = "-" + modele.resultat;
            modele.updateAffichageResultat();
        }
        else{
            modele.resultat = modele.resultat.substring(1);
            modele.updateAffichageResultat();
        }
    }

    public void virgule(){
        if(!modele.resultat.contains("."))
            update(".");
    }

    public void messageErreur(String k){
        if(accumulateur.pile.size() >= 2){
            switch (k){
                case "+" -> {
                    accumulateur.add();
                    historique_resultat = true;
                }

                case "x" -> {
                    accumulateur.mult();
                    historique_resultat = true;
                }

                case "-" -> {
                    accumulateur.sub();
                    historique_resultat = true;
                }

                case "/" -> {
                    if (accumulateur.pile.getLast() == 0) {
                        modele.message = "Erreur opération impossible";
                        modele.updateAffichageMessage();
                        modele.resultat = "Error";
                        modele.updateAffichageResultat();
                    }
                    else {
                        accumulateur.div();
                        historique_resultat = true;
                    }

                }
            }
        }
        else{
        modele.message = "Veuillez sélectionner un chiffre";
        modele.updateAffichageMessage();}
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        String k = ((Button) mouseEvent.getSource()).getText();
        switch (k) {
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
            case "+", "-", "x", "/" -> messageErreur(k);
            case "push" -> push();
            case "C" -> reset();
            case "," -> virgule();
            case "_" -> negatif();
        }
    }
}
