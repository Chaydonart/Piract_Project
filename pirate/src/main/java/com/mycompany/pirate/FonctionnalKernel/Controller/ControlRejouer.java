/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Interfaces.NotificationService;
import com.mycompany.pirate.Services.ServiceRejouer;
import java.util.Arrays;

/**
 *
 * @author BEN JAAFAR
 */
public class ControlRejouer {
    
    private ServiceRejouer serviceRejouer;

    public ControlRejouer(ServiceRejouer serviceRejouer) {
        this.serviceRejouer = serviceRejouer;
    }

    public void rejouer() {
        if (serviceRejouer != null) {
            serviceRejouer.rejouer();
        }
    }
    
}
