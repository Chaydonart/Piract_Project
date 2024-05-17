package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Boundary.Console.Boundary;
import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.Interfaces.NotificationService;
import java.util.Arrays;

public class ControlJeu {
   private Jeu jeu;
    private PionRepository pionRepository;
    private NotificationService notificationService;
    private ControlDeplacerPion controlDeplacerPion;
    private ControlSlotMachine controleSlotMachine;

    public ControlJeu(Jeu jeu, PionRepository pionRepository, NotificationService notificationService,
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
                int[] spinResult = controleSlotMachine.spin();
                notificationService.notifySpin(spinResult);
                
                int deplacement = Arrays.stream(spinResult).sum();
                notificationService.notify("Le " + pion.getName() + " se déplace de " + deplacement + " cases.");
                
                controlDeplacerPion.deplacerPion(deplacement);
                notificationService.notifyDeplacement(deplacement);
                
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
    
    public void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;   
    }

}