/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.ISlotMachine;
import java.util.Random;

/**
 *
 * @author RIBEIRO
 */
public class ControlCaseReculer {
        private final ISlotMachine controleSlotMachine ;

    //Si on recule sur une case spéciale on anime pas 
    //Risque de trop désavantager un joueur avec -5 cases et perdre une vie
    public ControlCaseReculer() {
        this.controleSlotMachine = new ControleSlotMachine();
    }

    public void reculer(Pion pion) {
        //Valeur aléatoire de retour en arrière
        Random random = new Random();
        int value = random.nextInt(5);
        System.out.println("Case reculer ! Le joueur doit reculer de "+ value + " cases");
        

    }

    @Override
    public int[] spin() {
        return controleSlotMachine.spin();
    }

    @Override
    public int getSumValues() {
        return controleSlotMachine.getSumValues();
    }
}
