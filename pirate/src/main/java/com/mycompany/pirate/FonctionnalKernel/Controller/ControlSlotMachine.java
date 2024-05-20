/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Interfaces.IControlSlotMachine;
import com.mycompany.pirate.Interfaces.IDialogue;

import java.util.Optional;
import java.util.Random;

/**
 *
 * @author BEN JAAFAR
 */
public class ControlSlotMachine implements IControlSlotMachine {
    private final Random random = new Random();
    private int compteurSpin = 0;
    private IDialogue dialogue;
    
    public ControlSlotMachine(IDialogue dialogue){
        this.dialogue = dialogue;
    }
    
    @Override
    public int[] spin() {
        this.compteurSpin++;
        int[] values = new int[3];
        values[0] = random.nextInt(3); // Uniquement la case 1 va de 0 a 4
        values[1] = random.nextInt(4) + 1; 
        values[2] = random.nextInt(4) + 1;
        Optional.ofNullable(dialogue).ifPresent(service -> service.notifySpin(values));
        return values;
    }
    
    public int getCompteurSpin() {
        return compteurSpin;
    }

}
