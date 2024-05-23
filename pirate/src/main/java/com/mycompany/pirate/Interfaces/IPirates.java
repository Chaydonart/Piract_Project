/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pirate.Interfaces;

/**
 *
 * @author BEN JAAFAR
 */
public interface IPirates {
    /* Il nous faut
    
    startGUI()
    
    deplcerPion()
    
    spinMachine()
    
    changerTurn()
    
    */
    public void movePiece(int deplacement, String name);
    public void startGUI();
    
    public void spinMachine(int[] values);
    public void newTurn();
    public void endGame();
    public void takeDamage(String name);
    public void gamblingDuelReussi(String name, boolean win);
    public void caseBombe(String name);
    public void caseRejouer();
    public void caseReculer();
    public void caseGambling(int value);
    
}
