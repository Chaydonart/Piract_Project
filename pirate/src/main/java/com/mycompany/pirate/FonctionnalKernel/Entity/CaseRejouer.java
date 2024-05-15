/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlRejouer;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControleSlotMachine;
import com.mycompany.pirate.Interfaces.IServiceRejouer;
import com.mycompany.pirate.Interfaces.NotificationService;
import com.mycompany.pirate.Services.ServiceRejouer;

/**
 *
 * @author BEN JAAFAR
 */
public class CaseRejouer extends Case {
     private IServiceRejouer serviceRejouer;
     
    public CaseRejouer(ControlDeplacerPion controlDeplacerPion, ControleSlotMachine controlSlotMachine, NotificationService notificationService) {
        this.serviceRejouer = new ServiceRejouer(controlDeplacerPion,controlSlotMachine,notificationService);
    }

    @Override
    public void ajouterPion(Pion pion) {
        if (serviceRejouer != null) {
            serviceRejouer.rejouer();
        }
    }

}
