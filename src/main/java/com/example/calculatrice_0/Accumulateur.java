package com.example.calculatrice_0;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Stack;

public class Accumulateur {
    Pile pile;
    Controleur controleur = new Controleur();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Accumulateur(Pile pile) {
        this.pile = pile;
        support.addPropertyChangeListener(controleur);
    }

    //swap le dernier élément avec l'avant dernier élément de la liste sachant qu'on ne peut pas supprimer un élément
    // au milieu de la pile
    public void swap(){
        Pile oldPile = (Pile) pile.clone();
        double temp_dernier = pile.pop();
        double temp_avant_dernier = pile.pop();
        pile.push(temp_dernier);
        pile.push(temp_avant_dernier);
        this.support.firePropertyChange("Pile", oldPile, pile);
    };



    public void add(){
        Pile oldPile = (Pile) pile.clone();
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(dernier + avant_dernier);
        this.support.firePropertyChange("Pile", oldPile, pile);
    };

    public void sub(){
        Pile oldPile = (Pile) pile.clone();
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(avant_dernier - dernier);
        this.support.firePropertyChange("Pile", oldPile, pile);
    };

    public void mult(){
        Pile oldPile = (Pile) pile.clone();
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(dernier * avant_dernier);
        this.support.firePropertyChange("Pile", oldPile, pile);
    };

    public void div(){
        Pile oldPile = (Pile) pile.clone();
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(avant_dernier / dernier);
        this.support.firePropertyChange("Pile", oldPile, pile);
    };

    public void neg(){
        Pile oldPile = (Pile) pile.clone();
        double dernier = pile.pop();
        pile.push(-dernier);
        this.support.firePropertyChange("Pile", oldPile, pile);
    };

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }


}
