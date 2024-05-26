package com.mycompany.pirate.test.mutants;

import com.mycompany.pirate.FonctionnalKernel.Controller.*;
import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.Interfaces.IControlDeplacerPion;
import com.mycompany.pirate.Interfaces.IControlSlotMachine;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.util.Arrays;

/**
 *
 * @author RIBEIRO
 */
public class ControlJeuModifie implements IControlDeplacerPion, IControlSlotMachine{
    private int[] forcedSpinValues;
    protected Jeu jeu;
    protected PionRepository pionRepository;
    protected IDialogue notificationService;
    protected IControlDeplacerPion controlDeplacerPion;
    protected IControlSlotMachine controleSlotMachine;

    public ControlJeuModifie(Jeu jeu, PionRepository pionRepository, IDialogue notificationService,
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
                 notificationService.notifyNouveauTour(pion.getName());

                // Simulation d'un tour de jeu pour le pion
                int[] spinResult = spin();
                
                int deplacement = Arrays.stream(spinResult).sum();
                deplacerPion(pion,deplacement);
                
                //notificationService.notifyEtatJeu();
                
                if(pion.getVie() <= 0){
                    jeu.setGameOver(true);
                    pionRepository.nextPion();
                    notificationService.notifyFinDeJeu(pionRepository.getPionActuel().getName());
                }

                // Check if the game is won by checking pion's position or other criteria
                if (jeu.checkVictory(pion)) {
                    jeu.setGameOver(true);
                    notificationService.notifyFinDeJeu(pion.getName());
                    break;
                }

                pionRepository.nextPion();
            }
            //break pour tester qu'un tour
            break;
        }
    }
    
    public void setForcedSpinValues(int[] values) {
        this.forcedSpinValues = values;
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
        return this.forcedSpinValues;
    }
}
