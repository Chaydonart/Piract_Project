/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.util.Optional;
import com.mycompany.pirate.Interfaces.INotificationService;

/**
 *
 * @author BEN JAAFAR
 */
public class CaseDegat extends Case {
    private INotificationService notificationService;

    public CaseDegat(INotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    @Override
    public void ajouterPion(Pion pion) {
        occupants.add(pion);
        pion.setVie(pion.getVie() - 1);
        Optional.ofNullable(notificationService).ifPresent(service -> service.notifyCaseDegat(pion.getName(), pion.getVie()));
    }
    
    @Override
    public boolean isSpecial(){
        return true;
    }
    
}
