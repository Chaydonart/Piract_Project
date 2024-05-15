package com.mycompany.pirate.FonctionnalKernel.Controller;

import TempBoundary.Boundary;
import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.Interfaces.IControlJeu;
import com.mycompany.pirate.Interfaces.IDeplacerPion;
import com.mycompany.pirate.Interfaces.ISlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;

public class ControlJeu  {
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
                gameUI.afficherMessage("C'est le tour du pion " + pion.getName());
                gameUI.lancerDe();
                
                if(pion.getVie() <= 0){
                    jeu.setGameOver(true);
                }

                // Check if the game is won by checking pion's position or other criteria
                if (jeu.checkVictory(pion)) {
                    gameUI.afficherMessage("Le pion " + pion.getName() + " a gagné !");
                    jeu.setGameOver(true);
                    break;
                }

                pionRepository.nextPion();
            }
        }
    }

    public void setGameUI(Boundary gameUI) {
        this.gameUI = gameUI; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}