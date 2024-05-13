package com.mycompany.pirate.FonctionnalKernel.Entity;

import com.mycompany.pirate.Boundary.interfaces.ICase;
import java.util.ArrayList;

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
    
    public boolean isSpecial(){
        return false;
    }
}
