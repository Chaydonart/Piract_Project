/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IControlRejouer;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author BEN JAAFAR
 */
public class ControlRejouer implements IControlRejouer {
    
    private ControlDeplacerPion controlDeplacerPion;
    private IDialogue notificationService;
    private ControlSlotMachine controlSlotMachine;

    public ControlRejouer(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine, IDialogue notificationService) {
        this.controlDeplacerPion = controlDeplacerPion;
        this.controlSlotMachine = controlSlotMachine;
        this.notificationService = notificationService;
    }
       
    @Override
    public void rejouer(Pion pion) {
        int[] values = controlSlotMachine.spin();
        int resultat = Arrays.stream(values).sum();
   
        Optional.ofNullable(notificationService).ifPresent(service -> service.notifyCaseRejouer(values,resultat)); 
            
        
        controlDeplacerPion.deplacerPion(pion,resultat); 
    }
    
}
