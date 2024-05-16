/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Services;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.Interfaces.IServiceReculer;
import com.mycompany.pirate.Interfaces.NotificationService;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author RIBEIRO
 */
public class ServiceReculer implements IServiceReculer {
    private ControlDeplacerPion controlDeplacerPion;
    private NotificationService notificationService;
    private ControlSlotMachine controlSlotMachine;

    public ServiceReculer(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine, NotificationService notificationService) {
        this.controlDeplacerPion = controlDeplacerPion;
        this.controlSlotMachine = controlSlotMachine;
        this.notificationService = notificationService;
    }
    
    @Override
    public void reculer() {
        //Valeur aléatoire de retour en arrière
        int[] values = controlSlotMachine.spin();
        int resultat = -Arrays.stream(values).sum();
       
        Optional.ofNullable(notificationService).ifPresent(service -> service.notifyCaseReculer(values,resultat)); 
        
        controlDeplacerPion.deplacerPion(resultat); //Reculer le pion
    }
}
