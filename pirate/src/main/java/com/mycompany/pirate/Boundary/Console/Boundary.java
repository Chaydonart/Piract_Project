/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.Console;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.Interfaces.IBoundary;
import com.mycompany.pirate.Interfaces.NotificationService;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author BEN JAAFAR
 * 
 * Va ensuite en plus implementer IPirates
 */
public class Boundary implements NotificationService, IBoundary{
    private ControlJeu gameLoopController;
    private PionRepository pionRepository;
    private Scanner scanner =  new Scanner(System.in); 
    
    public Boundary(ControlJeu gameLoopController, PionRepository pionRepository) {

        this.gameLoopController = gameLoopController;
        this.pionRepository = pionRepository;
    }

    public void start() {
        afficherMessage("Le jeu commence !");
        gameLoopController.startGame();
    }

    //Partie affichage console (temporaire)
    public void spin(int[] values) {
        afficherMessage("La machine a sous affiche = " + values[0] + " " +  values[1] + " " + values[2]);
        int resultat = Arrays.stream(values).sum();
        afficherMessage("Resultat de la machine " + resultat);
    }

    public void deplacerPion(int resultatDe) {
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
    
    
    // Partie pacerelle dialogue
    @Override
    public void notify(String message) {
        afficherMessage(message);
    }
    
    @Override 
    public void notifySpin(int[] values){
        spin(values);
    }
    
    @Override 
    public void notifyDeplacement(int deplacement){
        deplacerPion(deplacement);
    }

    @Override
    public void notifyCaseDegat(String name, int vie) {
        afficherMessage("Le pion " + name + " a pris des degats ! Vie restante : " + vie);
    }

    @Override
    public void notifyCaseRejouer(int[] values, int resultat) {
        afficherMessage("Le joueur tombe sur une case REJOUER");
        notifyDeplacement(resultat);
        afficherMessage("Le joueur va rejouer");
        afficherMessage("La machine affiche = " + values[0] + " " +  values[1] + " " + values[2]);
        afficherMessage("Le joueur avance de " + resultat + " cases");
    }

    @Override
    public void notifyCaseReculer(int[] values, int resultat) {             
        afficherMessage("Le joueur tombe sur une case RECULER");
        notifyDeplacement(resultat);
        afficherMessage("La machine affiche = " + values[0] + " " +  values[1] + " " + values[2]);
        afficherMessage("Le joueur recule de " + (-resultat) + " cases");
    }

    @Override
    public void notifyCaseGambling(String name,int randomValue,int res) {
        afficherMessage("Le " + name + " tombe sur une case GAMBLING");
        afficherMessage("Le " + name + " va donc proceder a un duel contre Gambi le robot !");
        afficherMessage("Duel gambling ! Le joueur doit faire une valeur superieure a "+ randomValue);
        afficherMessage("La roulette affiche... " + res + " !");
    }
    
    // Pour intialiser le jeu
    public void setGameLoopController(ControlJeu gameLoopController) {
        this.gameLoopController = gameLoopController; 
    }
    
}
