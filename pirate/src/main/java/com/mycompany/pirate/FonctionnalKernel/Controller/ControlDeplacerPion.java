package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Services.ServiceDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.Interfaces.IServiceDeplacerPion;

/**
 *
 * @author ESSENGUE MATIS
 */
public class ControlDeplacerPion {

    private IServiceDeplacerPion deplacePionService;
    private PionRepository pionRepository;

    public ControlDeplacerPion(ServiceDeplacerPion deplacePionService, PionRepository pionRepository) {
        this.deplacePionService = deplacePionService;
        this.pionRepository = pionRepository;
    }

    public void deplacerPion(int resultatDe) {
        Pion pionActuel = pionRepository.getPionActuel();
        deplacePionService.deplacerPion(pionActuel, resultatDe);
        pionRepository.save(pionActuel);
    }

    public void setDeplacerPionService(ServiceDeplacerPion deplacerPionService) {
        this.deplacePionService = deplacerPionService ; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
