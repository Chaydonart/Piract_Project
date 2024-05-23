/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.util.Optional;
import com.mycompany.pirate.Interfaces.IDialogue;

/**
 *
 * @author BEN JAAFAR
 */
public class CaseDegat extends Case {
    private IDialogue dialogue;

    public CaseDegat(IDialogue dialogue) {
        this.dialogue = dialogue;
    }
    
    @Override
    public void ajouterPion(Pion pion) {
        occupants.add(pion);
        pion.setVie(pion.getVie() - 1);
        Optional.ofNullable(dialogue).ifPresent(service -> service.notifyCaseDegat(pion.getName(), pion.getVie()));
    }
    

    
}
