/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import com.mycompany.pirate.Interfaces.NotificationService;
import java.util.ArrayList;

/**
 *
 * @author BEN JAAFAR
 */
public class CaseDegat extends Case {
    private ArrayList<Pion> occupants;
    private NotificationService notificationService;

    public CaseDegat(NotificationService notificationService) {
        occupants = new ArrayList<>();
        this.notificationService = notificationService;
    }
    
     @Override
    public void ajouterPion(Pion pion) {
        occupants.add(pion);
        pion.setVie(pion.getVie() - 1);
        if (notificationService != null) {
            notificationService.notify("Le pion " + pion.getName() + " a pris des dégâts ! Vie restante : " + pion.getVie());
        }
    }
    
    @Override
    public boolean isSpecial(){
        return true;
    }
    
}
