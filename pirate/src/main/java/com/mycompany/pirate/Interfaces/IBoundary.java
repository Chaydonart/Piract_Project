/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pirate.Interfaces;

/**
 *
 * @author BEN JAAFAR
 */
public interface IBoundary extends IPirates {
    void start();
    void spin(int[] values);
    void afficherEtatJeu();
    void afficherMessage(String message);
    void deplacerPion(int deplacement, String name);
    
    
    
}
