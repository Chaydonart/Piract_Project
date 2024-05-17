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
public interface INotificationService {
    void notify(String message);
    
    void notifySpin(int[] values);
    
    void notifyEtatJeu();
    
    void notifyCaseDegat(String name, int vie);
    
    void notifyCaseRejouer(int[] values, int resultat);
    
    void notifyCaseReculer(int[] values, int resultat);
   
    void notifyCaseGambling(String name,int randomValue,int res);
    
    void notifyDeplacerPion(int deplacement, String name);
}
