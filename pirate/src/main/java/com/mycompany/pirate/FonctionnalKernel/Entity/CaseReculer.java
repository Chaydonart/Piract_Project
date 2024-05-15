/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.util.ArrayList;

/**
 *
 * @author RIBEIRO
 */
public class CaseReculer extends Case {
    private ArrayList<Pion> occupants;
    private int position;

    public CaseReculer(int position) {
        occupants = new ArrayList<>();
        this.position = position;
    }
    
     @Override
    public void ajouterPion(Pion pion) {
        occupants.add(pion);
        pion.setVie(pion.getVie() - 1);
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
