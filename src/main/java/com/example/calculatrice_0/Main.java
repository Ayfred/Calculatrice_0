package com.example.calculatrice_0;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) {

        Pile p = new Pile();
        Accumulateur accumulateur = new Accumulateur(p);

        p.push(553);
        p.push(6);
        p.push(1);
        p.push(6);

        accumulateur.add();
        //accumulateur.div();

        System.out.println(p.pile);

    }
}