package com.mycompany.pirate.FonctionnalKernel.Entity;

public class Position {
    int ligne;
    int colonne;

    public Position(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }
}
