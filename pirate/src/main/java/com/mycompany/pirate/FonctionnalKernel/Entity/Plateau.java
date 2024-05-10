package com.mycompany.pirate.FonctionnalKernel.Entity;

public class Plateau {
    private int nbCases;
    private Case[] cases;

    public Plateau(int nbCases) {
        this.nbCases = nbCases;
        this.cases = new Case[nbCases];
        for (int i = 0; i < nbCases; i++) {
            this.cases[i] = new Case();
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
}
