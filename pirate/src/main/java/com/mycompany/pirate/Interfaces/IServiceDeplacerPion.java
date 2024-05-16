package com.mycompany.pirate.Interfaces;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;

public interface IServiceDeplacerPion {
    /**
     *
     * @author ESSENGUE MATIS
     */
    public void deplacerPion(Pion pion, int deplacement);
}
