package com.mycompany.pirate.utils;

public class Plateau {
    // nbLignes et nbColonnes sont les dimensions du plateau
    private int nbLignes;
    private int nbColonnes;
    private Case[][] cases;

    public Plateau(int nbLignes, int nbColonnes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        cases = new Case[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                cases[i][j] = new Case(i, j);
            }
        }
    }
}
