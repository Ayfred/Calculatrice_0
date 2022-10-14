package com.example.calculatrice_0;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public class Controleur implements PropertyChangeListener {


    int[] x = {1, 5, 6};
    int y = 5;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

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
        this.support.firePropertyChange("value", oldValue, newValue);}


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int y = (int) evt.getNewValue();
    }

    public static void main(String[] args) {






        //Pile p = new Pile();
        //Accumulateur accumulateur = new Accumulateur(p);

        //p.push(5);
        //p.push(6);
        //p.push(1);
        //p.push(6);

        //accumulateur.add();
        //accumulateur.div();



    }


}
