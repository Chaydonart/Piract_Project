/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.Console;

import com.mycompany.pirate.Boundary.UserInterface.UI;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.Interfaces.IDialogue;
import com.mycompany.pirate.Interfaces.IPirates;

/**
 *
 * @author BEN JAAFAR
 * 
 * Permet de connecter l'interface au jeu
 */
public class Boundary implements IPirates, IDialogue {
    private final PionRepository pionRepository;
    private UI GUI;

    public Boundary(ControlJeu gameLoopController, PionRepository pionRepository) {
        this.pionRepository = pionRepository;
    }

    @Override
    public void startGUI() {
        GUI = new UI();
        GUI.startGUI();
    }

    // Partie pacerelle dialogue
    @Override
    public void notifySpin(int[] values) {
        spinMachineUI(values);
    }

    @Override 
    public void notifyEtatJeu() {
    
    }

    @Override
    public void notifyCaseDegat(String name, int vie) {
    	caseBombe();
    	takeDamage(name);
    }

    @Override
    public void notifyCaseRejouer(String name) {
        caseRejouer(name);
    }

    @Override
    public void notifyCaseReculer() {             
        caseReculer();
    }

    @Override
    public void notifyCaseGambling(int randomValue) {
        caseGambling(randomValue);
    }

    @Override
    public void notifyDeplacerPion(int deplacement, String name) {
        movePiece(deplacement, name);
    }
    
    @Override
    public void notifyFinDeJeu() {
       endGame();
    }
    
    @Override
    public void notifyNouveauTour(String name){
        newTurn();
    }
    
    //Methode d'IPirates
    @Override
    public void movePiece(int deplacement, String name) {
    	this.GUI.movePiece(deplacement, name);
    }

    @Override
    public void newTurn() {
    	this.GUI.newTurn();
    }
    
    @Override
    public void endGame() {
    	GUI.endGame();
    }
    
    @Override
    public void notifyDuelResult(String name, boolean win){
        this.GUI.gamblingDuelResult(name,win);
    }

    @Override
    public void takeDamage(String name) {
        this.GUI.takeDamage(name);
    }

    @Override
    public void gamblingDuelResult(String name, boolean win) {
        this.GUI.gamblingDuelResult(name, win);
    }

    @Override
    public void caseRejouer(String name) {
        this.GUI.caseRejouer(name);
    }

    @Override
    public void caseReculer() {
        this.GUI.caseReculer();
    }

    @Override
    public void caseGambling(int value) {
        this.GUI.caseGambling(value);
    }

    @Override
    public void spinMachineUI(int[] values) {
        this.GUI.spinMachineUI(values);
    }
    
    @Override
    public void caseBombe() {
        this.GUI.caseBombe();
    }


}
