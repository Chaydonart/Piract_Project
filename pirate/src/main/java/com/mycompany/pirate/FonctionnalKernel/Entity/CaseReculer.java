/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlReculer;
import com.mycompany.pirate.Interfaces.IControlReculer;
import java.util.ArrayList;

/**
 *
 * @author RIBEIRO
 */
public class CaseReculer extends Case {
    private ArrayList<Pion> occupants;
    private final IControlReculer controlReculer = new ControlReculer();
    
    public CaseReculer() {
        occupants = new ArrayList<>();
    }
    
     @Override
    public void ajouterPion(Pion pion) {
        occupants.add(pion);
        controlReculer.reculer(pion);   
    }
    
    @Override
    public boolean isSpecial(){
        return true;
    }
    
    @Override
    public String toString(){
        return " atterrit sur une case RECULER !";
    }
}
