/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlReculer;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.Interfaces.IDialogue;

/**
 *
 * @author RIBEIRO
 */
public class CaseReculer extends Case {
    private final ControlReculer controlReculer;
    
    public CaseReculer(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine, IDialogue notificationService) {
        this.controlReculer = new ControlReculer(controlDeplacerPion,controlSlotMachine,notificationService);
    }
    
    @Override
    public void ajouterPion(Pion pion) {
        if (controlReculer != null) {
            controlReculer.reculer(pion);
        }
    }
}
