package com.example.calculatrice;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

/**
 * Input permet d'ecouter les evenements lies au clavier
 */
public class Input implements EventHandler<KeyEvent> {//Interface EventHandler avec comme type KeyEvent pour la gestion du clavier

    private final Controleur controleur;

    /**
     * Creation du constructeur
     * @param controleur on entre le controleur de la calculatrice
     */
    public Input(Controleur controleur) {
        this.controleur = controleur;
    }

    /**
     * Handle permet de detecter tous les evenements lies au clavier
     * @param keyEvent parametre qui contient toutes les informations du clavier
     */
    @Override
    public void handle(KeyEvent keyEvent){
        KeyCode key = keyEvent.getCode();
        InterfaceGraphique interfaceGraphique = controleur.interfaceGraphique;
        List<Button> buttons = interfaceGraphique.buttons;

        //Configuration des touches du clavier
        if(key == KeyCode.DIGIT0 || key == KeyCode.NUMPAD0){
            //On fait comprendre au controleur que lorsqu'on appuie sur 0, on veut afficher "0" dans l'affichage du resultat
            controleur.update("0");

            //Permet d'avoir un affichage de vert sur le bouton lorsque l'on appuie sur 0
            interfaceGraphique.updateButtonOnKey(buttons.get(0), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.DIGIT1 || key == KeyCode.NUMPAD1){
            controleur.update("1");
            interfaceGraphique.updateButtonOnKey(buttons.get(1), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.DIGIT2 || key == KeyCode.NUMPAD2){
            controleur.update("2");
            interfaceGraphique.updateButtonOnKey(buttons.get(2), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.DIGIT3 || key == KeyCode.NUMPAD3){
            controleur.update("3");
            interfaceGraphique.updateButtonOnKey(buttons.get(3), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.DIGIT4 || key == KeyCode.NUMPAD4){
            controleur.update("4");
            interfaceGraphique.updateButtonOnKey(buttons.get(4), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.DIGIT5 || key == KeyCode.NUMPAD5){
            controleur.update("5");
            interfaceGraphique.updateButtonOnKey(buttons.get(5), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.DIGIT6 || key == KeyCode.NUMPAD6){
            controleur.update("6");
            interfaceGraphique.updateButtonOnKey(buttons.get(6), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.DIGIT7 || key == KeyCode.NUMPAD7){
            controleur.update("7");
            interfaceGraphique.updateButtonOnKey(buttons.get(7), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.DIGIT8 || key == KeyCode.NUMPAD8){
            controleur.update("8");
            interfaceGraphique.updateButtonOnKey(buttons.get(8), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.DIGIT9 || key == KeyCode.NUMPAD9){
            controleur.update("9");
            interfaceGraphique.updateButtonOnKey(buttons.get(9), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.ENTER ||key == KeyCode.SPACE){
            controleur.push();
            interfaceGraphique.updateButtonOnKey(buttons.get(11), "-fx-background-color: #EC9706");
        }
        else if(key == KeyCode.SUBTRACT){
            controleur.operation("-");
            interfaceGraphique.updateButtonOnKey(buttons.get(13), "-fx-background-color: #EC9706");
        }
        else if(key == KeyCode.ADD){
            controleur.operation("+");
            interfaceGraphique.updateButtonOnKey(buttons.get(12), "-fx-background-color: #EC9706");
        }
        else if(key == KeyCode.DIVIDE){
            controleur.operation("/");
            interfaceGraphique.updateButtonOnKey(buttons.get(15), "-fx-background-color: #EC9706");
        }
        else if(key == KeyCode.ASTERISK || key == KeyCode.MULTIPLY){
            controleur.operation("x");
            interfaceGraphique.updateButtonOnKey(buttons.get(14), "-fx-background-color: #EC9706");
        }
        else if(key == KeyCode.C){
            controleur.accumulateur.clear();
            interfaceGraphique.updateButtonOnKey(buttons.get(10), "-fx-background-color: #bcbcbc");
        }
        else if(key == KeyCode.DECIMAL){
            controleur.virgule();
            interfaceGraphique.updateButtonOnKey(buttons.get(17), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.N){
            controleur.negatif();
            interfaceGraphique.updateButtonOnKey(buttons.get(16), "-fx-background-color: #bcbcbc");
        }
        else if(key == KeyCode.UNDEFINED){//touche % ou ù
            controleur.pourcentage();
            interfaceGraphique.updateButtonOnKey(buttons.get(18), "-fx-background-color: #bcbcbc");
        }
        else if(key == KeyCode.BACK_SPACE){
            controleur.supprimer();
            interfaceGraphique.updateButtonOnKey(buttons.get(19), "-fx-background-color: #5A5A5A");
        }
        else if(key == KeyCode.Q){
            //Ferme la fenêtre
            System.exit(0);
        }

        //Permet de réinitialiser keyEvent à chaque événement du clavier
        keyEvent.consume();
    }
}
