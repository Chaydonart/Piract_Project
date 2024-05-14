package com.mycompany.pirate.FonctionnalKernel.Entity;

import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.Random;
import java.util.function.Supplier;

public class Plateau {
    private int nbCases;
    private Case[] cases;
    Random randomNumbers = new Random();

    public Plateau(int nbCases) {
        this.nbCases = nbCases;
        this.cases = new Case[nbCases];
        
        // Définition des fournisseurs de cases spéciales
        Supplier<Case> caseNormale = Case::new;
        Supplier<Case> caseSpeciale1 = CaseDegat::new; 

        
        for (int i = 0; i < nbCases; i++) {
            Supplier<Case> caseSupplier;
            
            //ATTENTION LES i SONT DECALES DE 1 !!! il faut faire -1 (case 10 = case 9 en vrai)
            caseSupplier = switch (i) {
                case 3, 13, 21, 29, 30 -> caseSpeciale1;
                default -> caseNormale;
            };
   
            this.cases[i] = caseSupplier.get();
        }
    }

    public void poserPion(Pion pion) {
        int position = pion.getPosition() - 1;
        cases[position].ajouterPion(pion);
    }

    public void retirerPion(Pion pion) {
        int position = pion.getPosition() - 1;
        cases[position].retirerPion(pion);
    }

    public int getNbCases() {
        return nbCases;
    }
    
    public Case getCase(Pion pion){
        int position = pion.getPosition() - 1;
        return cases[position];
    }
}
