/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Services.ServiceSlotMachine;
import com.mycompany.pirate.Interfaces.IServiceSlotMachine;

/**
 *
 * @author BEN JAAFAR
 */
public class ControlSlotMachine implements IServiceSlotMachine {
    private IServiceSlotMachine smService;
    private int compteurSpin = 0;

    public ControlSlotMachine(ServiceSlotMachine smService) {
        this.smService = smService;
    }

    public int[] spin() {
        this.compteurSpin++;
        return smService.spin();
    }
    public int getCompteurSpin() {
        return compteurSpin;
    }

}
