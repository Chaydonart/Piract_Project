/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IControlCaseReculer;
import java.util.Random;

/**
 *
 * @author RIBEIRO
 */
public class ControlCaseReculer implements IControlCaseReculer{

    //Si on recule sur une case spéciale on anime pas 
    //Risque de trop désavantager un joueur avec -5 cases et perdre une vie
    public ControlCaseReculer() {
    }

    public void reculer(Pion pion) {
        //Valeur aléatoire de retour en arrière
        Random random = new Random();
        int value = random.nextInt(5);
        System.out.println("Case reculer ! Le joueur doit reculer de "+ value + " cases");
        int pos = pion.getPosition();
        
        //si plus grand -> case depart
        if (pos < value) {
            pion.setPosition(1);
            System.out.println("Le joueur se retrouve à la case 1");
        } else {
            pion.setPosition(pos - value);
            System.out.println("Le joueur se retrouve à la case " + (pos - value));
        }
    }
}
