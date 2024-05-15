/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

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
    private final ControleSlotMachine controleSlotMachine;
    private final SlotMachineService smService;
    
    public ServiceGameblingDuel() {
        this.smService = new SlotMachineService();
        this.controleSlotMachine = new ControleSlotMachine(smService);
    }
    
    @Override
    public void duelDeDes(Pion pion, NotificationService notificationServices) {
        //Lancers de dés
        int[] valeurs = controleSlotMachine.spin();
        int res = Arrays.stream(valeurs).sum();
        Random random = new Random();
        int randomValue = random.nextInt(9);
        
        notificationServices.notify("Duel gambling ! Le joueur doit faire une valeur superieur a "+ randomValue);
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
