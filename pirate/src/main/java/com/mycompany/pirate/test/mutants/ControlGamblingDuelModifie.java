/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.test.mutants;

import com.mycompany.pirate.FonctionnalKernel.Controller.*;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IControlGamblingDuel;
import com.mycompany.pirate.Interfaces.IControlSlotMachine;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

/**
 *
 * @author RIBEIRO
 */
public class ControlGamblingDuelModifie extends ControlGamblingDuel {
    private int forcedRandomValue;
    private int[] forcedSpinValues;

    public ControlGamblingDuelModifie(ControlSlotMachine controlSlotMachine) {
        super(controlSlotMachine);
    }

    public void setForcedRandomValue(int value) {
        this.forcedRandomValue = value;
    }

    public void setForcedSpinValues(int[] values) {
        this.forcedSpinValues = values;
    }

    @Override
    public int duelDeDes(Pion pion, IDialogue notificationServices) {
        Optional.ofNullable(notificationServices).ifPresent(service -> service.notifyCaseGambling(forcedRandomValue));
        
        int res = Arrays.stream(forcedSpinValues).sum();
  
        if (res < forcedRandomValue) {
            pion.setVie(pion.getVie() - 1);
            Optional.ofNullable(notificationServices).ifPresent(service -> service.notifyDuelResult(pion.getName(),false));
            return -1;
        } else {
            Optional.ofNullable(notificationServices).ifPresent(service -> service.notifyDuelResult(pion.getName(),true));
            return 0;
        }
    }

    @Override
    public int[] spin() {
        return forcedSpinValues;
    }
}
