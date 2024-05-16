package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Boundary.Console.Boundary;
import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;

public class ControlJeu {
    private Jeu jeu;
    private PionRepository pionRepository;
    private Boundary gameUI;

    public ControlJeu(Jeu jeu, PionRepository pionRepository, Boundary gameUI) {
        this.jeu = jeu;
        this.pionRepository = pionRepository;
        this.gameUI = gameUI;
    }

    public void startGame() {
        while (!jeu.isGameOver()) {
            for (Pion pion : pionRepository.getPions()) {
                if (jeu.isGameOver()) break;
                gameUI.afficherMessage("");
                gameUI.afficherMessage("C'est le tour du " + pion.getName());
                gameUI.spin();
                
                if(pion.getVie() <= 0){
                    jeu.setGameOver(true);
                }

                // Check if the game is won by checking pion's position or other criteria
                if (jeu.checkVictory(pion)) {
                    gameUI.afficherMessage("Le " + pion.getName() + " a gagne !");
                    jeu.setGameOver(true);
                    break;
                }

                pionRepository.nextPion();
            }
        }
    }

    public void setGameUI(Boundary gameUI) {
        this.gameUI = gameUI;
    }
}