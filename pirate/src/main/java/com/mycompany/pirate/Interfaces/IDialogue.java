/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pirate.Interfaces;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;

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
    
    void notifyCaseDegat(String name);
    
    void notifyCaseRejouer(String name, int resultat);
    
    void notifyCaseReculer(String name, int resultat);
   
    void notifyCaseGambling(String name,int randomValue);
    
    void notifyDeplacerPion(int deplacement, String name);
    
    void notifyNouveauTour(String name);
    
    void notifyFinPartie();
}
