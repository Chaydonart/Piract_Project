/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.Console;

import com.mycompany.pirate.Boundary.UserInterface.UI;
import com.mycompany.pirate.Boundary.UserInterface.uiTesting;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.Interfaces.IBoundary;
import java.util.Arrays;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author BEN JAAFAR
 * 
 * Va ensuite en plus implementer IPirates
 */
public class Boundary implements IBoundary {
    private final PionRepository pionRepository;
    private uiTesting GUI;

    public Boundary(ControlJeu gameLoopController, PionRepository pionRepository) {
        this.pionRepository = pionRepository;
    }

    @Override
    public void start() {
        GUI = new uiTesting();
        GUI.startGUI();
    }

    //Partie affichage console (temporaire)
    @Override
    public void spin(int[] values) {
        this.GUI.spinMachine(values);
    }
    
    @Override 
    public void tourSuivant(){
        this.GUI.nouveauTour();
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
       GUI.deplacerPion(deplacement);
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
        tourSuivant();
    }

}
