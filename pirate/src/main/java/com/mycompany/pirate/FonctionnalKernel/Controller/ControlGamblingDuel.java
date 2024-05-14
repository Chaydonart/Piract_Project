/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;

/**
 *
 * @author RIBEIRO
 */
public class ControlGamblingDuel {
    private final ControleSlotMachine controleSlotMachine;
    private final Pion pion1;
    private final Pion pion2;
    
    public ControlGamblingDuel(ControlJeu controlJeu) {
        this.pion1 = controlJeu.getJoueur1();
        this.pion2 = controlJeu.getJoueur2();
        this.controleSlotMachine = new ControleSlotMachine();
    }

    public void duelDeDes() {
        //Lancers de dés
        int lancerPion1 = controleSlotMachine.spin()[0];
        int lancerPion2 = controleSlotMachine.spin()[0];

        //perdant perd une vie
        if (lancerPion1 < lancerPion2) {
            pion1.setVie(pion1.getVie() - 1);
        } else if (lancerPion2 < lancerPion1) {
            pion2.setVie(pion2.getVie() - 1);
        }
        // En cas d'égalité, aucun pion ne perd de vie
    }
}