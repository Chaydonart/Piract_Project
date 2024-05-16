package com.mycompany.pirate.test;

import java.util.Arrays;

import com.mycompany.pirate.Boundary.Console.Boundary;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Services.ServiceDeplacerPion;
import com.mycompany.pirate.Services.SlotMachineService;

public class TestRes {
    public static Pion joueur1 = new Pion("Joueur 1");
    public static Pion joueur2 = new Pion("Joueur 2");
    public static Jeu jeu = new Jeu(Arrays.asList(joueur1, joueur2));
    public static PionRepository pionRepository = new PionRepository(jeu.getPions());
    public static SlotMachineService smService = new SlotMachineService();
    public static ControlDeplacerPion controlDeplacerPion = new ControlDeplacerPion(new ServiceDeplacerPion(null), pionRepository);
    public static ControlSlotMachine controlSlotMachine = new ControlSlotMachine(smService); 
    public static ControlJeu gameLoopController = new ControlJeu(jeu, pionRepository, null);
    public static Boundary gameUI = new Boundary(controlSlotMachine, controlDeplacerPion, gameLoopController, pionRepository);
    public static Plateau plateau =  new Plateau(36,gameUI);
    public static ServiceDeplacerPion deplacerPionService = new ServiceDeplacerPion(plateau);

}
