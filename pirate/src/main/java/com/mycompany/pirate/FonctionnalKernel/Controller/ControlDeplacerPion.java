package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Interfaces.IServiceDeplacerPion;

/**
 *
 * @author ESSENGUE MATIS
 */
public class ControlDeplacerPion  {

    private DeplacerPionService deplacePionService;
    private PionRepository pionRepository;

    public ControlDeplacerPion(DeplacerPionService deplacePionService, PionRepository pionRepository) {
        this.deplacePionService = deplacePionService;
        this.pionRepository = pionRepository;
    }

    public void deplacerPion(int resultatDe) {
        Pion pionActuel = pionRepository.getPionActuel();
        deplacePionService.deplacerPion(pionActuel, resultatDe);
        pionRepository.save(pionActuel);
    }

    public void setDeplacerPionService(DeplacerPionService deplacerPionService) {
        this.deplacePionService = deplacerPionService ; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
