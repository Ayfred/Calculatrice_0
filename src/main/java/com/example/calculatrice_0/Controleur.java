package com.example.calculatrice_0;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Controleur implements PropertyChangeListener, EventHandler<MouseEvent> {//implémentation des interfaces PropertyChangeListener et EventHandler

    Accumulateur accumulateur = new Accumulateur(new Pile());
    public final InterfaceGraphique interfaceGraphique;

    private boolean historique_resultat = false;
    PropertyChangeSupport support = new PropertyChangeSupport(this);


    /**
     * Constructeur controleur avec la structure MVC
     * @param interfaceGraphique permet de faire des manipulations a l'interface graphique telles que le changement d'affichage
     *                           du resultat etc...
     */
    public Controleur(InterfaceGraphique interfaceGraphique) {
        this.interfaceGraphique = interfaceGraphique;
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
                    interfaceGraphique.resultat = String.valueOf((int) dernier_nombre);}
                else{
                    interfaceGraphique.resultat = String.valueOf(dernier_nombre);
                }
                interfaceGraphique.updateAffichageResultat();
                System.out.println(accumulateur.pile);
            }
            case "pushNombre", "Clear" -> {
                //Affichage du nombre 0
                interfaceGraphique.affichageResultat.setText("0"); //ne modifie pas resultat
                System.out.println(accumulateur.pile);
            }
        }
    }

    //Méthode abstraite des événements liés à la souris
    @Override
    public void handle(MouseEvent mouseEvent) {

        //Récupération du texte du bouton qu'on a appuyé
        Button button = (Button) mouseEvent.getSource();
        String k = button.getText();
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED){
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

    }

    //Méthode update
    public void update(String nombre){
        //changement de couleur du bouton
        interfaceGraphique.hold = true;

        //affichage du résultat dans l'historique
        if(historique_resultat){
            interfaceGraphique.updateHistorique();

            //reset pour écraser la donnée lorsqu'on entre un nouveau chiffre
            interfaceGraphique.resultat = "0";

            //ne pas mettre à jour l'historique lorsqu'on tape sur les chiffres sans les push
            historique_resultat = false;
        }

        //mettre à jour l'affichage du nombre sur la calculatrice
        if((interfaceGraphique.resultat.equals("0") && !nombre.equals("."))
                || interfaceGraphique.resultat.equals("Error")){//si 0 et pas de virgule ou si Error, on écrase le nombre
            interfaceGraphique.resultat = nombre;
        }
        else{
            interfaceGraphique.resultat = interfaceGraphique.resultat + nombre;}//Permet d'entrer un nombre ( != un chiffre)

        //mettre à jour l'affichage du nombre sur la calculatrice
        interfaceGraphique.updateAffichageResultat();

        //reset le message
        if(!interfaceGraphique.message.equals("")){
            interfaceGraphique.message = "";
            interfaceGraphique.updateAffichageMessage();
        }
    }

    /**
     * Methode qui permet d'effacer le contenu d'une pile donc utilisation de clear d'accumulateur avec l'ajout de texte
     * pour l'affichage
     */
    public void reset(){
        //Effacement de la pile
        accumulateur.clear();
        //Réinitialisation du résultat
        interfaceGraphique.resultat = "0";
        //Commentaires
        interfaceGraphique.message = "Effacement de la mémoire terminé";
        interfaceGraphique.updateAffichageMessage();
    }

    /**
     * Methode push qui integre la methode push d'accumulateur en rajoutant les changements de texte pour l'affichage
     * de commentaires
     */
    public void push(){
        //Gestion d'erreur : si on essai de push le string "Error"
        if(interfaceGraphique.resultat.equals("Error")){
            interfaceGraphique.message = "Veuillez sélectionner un chiffre";
            interfaceGraphique.updateAffichageMessage();
        }
        else{
        accumulateur.push(Double.parseDouble((interfaceGraphique.affichageResultat.getText())),"nombre");
        interfaceGraphique.updateHistorique();
        interfaceGraphique.resultat = "0";
        if(!interfaceGraphique.message.equals("")){
                interfaceGraphique.message = "";
                interfaceGraphique.updateAffichageMessage();
            }
        }
    }

    /**
     * Methode qui permet d'ajouter un signe - a un nombre lors de l'affichage de ce dernier
     */
    public void negatif(){
        String text = interfaceGraphique.resultat;
        if(!String.valueOf(text.charAt(0)).equals("-")){//Si le nombre est positif, on le rend négatif
            interfaceGraphique.resultat = "-" + interfaceGraphique.resultat;
            interfaceGraphique.updateAffichageResultat();
        }
        else{//sinon on enlève le -
            interfaceGraphique.resultat = interfaceGraphique.resultat.substring(1);
            interfaceGraphique.updateAffichageResultat();
        }
    }


    /**
     * Méthode d'implementation de la virgule
     */
    public void virgule(){
        if(!interfaceGraphique.resultat.contains("."))//On regarde si le resultat contient une virgule
            update(".");
    }


    /**
     * Methode qui permet d'effectuer les operations et en isolant les erreurs liees aux operations
     * ici la division par zero
     * @param k on entre l'operateur + - x /
     */
    public void operation(String k){

        if(accumulateur.pile.size() >= 2){//Tant que la taille de la pile est supérieure à 2 alors on peut lui appliquer des opérations
            //Distinction des opérations
            switch (k){
                case "+" -> {
                    interfaceGraphique.message = "Opération addition effectuée";
                    interfaceGraphique.updateAffichageMessage();
                    accumulateur.add();
                    historique_resultat = true;
                }
                case "x" -> {
                    interfaceGraphique.message = "Opération multiplication effectuée";
                    interfaceGraphique.updateAffichageMessage();
                    accumulateur.mult();
                    historique_resultat = true;
                }
                case "-" -> {
                    interfaceGraphique.message = "Opération soustraction effectuée";
                    interfaceGraphique.updateAffichageMessage();
                    accumulateur.sub();
                    historique_resultat = true;
                }
                case "/" -> {

                    if (accumulateur.pile.getLast() == 0) {//Division par 0
                        interfaceGraphique.message = "Erreur division par 0 impossible";
                        interfaceGraphique.updateAffichageMessage();
                        interfaceGraphique.resultat = "Error";
                        interfaceGraphique.updateAffichageResultat();
                    }
                    else {
                        interfaceGraphique.message = "Opération division effectuée";
                        interfaceGraphique.updateAffichageMessage();
                        accumulateur.div();
                        historique_resultat = true;
                    }
                }
            }
        }
        else{//Si on veut entrer une opération lorsque la taille de la pile est inférieure ou égale à 1
        interfaceGraphique.message = "Veuillez sélectionner un chiffre";
        interfaceGraphique.updateAffichageMessage();}
    }


    /**
     * Creation de la methode pourcentage qui permet de multiplier par 0.01 le chiffre
     */
    public void pourcentage(){
        interfaceGraphique.resultat = String.valueOf(Double.parseDouble(interfaceGraphique.resultat)/100);
        interfaceGraphique.updateAffichageResultat();
    }
}
