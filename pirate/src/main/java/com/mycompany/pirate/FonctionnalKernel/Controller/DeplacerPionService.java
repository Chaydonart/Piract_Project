/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Case;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Interfaces.IServiceDeplacerPion;

/**
 *
 * @author BEN JAAFAR
 */
public class DeplacerPionService implements IServiceDeplacerPion {
    private Plateau plateau;

    public DeplacerPionService(Plateau plateau) {
        this.plateau = plateau;
    }
    
    @Override
    public void deplacerPion(Pion pion, int deplacement) {
        plateau.retirerPion(pion);
        int nouvellePosition = pion.getPosition() + deplacement;

        if (nouvellePosition > plateau.getNbCases()) {
            nouvellePosition = plateau.getNbCases(); // S'assurer de ne pas dépasser le plateau
        }

        pion.setPosition(nouvellePosition);
        plateau.poserPion(pion);

        Case caseActuelle = plateau.getCase(pion);
        if(caseActuelle.isSpecial()){
            // Logique pour les cases spéciales
        }
    }
    
}
