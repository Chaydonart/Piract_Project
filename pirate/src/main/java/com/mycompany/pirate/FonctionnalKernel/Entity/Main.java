/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import TempBoundary.Boundary;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import java.util.Arrays;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControleSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Controller.DeplacerPionService;
import com.mycompany.pirate.FonctionnalKernel.Controller.SlotMachineService;

/**
 *
 * @author BEN JAAFAR
 */
public class Main {
    public static void main(String[] args) {
        Pion joueur1 = new Pion("Joueur 1");
        Pion joueur2 = new Pion("Joueur 2");
        
        Jeu jeu = new Jeu(Arrays.asList(joueur1, joueur2));
        Plateau plateau = new Plateau(32);

        SlotMachineService smService = new SlotMachineService();
        ControleSlotMachine controlSlotMachine = new ControleSlotMachine(smService);
        
        
        PionRepository pionRepository = new PionRepository(jeu.getPions());
        DeplacerPionService deplacePionService = new DeplacerPionService(plateau);
        ControlDeplacerPion controlDeplacePion = new ControlDeplacerPion(deplacePionService, pionRepository);

         
        Boundary gameUI = new Boundary(controlSlotMachine, controlDeplacePion, null, pionRepository);
        ControlJeu gameLoopController = new ControlJeu(jeu, pionRepository, gameUI);

        gameUI = new Boundary(controlSlotMachine, controlDeplacePion, gameLoopController, pionRepository);

        gameUI.start();
    }
}
