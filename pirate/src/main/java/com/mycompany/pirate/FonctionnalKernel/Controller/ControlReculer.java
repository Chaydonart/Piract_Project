/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IControlDeplacerPion;
import com.mycompany.pirate.Interfaces.IControlReculer;
import com.mycompany.pirate.Interfaces.IControlSlotMachine;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author RIBEIRO & BEN JAAFAR
 */
public class ControlReculer implements IControlReculer, IControlDeplacerPion, IControlSlotMachine {
    private ControlDeplacerPion controlDeplacerPion;
    private IDialogue notificationService;
    private ControlSlotMachine controlSlotMachine;
    private int distanceRecule = 0;
   
    public ControlReculer(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine, IDialogue notificationService) {
        this.controlDeplacerPion = controlDeplacerPion;
        this.notificationService = notificationService;
        this.controlSlotMachine = controlSlotMachine;
        
    }
    
    @Override
    public void reculer(Pion pion) {
        Optional.ofNullable(notificationService).ifPresent(service -> service.notifyCaseReculer());
        
        int[] values = spin();;//Valeur aléatoire de retour en arrière
        int resultat = -Arrays.stream(values).sum();
        this.distanceRecule = resultat;
        
        deplacerPion(pion,resultat); 
    }
    
    public int getDistanceRecule() {
        return distanceRecule;
    }

    @Override
    public void deplacerPion(Pion pion, int deplacement) {
        this.controlDeplacerPion.deplacerPion(pion, deplacement);
    }

    @Override
    public int[] spin() {
        return this.controlSlotMachine.spin();
    }
    
}