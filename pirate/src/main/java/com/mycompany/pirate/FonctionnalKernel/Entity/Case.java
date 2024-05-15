package com.mycompany.pirate.FonctionnalKernel.Entity;

import com.mycompany.pirate.Interfaces.ICase;
import java.util.ArrayList;

/**
 *
 * @author BEN JAAFAR
 */

public class Case implements ICase {
    private ArrayList<Pion> occupants;

    public Case() {
        occupants = new ArrayList<>();
    }
    
    @Override
    public boolean estOccupee() {
        return !occupants.isEmpty();
    }
     
    @Override
    public void ajouterPion(Pion pion) {
        occupants.add(pion);
    }
    
    @Override
    public void retirerPion(Pion pion) {
        occupants.remove(pion);
    }
    
    @Override
    public boolean isSpecial(){
        return false;
    }
}
