package com.example.calculatrice_0;

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

    //Méthode d'ajout d'un listener
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Methode arrondi le nombre a cinq chiffres apres la virgule
     * @param nombre qu'on veut arrondir
     */
    public double arrondi(double nombre){
        if(nombre%1 != 0){
            double temp = 1e5;
            return Math.round(nombre * temp) / temp;
        }
        return nombre;
    }

    /**
     * Methode qui echange le dernier element avec l'avant dernier element de la liste sachant qu'on ne peut pas supprimer un element
     * au milieu de la pile
     */
    public void swap(){
        double temp_dernier = pile.pop();
        double temp_avant_dernier = pile.pop();
        push(temp_dernier, "Swap");
        push(temp_avant_dernier, "Swap");
    }

    /**
     * Methode push avec integration du firePropertyChange qui permet de detecter tout changement de la pile
     * @param nombre qu'on veut mettre dans la pile
     * @param operation quelle type... operateur ou nombre
     */
    public void push(double nombre, String operation){
        pile.push(nombre);
        //Distinction du push avec un opérateur et avec un nombre
        if(operation.equals("+")){
            support.firePropertyChange(operation, null, pile);
        }
        else if(operation.equals("-")){
            support.firePropertyChange(operation, null, pile);
        }
        else if(operation.equals("x")){
            support.firePropertyChange(operation, null, pile);
        }
        else if(operation.equals("/")){
            support.firePropertyChange(operation, null, pile);
        }
        else if(operation.equals("pushNombre")){
            support.firePropertyChange("pushNombre", null, pile);
        }
    }

    /**
     * Methode clear qui permet d'effacer le contenu d'une pile
     * et integration de firePropertyChange
     */
    public void clear(){
        pile.clear();
        support.firePropertyChange("Clear", null, pile);
    }

    /**
     * Methode addition avec integration de la methode arrondi et push
     */
    public void add(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(dernier + avant_dernier), "+");
    }

    /**
     * Methode soustraction avec la methode push et arrondi
     */
    public void sub(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(avant_dernier - dernier), "-");
    }

    /**
     * Methode multiplication avec la methode arrondi et push
     */
    public void mult(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(dernier * avant_dernier), "x");
    }

    /**
     * Methode division avec la methode arrondi et push
     */
    public void div(){
        /*double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(avant_dernier / dernier), "/");*/
        swap();
        push(arrondi(pile.pop()/pile.pop()), "/");
    }

    /**
     * Methode negation qui permet de changer le signe du dernier element de la pile
     */
    public void neg(){
        double dernier = pile.pop();
        push(-dernier, "operateur");
    }
}
