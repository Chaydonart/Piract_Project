/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IControlGamblingDuel;
import com.mycompany.pirate.Interfaces.IControlSlotMachine;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author RIBEIRO
 */
public class ControlGamblingDuel implements IControlGamblingDuel, IControlSlotMachine   {
    
    private final IControlSlotMachine controlSlotMachine;
    private Random random = new Random();

    public ControlGamblingDuel(ControlSlotMachine controlSlotMachine) {
        this.controlSlotMachine = controlSlotMachine;
    }
    
    @Override
    public int duelDeDes(Pion pion, IDialogue notificationServices) {
        int randomValue = this.random.nextInt(9);
        Optional.ofNullable(notificationServices).ifPresent(service -> service.notifyCaseGambling(randomValue));
        
        //Lancers de dés
        int[] valeurs = spin();
        int res = Arrays.stream(valeurs).sum();
  
        
        //perdant perd une vie
        if (res < randomValue) {
            pion.setVie(pion.getVie() - 1);
            Optional.ofNullable(notificationServices).ifPresent(service -> service.notify(pion.getName() + " a perdu le gambling ! Vie restante : " + pion.getVie()));
            Optional.ofNullable(notificationServices).ifPresent(service -> service.notifyDuelResult(pion.getName(),false));
            return -1;
        } else {
        	Optional.ofNullable(notificationServices).ifPresent(service -> service.notify("DUEL REUSSI !"));
                Optional.ofNullable(notificationServices).ifPresent(service -> service.notifyDuelResult(pion.getName(),true));
            return 0;
        }
    }

    @Override
    public int[] spin() {
       return this.controlSlotMachine.spin();
    }
    
}