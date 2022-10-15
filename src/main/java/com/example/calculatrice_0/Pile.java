package com.example.calculatrice_0;

import java.util.Stack;

public class Pile{//héritage
    Stack pile;
    double nombre;

    public Pile() {
        pile = new Stack();//constructeur
    }

    public Stack getPile() {
        return pile;
    }

    public int getLast(){//ne sert à r en vrai t'as raison
        return (int) pile.get(pile.size()-1);
    }

    public void push(double nombre){
        pile.push(nombre);//ajoute un élément à la pile
    };

    public double pop(){
        return (double) pile.pop();//enlève le dernier élément de la pile et retournant le dernier élement
    };

    public void drop(){
        pile.remove(pile.size()-1); //enlève le dernier élément de la pile sans retour
    };

    public void clear(){
        pile.clear();//supprime tous les éléments de la liste
    };

    public Stack clone(){
        return (Stack) pile.clone();
    }
}
