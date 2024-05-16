/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Services;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IServiceGamblingDuel;
import com.mycompany.pirate.Interfaces.NotificationService;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author BEN JAAFAR
 */
public class ServiceGameblingDuel implements IServiceGamblingDuel {
    private final ControlSlotMachine controleSlotMachine;
    private final SlotMachineService smService;
    private Random random= new Random();
    
    public ServiceGameblingDuel() {
        this.smService = new SlotMachineService();
        this.controleSlotMachine = new ControlSlotMachine(smService);
    }
    
    @Override
    public void duelDeDes(Pion pion, NotificationService notificationServices) {
        //Lancers de dés
        int[] valeurs = controleSlotMachine.spin();
        int res = Arrays.stream(valeurs).sum();
        int randomValue = this.random.nextInt(9);
        
        notificationServices.notify("Duel gambling ! Le joueur doit faire une valeur superieure a "+ randomValue);
        notificationServices.notify("La roulette affiche... " + res + " !");
  
        
        //perdant perd une vie
        if (res < randomValue) {
            pion.setVie(pion.getVie() - 1);
            notificationServices.notify(pion.getName() + " a perdu le gambling ! Vie restante : " + pion.getVie());
        } else {
            notificationServices.notify("DUEL REUSSI !");
        }
    }

    
}
