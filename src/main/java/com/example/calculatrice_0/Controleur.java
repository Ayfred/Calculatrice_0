package com.example.calculatrice_0;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Controleur implements PropertyChangeListener, EventHandler<MouseEvent> {

    Accumulateur accumulateur = new Accumulateur(new Pile());
    public final Modele modele;

    private boolean historique_resultat = false;

    //Constructeur
    public Controleur(Modele modele) {
        this.modele = modele;
        PropertyChangeSupport support = new PropertyChangeSupport(this);
        support.addPropertyChangeListener(this);
        accumulateur.addPropertyChangeListener(this);
    }

    //Listener
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        switch(evt.getPropertyName()){
            case "pushOperateur" -> {
                //affichage du résultat après opération
                double dernier_nombre = accumulateur.pile.getLast();
                //on évite les zéros inutiles après la virgule si on a un type double
                if(dernier_nombre%1 == 0){
                    modele.resultat = String.valueOf((int) dernier_nombre);}
                else{
                    modele.resultat = String.valueOf(dernier_nombre);
                }
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

    //Méthode abstraite des événements liés à la souris
    @Override
    public void handle(MouseEvent mouseEvent) {
        //Récupération du texte du bouton qu'on a appuiyé
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
            case "+", "-", "x", "/" -> operation(k);
            case "push" -> push();
            case "C" -> reset();
            case "," -> virgule();
            case "_" -> negatif();
            case "%" -> pourcentage();
        }
    }

    //Méthode update
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
        if((modele.resultat.equals("0.0") || modele.resultat.equals("0")) && !nombre.equals(".")){
            modele.resultat = nombre;
        }
        else{
            modele.resultat = modele.resultat + nombre;}//Permet d'entrer un nombre ( != un chiffre)

        //mettre à jour l'affichage du nombre sur la calculatrice
        modele.updateAffichageResultat();

        //reset le message
        if(!modele.message.equals("")){
            modele.message = "";
            modele.updateAffichageMessage();
        }
    }

    //Méthode reset
    public void reset(){
        //Effacement de la pile
        accumulateur.clear();
        //Réinitialisation du résultat
        modele.resultat = "0";
        //Commentaires
        modele.message = "Effacement de la mémoire terminé";
        modele.updateAffichageMessage();
    }

    //Méthode push
    public void push(){
        accumulateur.push(Double.parseDouble((modele.affichageResultat.getText())),"nombre");
        modele.updateHistorique();
        modele.resultat = "0";
        if(!modele.message.equals("")){
            modele.message = "";
            modele.updateAffichageMessage();
        }
    }

    //Méthode négatif
    public void negatif(){
        String text = modele.resultat;
        if(!String.valueOf(text.charAt(0)).equals("-")){//Si le nombre est positif, on le rend négatif
            modele.resultat = "-" + modele.resultat;
            modele.updateAffichageResultat();
        }
        else{//sinon on enlève le -
            modele.resultat = modele.resultat.substring(1);
            modele.updateAffichageResultat();
        }
    }

    //Méthode d'implementation de la virgule
    public void virgule(){
        if(!modele.resultat.contains("."))
            update(".");
    }


    public void operation(String k){

        if(accumulateur.pile.size() >= 2){//Tant que la taille de la pile est supérieure à 2 alors on peut lui appliquer des opérations
            //Distinction des opérations
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
                    if (accumulateur.pile.getLast() == 0) {//Division par 0
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
        else{//Si on veut entrer une opération lorsque la taille de la pile est inférieure ou égale à 1
        modele.message = "Veuillez sélectionner un chiffre";
        modele.updateAffichageMessage();}
    }

    //Création de la méthode pourcentage qui permet de multiplier par 0.01 le chiffre
    public void pourcentage(){
        modele.resultat = String.valueOf(Double.parseDouble(modele.resultat)/100);
        modele.updateAffichageResultat();
    }
}
