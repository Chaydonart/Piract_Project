/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IControlDeplacerPion;
import com.mycompany.pirate.Interfaces.IControlRejouer;
import com.mycompany.pirate.Interfaces.IControlSlotMachine;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author BEN JAAFAR
 * 
 * Controlleur permettant de gerer la mecanique de la case reculer
 */
public class ControlRejouer implements IControlRejouer, IControlSlotMachine, IControlDeplacerPion {
    
    private ControlDeplacerPion controlDeplacerPion;
    private IDialogue notificationService;
    private ControlSlotMachine controlSlotMachine;
    private int distanceRejoue;

    public ControlRejouer(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine, IDialogue notificationService) {
        this.controlDeplacerPion = controlDeplacerPion;
        this.controlSlotMachine = controlSlotMachine;
        this.notificationService = notificationService;
    }
       
    @Override
    public void rejouer(Pion pion) {
        int[] values = spin();
        int resultat = Arrays.stream(values).sum();
        this.distanceRejoue = resultat;
        Optional.ofNullable(notificationService).ifPresent(service -> service.notifyCaseRejouer(pion.getName(),resultat));
        deplacerPion(pion,resultat); 
    }

    @Override
    public int[] spin() {
        return this.controlSlotMachine.spin();
    }

    @Override
    public void deplacerPion(Pion pion, int deplacement) {
        this.controlDeplacerPion.deplacerPion(pion, deplacement);
    }
    public int getDistanceRejoue() {
		return distanceRejoue;
	}
    
}
