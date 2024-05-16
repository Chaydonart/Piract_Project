package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.util.function.Supplier;

/**
 *
 * @author RIBEIRO
 */

public class Plateau {
    private int nbCases;
    private Case[] cases;
    //Random randomNumbers = new Random();

    public Plateau(int nbCases) {
        this.nbCases = nbCases;
        this.cases = new Case[nbCases];
        
        // Définition des fournisseurs de cases spéciales
        Supplier<Case> caseNormale = Case::new;
        Supplier<Case> caseSpeciale1 = CaseDegat::new; 
        Supplier<Case> caseSpeciale2 = CaseGambling::new;
        Supplier<Case> caseSpeciale3 = CaseReculer::new;
        
        for (int i = 0; i < nbCases; i++) {
            Supplier<Case> caseSupplier;
            
            //ATTENTION LES i SONT DECALES DE 1 !!! il faut faire -1 (case 10 = case 9 en vrai)
            caseSupplier = switch (i) {
                case 3, 13, 21, 29, 30 -> caseSpeciale1;
                case 7, 19, 31 -> caseSpeciale2;
                case 8, 9, 10, 11, 12 -> caseSpeciale3;
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
