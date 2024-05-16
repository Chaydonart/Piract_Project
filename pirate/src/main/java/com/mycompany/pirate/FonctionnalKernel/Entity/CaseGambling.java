/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.util.ArrayList;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlCaseGambling;
import com.mycompany.pirate.Interfaces.IControlCaseGambling;

/**
 *
 * @author RIBEIRO
 */
public class CaseGambling extends Case {
    private ArrayList<Pion> occupants;
    private final IControlCaseGambling controlGamblingDuel = new ControlCaseGambling();

    public CaseGambling() {
        occupants = new ArrayList<>();
    }
    
     @Override
    public void ajouterPion(Pion pion) {
        occupants.add(pion);
        controlGamblingDuel.duelDeDes(pion);
    }
    
    @Override
    public boolean isSpecial(){
        return true;
    }
    
    @Override
    public String toString(){
        return " atterrit sur une case GAMBLING !";
    }
}
