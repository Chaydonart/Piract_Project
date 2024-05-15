/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Services.SlotMachineService;
import com.mycompany.pirate.Interfaces.IServiceSlotMachine;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author BEN JAAFAR
 */
public class ControleSlotMachine implements IServiceSlotMachine {
    private IServiceSlotMachine smService;

    public ControleSlotMachine(SlotMachineService smService) {
        this.smService = smService;
    }

    public int[] spin() {
        return smService.spin();
    }

}
