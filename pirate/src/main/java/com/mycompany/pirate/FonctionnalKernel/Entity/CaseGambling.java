/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlGamblingDuel;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.Interfaces.IDialogue;

/**
 *
 * @author RIBEIRO
 */
public class CaseGambling extends Case {
    private IDialogue notificationService;
    private ControlGamblingDuel controlGamblingDuel;

    public CaseGambling(ControlSlotMachine controlSlotMachine, IDialogue notificationService) {
        this.notificationService = notificationService;
        this.controlGamblingDuel =  new ControlGamblingDuel(controlSlotMachine);
    }
    
     @Override
    public void ajouterPion(Pion pion) {
        occupants.add(pion);
        controlGamblingDuel.duelDeDes(pion,notificationService);
    }
   

}
