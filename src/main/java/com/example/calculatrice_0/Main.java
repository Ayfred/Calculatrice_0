package com.example.calculatrice_0;

public class Main {

    public static void main(String[] args) {

        Pile p = new Pile();
        Accumulateur accumulateur = new Accumulateur(p);

        p.push(553);
        p.push(6);
        p.push(1);
        p.push(6);
        p.push(7);


        accumulateur.add();
        accumulateur.div();
        accumulateur.mult();
        accumulateur.sub();
        accumulateur.neg();
        try{
            accumulateur.sub();}
        catch(Exception e){
            System.out.println("Opération impossible");
        };


        //System.out.println(p.pile);

    }
}