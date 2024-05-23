/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pirate.Interfaces;

/**
 *
 * @author BEN JAAFAR
 * 
 * Adaptateur qui permet le dialogue entre le jeu et la boundary
 */
public interface IDialogue {
    void notify(String message);
    
    void notifySpin(int[] values);
    
    void notifyEtatJeu();
    
    void notifyCaseDegat(String name, int vie);
    
    void notifyCaseRejouer(String name);
    
    void notifyCaseReculer();
   
    void notifyCaseGambling(int randomValue);  
    void notifyDuelResult(String name, boolean win);
    
    void notifyDeplacerPion(int deplacement, String name);
    
    void notifyNouveauTour(String name);
    
    void notifyFinDeJeu();
    
  
}
