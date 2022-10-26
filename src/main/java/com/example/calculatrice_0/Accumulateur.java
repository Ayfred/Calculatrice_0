package com.example.calculatrice_0;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Accumulateur {
    Pile pile;
    public PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Accumulateur(Pile pile) {
        this.pile = pile;
    }

    //swap le dernier élément avec l'avant dernier élément de la liste sachant qu'on ne peut pas supprimer un élément
    // au milieu de la pile
    public void swap(){
        double temp_dernier = pile.pop();
        double temp_avant_dernier = pile.pop();
        push(temp_dernier);
        push(temp_avant_dernier);
    };

    public void push(double nombre){
        pile.push(nombre);
        support.firePropertyChange("Push", null, pile);
    }

    public void add(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(dernier + avant_dernier);
    };

    public void sub(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(avant_dernier - dernier);
    };

    public void mult(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(dernier * avant_dernier);
    };

    public void div(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        push(avant_dernier / dernier);
    };

    public void neg(){
        double dernier = pile.pop();
        push(-dernier);
    };

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }


}
