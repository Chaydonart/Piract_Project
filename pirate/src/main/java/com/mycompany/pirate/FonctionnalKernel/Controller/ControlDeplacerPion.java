package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Interfaces.IDeplacerPion;

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
        } else if (nouvellePosition < 1) {
            nouvellePosition = 1;
        }
        plateau.retirerPion(pion);
        pion.setPosition(nouvellePosition);
        if(plateau.getCase(pion).isSpecial()){
            System.out.println(pion.getName() + plateau.getCase(pion).toString());
        }
        plateau.poserPion(pion);

      
        return nouvellePosition;
    }

}
