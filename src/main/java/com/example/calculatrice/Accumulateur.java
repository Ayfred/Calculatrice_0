package com.example.calculatrice;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * L'accumulateur represente une memoire qui contient le resultat du calcul en cours
 */
public class Accumulateur {
    Pile pile;
    public PropertyChangeSupport support = new PropertyChangeSupport(this);//Ajout d'un PropertyChangeSupport

    /**
     * Constructeur accumulateur
     * @param pile on entre la pile pour effectuer des operations avec les elements de la pile
     */
    public Accumulateur(Pile pile) {
        this.pile = pile;
    }

    /**
     * Ajout d'un PropertyChangeListener
     * @param listener Listener qui permet de detecter les changements
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Arrondi le nombre a cinq chiffres apres la virgule
     * @param nombre On entre le nombre qu'on veut arrondir
     * @return nombre arrondi a cinq chiffres
     */
    public double arrondi(double nombre){
        if(nombre%1 != 0){
            double temp = 1e5;
            return Math.round(nombre * temp) / temp;
        }
        return nombre;
    }

    /**
     * Echange le dernier element avec l'avant dernier element de la pile
     */
    public void swap(){
        double temp_dernier = pile.pop();
        double temp_avant_dernier = pile.pop();
        push(temp_dernier, "Swap");
        push(temp_avant_dernier, "Swap");
    }

    /**
     * Methode push utilisant les methodes firePropertyChange et push de la pile
     * @param nombre Nombre qu'on souhaite stocker dans la pile
     * @param operation quelle type... operateur ou nombre
     */
    public void push(double nombre, String operation){
        pile.push(nombre);

        //Distinction du push avec un opérateur et avec un nombre
        switch (operation) {
            case "+": case "-" : case "x": case "/" :
                support.firePropertyChange(operation, null, pile); break;
            case "pushNombre" : support.firePropertyChange("pushNombre", null, pile); break;
        }
    }

    /**
     * Efface le contenu d'une pile et averti le listener avec firePropertyChange
     */
    public void clear(){
        pile.clear();
        support.firePropertyChange("Clear", null, pile);
    }

    /**
     * Addition du dernier et l'avant dernier l'element de la pile
     */
    public void add(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(dernier + avant_dernier), "+");
    }

    /**
     * Soustrait l'avant dernier element avec le dernier element de la pile
     */
    public void sub(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(avant_dernier - dernier), "-");
    }

    /**
     * Multiplie le dernier element avec l'avant denrier element de la pile
     */
    public void mult(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(dernier * avant_dernier), "x");
    }

    /**
     * Divise l'avant dernier element avec le dernier element de la pile
     */
    public void div(){
        swap();
        push(arrondi(pile.pop()/pile.pop()), "/");
    }

    /**
     * Change le signe du dernier element de la pile
     */
    public void neg(){
        double dernier = pile.pop();
        push(-dernier, "operateur");
    }
}
