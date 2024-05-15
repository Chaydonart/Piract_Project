/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Interfaces.IServiceRejouer;
import com.mycompany.pirate.Interfaces.NotificationService;
import com.mycompany.pirate.Services.ServiceRejouer;
import java.util.Arrays;

/**
 *
 * @author BEN JAAFAR
 */
public class ControlRejouer {
    
    private IServiceRejouer serviceRejouer;

    public ControlRejouer(ControlDeplacerPion controlDeplacerPion, ControleSlotMachine controlSlotMachine, NotificationService notificationService) {
        this.serviceRejouer = new ServiceRejouer(controlDeplacerPion,controlSlotMachine,notificationService);
    }

    public void rejouer() {
        if (serviceRejouer != null) {
            serviceRejouer.rejouer();
        }
    }
    
}
