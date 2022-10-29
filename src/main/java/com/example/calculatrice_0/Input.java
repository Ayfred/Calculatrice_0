package com.example.calculatrice_0;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input implements EventHandler<KeyEvent> {


    private final Controleur controleur;

    public Input(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        //System.out.println(keyEvent.getCode());
        if(keyEvent.getCode() == KeyCode.DIGIT0 || keyEvent.getCode() == KeyCode.NUMPAD0){
            controleur.update("0");}
        else if(keyEvent.getCode() == KeyCode.DIGIT1 || keyEvent.getCode() == KeyCode.NUMPAD1){
            controleur.update("1");}
        else if(keyEvent.getCode() == KeyCode.DIGIT2 || keyEvent.getCode() == KeyCode.NUMPAD2){
            controleur.update("2");}
        else if(keyEvent.getCode() == KeyCode.DIGIT3 || keyEvent.getCode() == KeyCode.NUMPAD3){
            controleur.update("3");}
        else if(keyEvent.getCode() == KeyCode.DIGIT4 || keyEvent.getCode() == KeyCode.NUMPAD4){
            controleur.update("4");}
        else if(keyEvent.getCode() == KeyCode.DIGIT5 || keyEvent.getCode() == KeyCode.NUMPAD5){
            controleur.update("5");}
        else if(keyEvent.getCode() == KeyCode.DIGIT6 || keyEvent.getCode() == KeyCode.NUMPAD6){
            controleur.update("6");}
        else if(keyEvent.getCode() == KeyCode.DIGIT7 || keyEvent.getCode() == KeyCode.NUMPAD7){
            controleur.update("7");}
        else if(keyEvent.getCode() == KeyCode.DIGIT8 || keyEvent.getCode() == KeyCode.NUMPAD8){
            controleur.update("8");}
        else if(keyEvent.getCode() == KeyCode.DIGIT9 || keyEvent.getCode() == KeyCode.NUMPAD9){
            controleur.update("9");}
        else if(keyEvent.getCode() == KeyCode.ENTER){
            controleur.push();}
        else if(keyEvent.getCode() == KeyCode.SUBTRACT){
            controleur.operation("-");}
        else if(keyEvent.getCode() == KeyCode.ADD){
            controleur.operation("+");}
        else if(keyEvent.getCode() == KeyCode.DIVIDE){
            controleur.operation("/");}
        else if(keyEvent.getCode() == KeyCode.ASTERISK || keyEvent.getCode() == KeyCode.MULTIPLY){
            controleur.operation("x");}
        else if(keyEvent.getCode() == KeyCode.C){
            controleur.reset();}
        else if(keyEvent.getCode() == KeyCode.PERIOD){
            controleur.virgule();}
        else if(keyEvent.getCode() == KeyCode.N){
            controleur.negatif();}
        else if(keyEvent.getCode() == KeyCode.UNDEFINED){//touche % ou ù
            controleur.pourcentage();}
        else if(keyEvent.getCode() == KeyCode.T){
            controleur.modele.gameState(2);
        }
        else if(keyEvent.getCode() == KeyCode.SPACE){
            controleur.modele.gameState(1);
        }
    }

}
