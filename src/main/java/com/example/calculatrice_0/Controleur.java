package com.example.calculatrice_0;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Controleur implements PropertyChangeListener {




    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("updating: " + evt.getNewValue());
    }
}
