package com.mycompany.pirate.Boundary.interfaces;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;

public interface IDeplacerPion {
    /**
     *
     * @author ESSENGUE MATIS
     */
    int deplacerPion(Pion pion, Plateau plateau, int deplacement);
}
