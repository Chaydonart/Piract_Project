/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.util.ArrayList;

/**
 *
 * @author BEN JAAFAR
 */
public class CaseDegat extends Case {
     private ArrayList<Pion> occupants;

    public CaseDegat() {
        occupants = new ArrayList<>();
    }
    
     @Override
    public void ajouterPion(Pion pion) {
        pion.setVie(pion.getVie() - 1);
        occupants.add(pion);
    }
    
    @Override
    public boolean isSpecial(){
        return true;
    }
    
    public String toString(){
        return " perd un point de vie du à une case DEGAT !";
    }
}
