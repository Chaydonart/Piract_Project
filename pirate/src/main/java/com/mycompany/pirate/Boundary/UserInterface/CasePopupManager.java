/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.POPUP_BOMBE;
import static com.mycompany.pirate.data.FileRef.POPUP_RECULER;
import static com.mycompany.pirate.data.FileRef.POPUP_REJOUER;
import javax.swing.ImageIcon;

/**
 *
 * @author BEN JAAFAR
 * 
 * Permet de ne pas avoir a chager les images a chaque fois et de juste directement 
 * afficher la popup, les images etant toujours chargées
 */
public class CasePopupManager {
    private static final ImageIcon iconBomb = new ImageIcon(POPUP_BOMBE);
    private static final ImageIcon iconRejouer = new ImageIcon(POPUP_REJOUER);
    private static final ImageIcon iconReculer = new ImageIcon(POPUP_RECULER);
    
    public static void popupCaseBomb(){
        new CasePopup(iconBomb,500);
    }
    
    public static void popupCaseRejouer(){
        new CasePopup(iconRejouer,1000);
    }
    
    public static void popupCaseReculer(){
        new CasePopup(iconReculer,1000);
    }
}
