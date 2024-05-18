/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IControlReculer;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author RIBEIRO
 */
public class ControlReculer implements IControlReculer{
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
        //Valeur aléatoire de retour en arrière
        int[] values = controlSlotMachine.spin();
        
        int resultat = -Arrays.stream(values).sum();
        this.distanceRecule = resultat;
       
        Optional.ofNullable(notificationService).ifPresent(service -> service.notifyCaseReculer(values,resultat)); 
        
        controlDeplacerPion.deplacerPion(pion,resultat); 
    }
    
    public int getDistanceRecule() {
        return distanceRecule;
    }
    
}