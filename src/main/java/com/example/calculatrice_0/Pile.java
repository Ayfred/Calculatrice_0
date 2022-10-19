package com.example.calculatrice_0;

import java.util.Stack;

public class Pile extends Stack<Double> {//héritage

    public Pile() {
    }

    public double getLast(){//ne sert à r en vrai t'as raison
        return this.get(this.size()-1);
    }

}
