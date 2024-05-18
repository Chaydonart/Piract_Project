/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IControlGamblingDuel;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author RIBEIRO
 */
public class ControlGamblingDuel implements IControlGamblingDuel   {
    
    private final ControlSlotMachine controlSlotMachine;
    private Random random = new Random();

    public ControlGamblingDuel(ControlSlotMachine controlSlotMachine) {
        this.controlSlotMachine = controlSlotMachine;
    }
    
    @Override
    public void duelDeDes(Pion pion, IDialogue notificationServices) {
        //Lancers de dés
        int[] valeurs = controlSlotMachine.spin();
        int res = Arrays.stream(valeurs).sum();
        int randomValue = this.random.nextInt(9);
        
        Optional.ofNullable(notificationServices).ifPresent(service -> service.notifyCaseGambling(pion.getName(),randomValue,res));
        
        //perdant perd une vie
        if (res < randomValue) {
            pion.setVie(pion.getVie() - 1);
            notificationServices.notify(pion.getName() + " a perdu le gambling ! Vie restante : " + pion.getVie());
        } else {
            notificationServices.notify("DUEL REUSSI !");
        }
    }
    
}