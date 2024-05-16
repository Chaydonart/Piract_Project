/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.main;

import com.mycompany.pirate.Boundary.Console.Boundary;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import java.util.Arrays;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Services.DeplacerPionService;
import com.mycompany.pirate.Services.SlotMachineService;

/**
 *
 * @author BEN JAAFAR
 */
public class Main {
    public static void main(String[] args) {
        // Créez les autres objets nécessaires
        Pion joueur1 = new Pion("Joueur 1");
        Pion joueur2 = new Pion("Joueur 2");
        Jeu jeu = new Jeu(Arrays.asList(joueur1, joueur2));
        PionRepository pionRepository = new PionRepository(jeu.getPions());
        SlotMachineService smService = new SlotMachineService();
        
        // Créez les contrôleurs
        ControlDeplacerPion controlDeplacerPion = new ControlDeplacerPion(new DeplacerPionService(null), pionRepository);
        ControlSlotMachine controlSlotMachine = new ControlSlotMachine(smService);
        
        // Créez le GameLoopController avec le GameUI null
        ControlJeu gameLoopController = new ControlJeu(jeu, pionRepository, null);
        
        // Créez le GameUI avec le GameLoopController null
        Boundary gameUI = new Boundary(controlSlotMachine, controlDeplacerPion, null, pionRepository);
        
        // Mettez à jour le GameLoopController avec le GameUI correct
        gameLoopController.setGameUI(gameUI);
        
        // Mettez à jour le GameUI avec le GameLoopController correct
        gameUI.setGameLoopController(gameLoopController);
        
        // Mettez à jour le service de déplacement de pion pour utiliser le plateau avec le service de notification
        Plateau plateau =  new Plateau(36,gameUI);
        
        DeplacerPionService deplacerPionService = new DeplacerPionService(plateau);
        controlDeplacerPion.setDeplacerPionService(deplacerPionService);
        
        plateau.initialiser(controlDeplacerPion,controlSlotMachine);
        
        // Mettez à jour le GameLoopController avec le GameUI correct
        gameLoopController.setGameUI(gameUI);
        
        // Démarrez le jeu en appelant start() sur gameUI
        gameUI.start();
    }
}


