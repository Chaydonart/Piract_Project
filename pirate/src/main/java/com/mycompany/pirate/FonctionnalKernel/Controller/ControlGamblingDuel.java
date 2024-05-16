/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IServiceGamblingDuel;
import com.mycompany.pirate.Interfaces.NotificationService;
import java.util.Random;
import com.mycompany.pirate.Interfaces.IServiceSlotMachine;

/**
 *
 * @author RIBEIRO
 */
public class ControlGamblingDuel implements IServiceGamblingDuel   {
    
    private IServiceGamblingDuel gdService;

    public ControlGamblingDuel(IServiceGamblingDuel gdService) {
        this.gdService = gdService;
    }
    
    @Override
    public void duelDeDes(Pion pion, NotificationService nots){
        this.gdService.duelDeDes(pion,nots);
    }
    
}