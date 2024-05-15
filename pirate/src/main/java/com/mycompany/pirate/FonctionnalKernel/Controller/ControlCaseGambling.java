/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.ISlotMachine;
import java.util.Random;
import com.mycompany.pirate.Interfaces.IControlCaseGambling;

/**
 *
 * @author RIBEIRO
 */
public class ControlCaseGambling implements IControlCaseGambling {
    private final ISlotMachine controleSlotMachine ;

    
    public ControlCaseGambling() {
        this.controleSlotMachine = new ControleSlotMachine();
    }

    @Override
    public void duelDeDes(Pion pion) {
        //Lancers de dés
        spin();
        int lancerPion = getSumValues();
        Random random = new Random();
        int value = random.nextInt(6);
        System.out.println("Case gambling ! Le joueur doit faire un lancer superieur a "+ value);
        System.out.println("La roulette affiche... " + lancerPion + " !");

        //perdant perd une vie
        if (lancerPion < value) {
            pion.setVie(pion.getVie() - 1);
            System.out.println(pion.getName() + " a perdu le gambling ! Une vie en moins ");
        } else {
            System.out.println("DUEL REUSSI !");
        }
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