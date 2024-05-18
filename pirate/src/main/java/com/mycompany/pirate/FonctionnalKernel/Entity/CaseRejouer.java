/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlRejouer;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.Interfaces.IDialogue;

/**
 *
 * @author BEN JAAFAR
 */
public class CaseRejouer extends Case {
    private ControlRejouer controlRejouer;
     
    public CaseRejouer(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine, IDialogue notificationService) {
        this.controlRejouer = new ControlRejouer(controlDeplacerPion,controlSlotMachine,notificationService);
    }

    @Override
    public void ajouterPion(Pion pion) {
        controlRejouer.rejouer(pion);
    }

}
