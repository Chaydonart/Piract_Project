/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Services;

import com.mycompany.pirate.FonctionnalKernel.Entity.Case;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Interfaces.IServiceDeplacerPion;

/**
 *
 * @author BEN JAAFAR & RIBEIRO
 */
public class ServiceDeplacerPion implements IServiceDeplacerPion {
    private Plateau plateau;

    public ServiceDeplacerPion(Plateau plateau) {
        this.plateau = plateau;
    }
    
    @Override
    public void deplacerPion(Pion pion, int deplacement) {
        plateau.retirerPion(pion);
        int nouvellePosition;

        //Pour reculer s'assurer que le deplacement n'envoie pas à une case négative
        if (pion.getPosition() + deplacement < 1) {
            nouvellePosition = 1;
        // S'assurer de ne pas dépasser le plateau
        } else if (pion.getPosition() + deplacement > plateau.getNbCases()) {
            nouvellePosition = plateau.getNbCases(); 
        } else {
            nouvellePosition = pion.getPosition() + deplacement;
        }

        //System.out.println("TEST Le joueur atterrit sur la case " + nouvellePosition);
        pion.setPosition(nouvellePosition);
        plateau.poserPion(pion);
        

        Case caseActuelle = plateau.getCase(pion);
        if(caseActuelle.isSpecial()){
            // Logique pour les cases spéciales
        }
    }
    
}
