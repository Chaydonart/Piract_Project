/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IControlGamblingDuel;
import com.mycompany.pirate.Interfaces.ISlotMachine;
import com.mycompany.pirate.Interfaces.NotificationService;
import java.util.Random;

/**
 *
 * @author RIBEIRO
 */
public class ControlGamblingDuel  {
    
    private ServiceGameblingDuel gdService;

    public ControlGamblingDuel(ServiceGameblingDuel gdService) {
        this.gdService = gdService;
    }
    
    public void duelDeDes(Pion pion, NotificationService nots){
        this.gdService.duelDeDes(pion,nots);
    }
    
}