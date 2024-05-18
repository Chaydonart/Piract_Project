package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.Interfaces.IControlDeplacerPion;
import com.mycompany.pirate.Interfaces.IControlSlotMachine;
import java.util.Arrays;
import com.mycompany.pirate.Interfaces.IDialogue;

public class ControlJeu implements IControlDeplacerPion, IControlSlotMachine{
    private Jeu jeu;
    private PionRepository pionRepository;
    private IDialogue notificationService;
    private IControlDeplacerPion controlDeplacerPion;
    private IControlSlotMachine controleSlotMachine;

    public ControlJeu(Jeu jeu, PionRepository pionRepository, IDialogue notificationService,
                      ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controleSlotMachine) {
        this.jeu = jeu;
        this.pionRepository = pionRepository;
        this.notificationService = notificationService;
        this.controlDeplacerPion = controlDeplacerPion;
        this.controleSlotMachine = controleSlotMachine;
    }

    public void startGame() {
        while (!jeu.isGameOver()) {
            for (Pion pion : pionRepository.getPions()) {
                if (jeu.isGameOver()) break;
                 notificationService.notify(pion.getName() + " prend son tour");

                // Simulation d'un tour de jeu pour le pion
                int[] spinResult = spin();
                notificationService.notifySpin(spinResult);
                
                int deplacement = Arrays.stream(spinResult).sum();
                deplacerPion(pion,deplacement);
                notificationService.notifyEtatJeu();
           
                if(pion.getVie() <= 0){
                    jeu.setGameOver(true);
                }

                // Check if the game is won by checking pion's position or other criteria
                if (jeu.checkVictory(pion)) {
                    notificationService.notify("Le " + pion.getName() + " a gagne !");
                    jeu.setGameOver(true);
                    break;
                }

                pionRepository.nextPion();
                notificationService.notify("");
            }
        }
    }
    
    public void setNotificationService(IDialogue notificationService){
        this.notificationService = notificationService;   
    }
    
    
    //Override des controlleurs pour permettre les updates dans le futur
    @Override
    public void deplacerPion(Pion pion, int deplacement) {
        this.controlDeplacerPion.deplacerPion(pion,deplacement);
    }

    @Override
    public int[] spin() {
        return this.controleSlotMachine.spin();
    }

}