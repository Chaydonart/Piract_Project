/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.Console;

import com.mycompany.pirate.Boundary.UserInterface.UI;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.Interfaces.IBoundary;
import com.mycompany.pirate.Interfaces.IDialogue;
import com.mycompany.pirate.Interfaces.IPirates;

/**
 *
 * @author BEN JAAFAR
 * 
 * Va ensuite en plus implementer IPirates
 */
public class Boundary implements IPirates,IDialogue {
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

    //Partie affichage console (temporaire)
    @Override
    public void spinMachine(int[] values) {
        this.GUI.spinMachine(values);
    }
    
    @Override
    public void notifySpin(int[] values) {
    	spinMachine(values);
    }
//    @Override 
//    public void tourSuivant(){
//        this.GUI.newTurn();
//    }
//    
//    @Override
//    public void afficherEtatJeu() {
//    	for (Pion pion : pionRepository.getPions()) {
//            notify("RÉCAPITULATIF : Pion " + pion.getName() + " est sur la case " + pion.getPosition());
//        }
//    }

//    @Override
//    public void afficherMessage(String message) {
//        System.out.println(message);
//    }

    

    // Partie pacerelle dialogue
    @Override
    public void notify(String message) {
        System.out.println(message);
    }


    @Override 
    public void notifyEtatJeu() {
    	for (Pion pion : pionRepository.getPions()) {
            notify("RÉCAPITULATIF : Pion " + pion.getName() + " est sur la case " + pion.getPosition());
        }
    }

    @Override
    public void notifyCaseDegat(String name, int vie) {
    	caseBombe();
    	this.GUI.takeDamage(name);
    }

    @Override
    public void caseBombe() {
    	this.GUI.caseBombe();
 
    }
    @Override
    public void notifyCaseRejouer(String name) {
        this.GUI.caseRejouer(name);
    }

    @Override
    public void notifyCaseReculer() {             
        this.GUI.caseReculer();
    }

    @Override
    public void notifyCaseGambling(int randomValue) {
        this.GUI.caseGambling(randomValue);
    }

    @Override
    public void notifyDeplacerPion(int deplacement, String name) {
        movePiece(deplacement, name);
    }
    @Override
    public void movePiece(int deplacement, String name) {
    	this.GUI.movePiece(deplacement, name);
    }
    
    @Override
    public void notifyNouveauTour(String name){
        newTurn();
    }
    @Override
    public void newTurn() {
    	this.GUI.newTurn();
    }

    @Override
    public void notifyFinDeJeu() {
       endGame();
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


}
