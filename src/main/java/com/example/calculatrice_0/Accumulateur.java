package com.example.calculatrice_0;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Accumulateur {
    Pile pile;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public Accumulateur(Pile pile) {
        this.pile = pile;
    }

    //swap le dernier élément avec l'avant dernier élément de la liste sachant qu'on ne peut pas supprimer un élément
    // au milieu de la pile
    public void swap(){
        double temp_dernier = pile.pop();
        double temp_avant_dernier = pile.pop();
        pile.push(temp_dernier);
        pile.push(temp_avant_dernier);
    };

    public void add(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(dernier + avant_dernier);
    };

    public void sub(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(dernier - avant_dernier);
    };

    public void mult(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(dernier * avant_dernier);
    };

    public void div(){
        double dernier = pile.pop();
        double avant_dernier = pile.pop();
        pile.push(dernier / avant_dernier);
    };

    public void neg(){
        double dernier = pile.pop();
        pile.push(-dernier);
    };

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String newValue) {
        String oldValue = this.value;
        this.value = newValue;
        this.support.firePropertyChange("value", oldValue, newValue);
    }




}
