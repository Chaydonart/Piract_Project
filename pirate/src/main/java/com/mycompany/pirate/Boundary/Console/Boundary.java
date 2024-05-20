/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.Console;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.Interfaces.IBoundary;
import java.util.Arrays;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.util.Scanner;

/**
 *
 * @author BEN JAAFAR
 * 
 * Va ensuite en plus implementer IPirates
 */
public class Boundary implements IBoundary {
      private ControlJeu gameLoopController;
    private final PionRepository pionRepository;
    private final Scanner scanner;

    public Boundary(ControlJeu gameLoopController, PionRepository pionRepository) {
        this.gameLoopController = gameLoopController;
        this.pionRepository = pionRepository;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
        afficherMessage("Le jeu commence !");
        afficherMessage("Appuyez sur Entrée pour démarrer le jeu...");
        scanner.nextLine(); // Attendre que l'utilisateur appuie sur Entrée
        gameLoopController.startGame();
    }

    //Partie affichage console (temporaire)
    @Override
    public void spin(int[] values) {
        afficherMessage("Appuyez sur Entrée pour lancer la machine à sous...");
        scanner.nextLine(); // Attendre que l'utilisateur appuie sur Entrée
        afficherMessage("La machine à sous affiche = " + values[0] + " " +  values[1] + " " + values[2]);
        int resultat = Arrays.stream(values).sum();
        afficherMessage("Résultat de la machine " + resultat);
    }

    @Override
    public void afficherEtatJeu() {
        for (Pion pion : pionRepository.getPions()) {
            afficherMessage("RÉCAPITULATIF : Pion " + pion.getName() + " est sur la case " + pion.getPosition());
        }
    }

    @Override
    public void afficherMessage(String message) {
        System.out.println(message);
    }

    @Override    
    public void deplacerPion(int deplacement, String name) {
        afficherMessage("Le " + name + " avance de " + deplacement + " cases");
    }

    // Partie pacerelle dialogue
    @Override
    public void notify(String message) {
        afficherMessage(message);
    }

    @Override 
    public void notifySpin(int[] values) {
        spin(values);
    }

    @Override 
    public void notifyEtatJeu() {
        afficherEtatJeu();
    }

    @Override
    public void notifyCaseDegat(String name, int vie) {
        afficherMessage("Le joueur tombe sur une case DÉGÂTS");
        afficherMessage("Le pion " + name + " a pris des dégâts ! Vie restante : " + vie);
    }

    @Override
    public void notifyCaseRejouer(int[] values, int resultat) {
        afficherMessage("Le joueur tombe sur une case REJOUER");
        notifyEtatJeu();
        afficherMessage("Le joueur va rejouer");
        spin(values);

    }

    @Override
    public void notifyCaseReculer(int[] values, int resultat) {             
        afficherMessage("Le joueur tombe sur une case RECULER");
        notifyEtatJeu();
        spin(values);
    }

    @Override
    public void notifyCaseGambling(String name, int randomValue) {
        afficherMessage("Le " + name + " tombe sur une case GAMBLING");
        afficherMessage("Le " + name + " va donc procéder à un duel contre Gambi le robot !");
        afficherMessage("Duel gambling ! Le joueur doit faire une valeur supérieure à " + randomValue);
    }

    @Override
    public void notifyDeplacerPion(int deplacement, String name) {
        deplacerPion(deplacement, name);
    }
    
    @Override
    public void notifyNouveauTour(String name){
        afficherMessage("# " + name + " prend son tour #");
    }

    // Pour initialiser le jeu
    public void setGameLoopController(ControlJeu gameLoopController) {
        this.gameLoopController = gameLoopController; 
    }
}
