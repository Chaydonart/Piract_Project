/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.Console;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Interfaces.IBoundary;
import com.mycompany.pirate.Interfaces.NotificationService;
import java.util.Arrays;
import com.mycompany.pirate.Interfaces.IServiceSlotMachine;

/**
 *
 * @author BEN JAAFAR
 */
public class Boundary implements NotificationService, IBoundary{
    private ControlSlotMachine controlSlotMachine;
    private ControlDeplacerPion controlDeplacePion;
    private ControlJeu gameLoopController;
    private PionRepository pionRepository;

    public Boundary(ControlSlotMachine controlSlotMachine, ControlDeplacerPion controlDeplacePion, ControlJeu gameLoopController, PionRepository pionRepository) {
        this.controlSlotMachine = controlSlotMachine;
        this.controlDeplacePion = controlDeplacePion;
        this.gameLoopController = gameLoopController;
        this.pionRepository = pionRepository;
    }

    public void start() {
        afficherMessage("Le jeu commence !");
        gameLoopController.startGame();
    }

    //on utilise une machine a sous comme dé
    public void spin() {
        int[] values = controlSlotMachine.spin();
        afficherMessage("La machine a sous affiche = " + values[0] + " " +  values[1] + " " + values[2]);
        int resultat = Arrays.stream(values).sum();
        afficherMessage("Resultat de la machine " + resultat);
        deplacerPion(resultat);
    }

    public void deplacerPion(int resultatDe) {
        controlDeplacePion.deplacerPion(resultatDe);
        afficherEtatJeu();
    }

    public void afficherEtatJeu() {
        for (Pion pion : pionRepository.getPions()) {
            afficherMessage("RECAPITULATIF : Pion " + pion.getName() + " est sur la case " + pion.getPosition());
        }
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void notify(String message) {
        afficherMessage(message);
    }

    public void setGameLoopController(ControlJeu gameLoopController) {
        this.gameLoopController = gameLoopController; 
    }
    
}
