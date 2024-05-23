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
    
    public void spinMachineUI(int[] values);
    public void newTurn();
    public void endGame();
    public void takeDamage(String name);
    public void gamblingDuelResult(String name, boolean win);
    public void caseBombe();
    public void caseRejouer(String name);
    public void caseReculer();
    public void caseGambling(int value);
    
}
