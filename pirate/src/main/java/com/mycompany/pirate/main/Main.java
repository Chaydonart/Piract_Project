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
import com.mycompany.pirate.Services.ServiceDeplacerPion;
import com.mycompany.pirate.Services.ServiceSlotMachine;

/**
 *
 * @author BEN JAAFAR
 */
public class Main {
    public static void main(String[] args) {
        // Initialisation
        Pion joueur1 = new Pion("Joueur 1");
        Pion joueur2 = new Pion("Joueur 2");
        Jeu jeu = new Jeu(Arrays.asList(joueur1, joueur2));
        PionRepository pionRepository = new PionRepository(jeu.getPions());
        
        // Boundary
        Boundary gameUI = new Boundary(null, pionRepository);
        
        //Plteau
        Plateau plateau =  new Plateau(36,gameUI);
        
        // Controlleur
        ServiceDeplacerPion deplacerPionService = new ServiceDeplacerPion(plateau,gameUI);
        ServiceSlotMachine smService = new ServiceSlotMachine();
        ControlDeplacerPion controlDeplacerPion = new ControlDeplacerPion(deplacerPionService, pionRepository);
        ControlSlotMachine controlSlotMachine = new ControlSlotMachine(smService);
        ControlJeu gameLoopController = new ControlJeu(jeu, pionRepository, gameUI,controlDeplacerPion, controlSlotMachine);
        
        //Update controlleur for UI
        gameUI.setGameLoopController(gameLoopController);
        
        //Initialisation du plateau avec les controlleurs pour les cases speciales 
        plateau.initialiser(controlDeplacerPion,controlSlotMachine);
        
        // Démarrez le jeu en appelant start() sur gameUI
        gameUI.start();
    }
}


