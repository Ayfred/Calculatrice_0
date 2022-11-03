package com.example.calculatrice_0;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Accumulateur {
    Pile pile;
    //Ajout d'un PropertyChangeSupport
    public PropertyChangeSupport support = new PropertyChangeSupport(this);
    //Constructeur
    public Accumulateur(Pile pile) {
        this.pile = pile;
    }

    //Méthode d'ajout d'un listener
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    //Méthode arrondi le chiffre au 5 millième près
    public double arrondi(double nombre){
        if(nombre%1 != 0){
            double temp = 1e5;
            return Math.round(nombre * temp) / temp;
        }
        return nombre;
    }

    //swap le dernier élément avec l'avant dernier élément de la liste sachant qu'on ne peut pas supprimer un élément
    // au milieu de la pile
    public void swap(){
        double temp_dernier = pile.pop();
        double temp_avant_dernier = pile.pop();
        push(temp_dernier, "");
        push(temp_avant_dernier, "");
    }

    //Méthode push
    public void push(double nombre, String operation){
        pile.push(nombre);
        //Distinction du push avec un opérateur et avec un nombre
        if(operation.equals("operateur")){
            support.firePropertyChange("pushOperateur", null, pile);
        }
        else{
            support.firePropertyChange("pushNombre", null, pile);
        }
    }

    //Méthode effacement de la pile
    public void clear(){
        pile.clear();
        support.firePropertyChange("Clear", null, pile);
    }

    //Méthode addition
    public void add(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(dernier + avant_dernier), "operateur");
    }

    //Méthode soustraction
    public void sub(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(avant_dernier - dernier), "operateur");
    }

    //Méthode multipication
    public void mult(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(dernier * avant_dernier), "operateur");
    }

    //Méthode division
    public void div(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(avant_dernier / dernier), "operateur");
    }

    //Méthode négation
    public void neg(){
        double dernier = pile.pop();
        push(-dernier, "operateur");
    }
}
