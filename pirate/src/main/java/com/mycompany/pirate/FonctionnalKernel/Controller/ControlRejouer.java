/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Interfaces.IServiceRejouer;
import com.mycompany.pirate.Services.ServiceRejouer;
import com.mycompany.pirate.Interfaces.INotificationService;

/**
 *
 * @author BEN JAAFAR
 */
public class ControlRejouer implements IServiceRejouer {
    
    private IServiceRejouer serviceRejouer;

    public ControlRejouer(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine, INotificationService notificationService) {
        this.serviceRejouer = new ServiceRejouer(controlDeplacerPion,controlSlotMachine,notificationService);
    }
       
    @Override
    public void rejouer() {
        if (serviceRejouer != null) {
            serviceRejouer.rejouer();
        }
    }
    
}
