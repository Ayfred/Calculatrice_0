package com.example.calculatrice_0;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Accumulateur {
    Pile pile;
    public PropertyChangeSupport support = new PropertyChangeSupport(this);
    //public PropertyChangeSupport support;

    public Accumulateur(Pile pile) {
        this.pile = pile;
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

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
    };

    public void push(double nombre, String operation){
        pile.push(nombre);
        if(operation.equals("operateur")){
            support.firePropertyChange("pushOperateur", null, pile);
        }
        else{
            support.firePropertyChange("pushNombre", null, pile);
        }
    }

    public void clear(){
        pile.clear();
        support.firePropertyChange("Clear", null, pile);
    }

    public void add(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(dernier + avant_dernier), "operateur");
    };

    public void sub(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(avant_dernier - dernier), "operateur");
    };

    public void mult(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(dernier * avant_dernier), "operateur");
    };

    public void div(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(arrondi(avant_dernier / dernier), "operateur");
    };

    public void neg(){
        double dernier = pile.pop();
        push(-dernier, "operateur");
    }
}
