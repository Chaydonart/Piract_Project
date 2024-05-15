/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author RIBEIRO
 */
public class CaseReculer extends Case {
    private ArrayList<Pion> occupants;

    public CaseReculer() {
        occupants = new ArrayList<>();
    }
    
     @Override
    public void ajouterPion(Pion pion) {
        Random random = new Random();
        int value = random.nextInt(6);
        int pos = pion.getPosition();
        occupants.add(pion);
        
        
    }
    
    @Override
    public boolean isSpecial(){
        return true;
    }
    
    @Override
    public String toString(){
        return " perd un point de vie du à une case DEGAT !";
    }
}
