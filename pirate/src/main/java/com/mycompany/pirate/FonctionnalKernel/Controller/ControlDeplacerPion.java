package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Boundary.interfaces.IDeplacerPion;

/**
 *
 * @author ESSENGUE MATIS
 */
public class ControlDeplacerPion implements IDeplacerPion {

    @Override
    public int deplacerPion(Pion pion, Plateau plateau, int deplacement) {
        int position = pion.getPosition();
        int nouvellePosition = position + deplacement;
        if (nouvellePosition > plateau.getNbCases()) {
            nouvellePosition = plateau.getNbCases();
        }
        plateau.retirerPion(pion);
        pion.setPosition(nouvellePosition);
        plateau.poserPion(pion);
        
        if(plateau.getCase(pion).isSpecial()){
            System.out.println("SPECIALE = "+plateau.getCase(pion).toString());
            System.out.println("VIE = "+ pion.getVie());
        }
      
        return nouvellePosition;
    }

}
