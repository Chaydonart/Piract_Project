package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.util.ArrayList;

/**
 *
 * @author BEN JAAFAR
 */

public class Case {
    private ArrayList<Pion> occupants;

    public Case() {
        occupants = new ArrayList<>();
    }
    
    public boolean estOccupee() {
        return !occupants.isEmpty();
    }
     
    public void ajouterPion(Pion pion) {
        occupants.add(pion);
    }
    
    public void retirerPion(Pion pion) {
        occupants.remove(pion);
    }
    
    public boolean isSpecial(){
        return false;
    }
}
