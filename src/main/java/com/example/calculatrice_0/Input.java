package com.example.calculatrice_0;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class Input implements EventHandler<KeyEvent> {//Interface EventHandler avec comme type KeyEvent pour la gestion du clavier

    private final Controleur controleur;

    //Création du constructeur
    public Input(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void handle(KeyEvent keyEvent){
        System.out.println(keyEvent.getCode());
        InterfaceGraphique interfaceGraphique = controleur.interfaceGraphique;
        List<Button> buttons = interfaceGraphique.buttons;

        //Configuration des touches du clavier
        if(keyEvent.getCode() == KeyCode.DIGIT0 || keyEvent.getCode() == KeyCode.NUMPAD0){
            controleur.update("0");
            interfaceGraphique.updateButtonOnKey(buttons.get(0), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.DIGIT1 || keyEvent.getCode() == KeyCode.NUMPAD1){
            controleur.update("1");
            interfaceGraphique.updateButtonOnKey(buttons.get(1), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.DIGIT2 || keyEvent.getCode() == KeyCode.NUMPAD2){
            controleur.update("2");
            interfaceGraphique.updateButtonOnKey(buttons.get(2), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.DIGIT3 || keyEvent.getCode() == KeyCode.NUMPAD3){
            controleur.update("3");
            interfaceGraphique.updateButtonOnKey(buttons.get(3), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.DIGIT4 || keyEvent.getCode() == KeyCode.NUMPAD4){
            controleur.update("4");
            interfaceGraphique.updateButtonOnKey(buttons.get(4), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.DIGIT5 || keyEvent.getCode() == KeyCode.NUMPAD5){
            controleur.update("5");
            interfaceGraphique.updateButtonOnKey(buttons.get(5), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.DIGIT6 || keyEvent.getCode() == KeyCode.NUMPAD6){
            controleur.update("6");
            interfaceGraphique.updateButtonOnKey(buttons.get(6), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.DIGIT7 || keyEvent.getCode() == KeyCode.NUMPAD7){
            controleur.update("7");
            interfaceGraphique.updateButtonOnKey(buttons.get(7), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.DIGIT8 || keyEvent.getCode() == KeyCode.NUMPAD8){
            controleur.update("8");
            interfaceGraphique.updateButtonOnKey(buttons.get(8), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.DIGIT9 || keyEvent.getCode() == KeyCode.NUMPAD9){
            controleur.update("9");
            interfaceGraphique.updateButtonOnKey(buttons.get(9), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.ENTER){
            controleur.push();
            interfaceGraphique.updateButtonOnKey(buttons.get(11), "-fx-background-color: #EC9706");
        }
        else if(keyEvent.getCode() == KeyCode.SUBTRACT){
            controleur.operation("-");
            interfaceGraphique.updateButtonOnKey(buttons.get(13), "-fx-background-color: #EC9706");
        }
        else if(keyEvent.getCode() == KeyCode.ADD){
            controleur.operation("+");
            interfaceGraphique.updateButtonOnKey(buttons.get(12), "-fx-background-color: #EC9706");
        }
        else if(keyEvent.getCode() == KeyCode.DIVIDE){
            controleur.operation("/");
            interfaceGraphique.updateButtonOnKey(buttons.get(15), "-fx-background-color: #EC9706");
        }
        else if(keyEvent.getCode() == KeyCode.ASTERISK || keyEvent.getCode() == KeyCode.MULTIPLY){
            controleur.operation("x");
            interfaceGraphique.updateButtonOnKey(buttons.get(14), "-fx-background-color: #EC9706");
        }
        else if(keyEvent.getCode() == KeyCode.C){
            controleur.reset();
            interfaceGraphique.updateButtonOnKey(buttons.get(10), "-fx-background-color: #bcbcbc");
        }
        else if(keyEvent.getCode() == KeyCode.DECIMAL){
            controleur.virgule();
            interfaceGraphique.updateButtonOnKey(buttons.get(17), "-fx-background-color: #5A5A5A");
        }
        else if(keyEvent.getCode() == KeyCode.N){
            controleur.negatif();
            interfaceGraphique.updateButtonOnKey(buttons.get(16), "-fx-background-color: #bcbcbc");
        }
        else if(keyEvent.getCode() == KeyCode.UNDEFINED){//touche % ou ù
            controleur.pourcentage();
            interfaceGraphique.updateButtonOnKey(buttons.get(18), "-fx-background-color: #bcbcbc");
        }
        keyEvent.consume();
    }


}
