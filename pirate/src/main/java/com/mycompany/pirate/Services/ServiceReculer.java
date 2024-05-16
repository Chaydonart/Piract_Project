/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Services;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.Interfaces.IServiceReculer;
import java.util.Arrays;
import java.util.Optional;
import com.mycompany.pirate.Interfaces.INotificationService;

/**
 *
 * @author RIBEIRO
 */
public class ServiceReculer implements IServiceReculer {
    private ControlDeplacerPion controlDeplacerPion;
    private INotificationService notificationService;
    private ControlSlotMachine controlSlotMachine;
    private int distanceRecule = 0;

    public ServiceReculer(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine, INotificationService notificationService) {
        this.controlDeplacerPion = controlDeplacerPion;
        this.controlSlotMachine = controlSlotMachine;
        this.notificationService = notificationService;
    }
    
    @Override
    public void reculer() {
        //Valeur aléatoire de retour en arrière
        int[] values = controlSlotMachine.spin();
        
        int resultat = -Arrays.stream(values).sum();
        this.distanceRecule = resultat;
       
        Optional.ofNullable(notificationService).ifPresent(service -> service.notifyCaseReculer(values,resultat)); 
        
        controlDeplacerPion.deplacerPion(resultat); //Reculer le pion
    }

    public int getDistanceRecule() {
        return distanceRecule;
    }
}
