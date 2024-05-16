/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Interfaces.IServiceRejouer;
import com.mycompany.pirate.Interfaces.NotificationService;
import com.mycompany.pirate.Services.ServiceRejouer;

/**
 *
 * @author BEN JAAFAR
 */
public class ControlRejouer implements IServiceRejouer {
    
    private IServiceRejouer serviceRejouer;

    public ControlRejouer(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine, NotificationService notificationService) {
        this.serviceRejouer = new ServiceRejouer(controlDeplacerPion,controlSlotMachine,notificationService);
    }
       
    @Override
    public void rejouer() {
        if (serviceRejouer != null) {
            serviceRejouer.rejouer();
        }
    }
    
}
