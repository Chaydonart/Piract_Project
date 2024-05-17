/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlGamblingDuel;
import com.mycompany.pirate.Services.ServiceGameblingDuel;
import com.mycompany.pirate.Interfaces.IServiceGamblingDuel;
import com.mycompany.pirate.Interfaces.INotificationService;

/**
 *
 * @author RIBEIRO
 */
public class CaseGambling extends Case {
    private INotificationService notificationService;
    
    private IServiceGamblingDuel gdDuel = new ServiceGameblingDuel();
    private ControlGamblingDuel controlGamblingDuel = new ControlGamblingDuel(gdDuel);

    public CaseGambling(INotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
     @Override
    public void ajouterPion(Pion pion) {
        occupants.add(pion);
        controlGamblingDuel.duelDeDes(pion,notificationService);
    }
   

}
