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
        Supplier<Case> caseSpeciale1 = CaseDegat::new; // Remplacez CaseSpeciale1 par le nom de votre classe spéciale 1

        // Génération aléatoire du type de case pour chaque position du plateau
        for (int i = 0; i < nbCases; i++) {
            Supplier<Case> caseSupplier;
            int randomValue = randomNumbers.nextInt(3); // Générer une valeur aléatoire entre 0 et 2
            switch (randomValue) {
                case 0:
                    caseSupplier = caseSpeciale1;
                    break;                    
                default:
                    caseSupplier = caseNormale;
            }
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
