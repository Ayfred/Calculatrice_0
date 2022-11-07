package com.example.calculatrice_0;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DecimalFormat;
import java.util.Locale;

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

    /**
     * Methode abstraite qui permet de detecter tous evenements lies au changement de l'etat de la pile
      * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch(evt.getPropertyName()){
            case "+", "-", "x", "/" -> {
                //Affichage du résultat après opération
                double dernier_nombre = accumulateur.pile.getLast();

                //on évite les zéros inutiles après la virgule si on a un type double
                if(dernier_nombre%1 == 0){
                    interfaceGraphique.resultat = String.valueOf((int) dernier_nombre);}
                else{
                    interfaceGraphique.resultat = String.valueOf(dernier_nombre);
                }
                interfaceGraphique.updateAffichageResultat();

                //Mise à jour du message
                if(evt.getPropertyName().equals("+")){
                    interfaceGraphique.message = "Opération addition effectuée";
                }
                else if(evt.getPropertyName().equals("-")){
                    interfaceGraphique.message = "Opération soustraction effectuée";
                }
                else if(evt.getPropertyName().equals("x")){
                    interfaceGraphique.message = "Opération multiplication effectuée";
                }
                else if(evt.getPropertyName().equals("/")){
                    interfaceGraphique.message = "Opération division effectuée";
                }
                interfaceGraphique.updateAffichageMessage();
                historique_resultat = true;

                //Mise à jour de l'affichage de la pile
                interfaceGraphique.affichagePile.setText(String.valueOf(accumulateur.pile));
            }
            case "pushNombre" -> {
                //Affichage du nombre 0
                interfaceGraphique.updateHistorique();
                interfaceGraphique.resultat = "0";
                interfaceGraphique.updateAffichageResultat();

                //Efface le message dès qu'on appuie sur un chiffre
                if(!interfaceGraphique.message.equals("")){
                    interfaceGraphique.message = "";
                    interfaceGraphique.updateAffichageMessage();
                }

                //Mise à jour de l'affichage de la pile
                interfaceGraphique.affichagePile.setText(String.valueOf(accumulateur.pile));
            }
            case "Clear" -> {
                //Réinitialisation du résultat
                interfaceGraphique.resultat = "0";
                interfaceGraphique.updateAffichageResultat();
                //Commentaires
                interfaceGraphique.message = "Effacement de la mémoire terminé";
                interfaceGraphique.updateAffichageMessage();
                //On efface l'historique
                interfaceGraphique.resetHistorique();

                //Mise à jour de l'affichage de la pile
                interfaceGraphique.affichagePile.setText("");
            }

        }
        System.out.println(accumulateur.pile);
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
                case "C" -> accumulateur.clear(); //Effacement de la pile
                case "," -> virgule();
                case "_" -> negatif();
                case "%" -> pourcentage();
                case "<-" -> supprimer();
            }
        }
        mouseEvent.consume();
    }

    //Méthode update
    public void update(String nombre){

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
            //Limite de 9 chiffres en tout et 2 espaces soit un total d'une longueur de 11 pour la chaine de caractère
            if(interfaceGraphique.resultat.length() >= 9){
                interfaceGraphique.message = "Taille maximale atteinte !";
                interfaceGraphique.updateAffichageMessage();
            }
            //Permet d'entrer un nombre de 1 jusqu'à 999 999 999
            else{
                interfaceGraphique.resultat = interfaceGraphique.resultat + nombre;
                System.out.println(interfaceGraphique.resultat);
                interfaceGraphique.resultat = interfaceGraphique.resultat.replaceAll(" ", "");
                int m = 0;
                for(int i = 1; i <= interfaceGraphique.resultat.length()-2; i++){
                    if(i % 3 == 0){

                        System.out.println(interfaceGraphique.resultat.substring(0, interfaceGraphique.resultat.length() -i));
                        System.out.println(interfaceGraphique.resultat.substring(interfaceGraphique.resultat.length()-i));
                        interfaceGraphique.resultat = interfaceGraphique.resultat.substring(0, interfaceGraphique.resultat.length() -i-m) + " " + interfaceGraphique.resultat.substring(interfaceGraphique.resultat.length()-i-m);
                    }
                }
            }
        }

        //mettre à jour l'affichage du nombre sur la calculatrice
        interfaceGraphique.updateAffichageResultat();

        //reset le message
        if(!interfaceGraphique.message.equals("")){
            interfaceGraphique.message = "";
            interfaceGraphique.updateAffichageMessage();
        }
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
        //Gestion d'erreur: Impossibilité de convertir une chaine de caractère avec la présence d'espace en un double
        else if(interfaceGraphique.resultat.contains(" ")){
            //Suppression de l'espace lors du stockage du nombre dans la pile
            interfaceGraphique.resultat = interfaceGraphique.resultat.replaceAll(" ", "");
            accumulateur.push(Double.parseDouble(interfaceGraphique.resultat),"pushNombre");
        }
        else{
            accumulateur.push(Double.parseDouble(interfaceGraphique.resultat),"pushNombre");
        }
    }

    /**
     * Methode qui permet d'ajouter un signe - a un nombre lors de <b>l'affichage</b> de ce dernier
     * On ne MODIFIE PAS la pile
     */
    public void negatif(){
        if(!String.valueOf(interfaceGraphique.resultat.charAt(0)).equals("-") && !interfaceGraphique.resultat.equals("0")){//Si le nombre est positif, on le rend négatif
            interfaceGraphique.resultat = "-" + interfaceGraphique.resultat;
            interfaceGraphique.updateAffichageResultat();
        }
        //Lorsque l'utilisateur essaie de push "-0" ou tout nombre contenant "-0", on ne fait rien
        else if(interfaceGraphique.resultat.equals("0")){

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
        //Gestion d'erreur : Tant que la taille de la pile est supérieure à 2 alors on peut lui appliquer des opérations
        if(accumulateur.pile.size() >= 2){
            //Distinction des opérations
            switch (k) {
                case "+" -> accumulateur.add();
                case "-" -> accumulateur.sub();
                case "x" -> accumulateur.mult();
                case "/" -> {
                    //Gestion d'erreur : Divison par 0
                    if (accumulateur.pile.getLast() == 0) {
                        interfaceGraphique.message = "Erreur division par 0 impossible";
                        interfaceGraphique.updateAffichageMessage();
                        interfaceGraphique.resultat = "Error";
                        interfaceGraphique.updateAffichageResultat();
                    } else {
                        accumulateur.div();
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

    /**
     * Methode supprimer
     */
    public void supprimer(){
        //Gestion d'erreur : Si on essaie de supprimer la chaine de caractère "Error"
        if(interfaceGraphique.resultat.equals("Error")){
            interfaceGraphique.message = "Aucun chiffre à supprimer";
            interfaceGraphique.updateAffichageMessage();
        }
        else if(!interfaceGraphique.resultat.equals("0")){
            //Si le résultat est un chiffre et si on essaie de le supprimer, on retourne à 0
            if(interfaceGraphique.resultat.length() == 1){
                interfaceGraphique.resultat = "0";
                interfaceGraphique.updateAffichageResultat();
            }
            //On supprime le dernier chiffre de résultat
            else{
                interfaceGraphique.resultat = interfaceGraphique.resultat.substring(0, interfaceGraphique.resultat.length()-1);
                interfaceGraphique.updateAffichageResultat();
            }
        }
    }
}
