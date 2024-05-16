/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Interfaces.IServiceReculer;
import com.mycompany.pirate.Interfaces.NotificationService;
import com.mycompany.pirate.Services.ServiceReculer;

/**
 *
 * @author RIBEIRO
 */
public class ControlReculer implements IServiceReculer{
    private IServiceReculer serviceReculer;
   
    public ControlReculer(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine, NotificationService notificationService) {
        this.serviceReculer = new ServiceReculer(controlDeplacerPion,controlSlotMachine,notificationService);
    }
    
    @Override
    public void reculer() {
        if (serviceReculer != null) {
            serviceReculer.reculer();
        }
    }
    
}