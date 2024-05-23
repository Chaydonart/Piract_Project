package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Interfaces.IControlDeplacerPion;
import java.util.Optional;
import com.mycompany.pirate.Interfaces.IDialogue;

/**
 *
 * @author ESSENGUE MATIS
 */
public class ControlDeplacerPion implements IControlDeplacerPion {
    
    private PionRepository pionRepository;
    private Plateau plateau;
    private IDialogue notificationService;

    public ControlDeplacerPion(Plateau plateau, IDialogue notificationService, PionRepository pionRepository) {
        this.pionRepository = pionRepository;
        this.plateau = plateau;
        this.notificationService = notificationService;
    }
    
    @Override
    public void deplacerPion(Pion pion, int deplacement) {
        plateau.retirerPion(pion);
        int nouvellePosition;

        //Pour reculer s'assurer que le deplacement n'envoie pas à une case négative
        if (pion.getPosition() + deplacement <= 1) {
            nouvellePosition = 1;
        // S'assurer de ne pas dépasser le plateau
        } else if (pion.getPosition() + deplacement > plateau.getNbCases()) {
            nouvellePosition = plateau.getNbCases(); 
        } else {
            nouvellePosition = pion.getPosition() + deplacement;
        }
        Optional.ofNullable(notificationService).ifPresent(service -> service.notifyDeplacerPion(deplacement,pion.getName())); 
        pion.setPosition(nouvellePosition);
        plateau.poserPion(pion);
    }
    
    public PionRepository getPionRepository() {
        return pionRepository;
    }

}
