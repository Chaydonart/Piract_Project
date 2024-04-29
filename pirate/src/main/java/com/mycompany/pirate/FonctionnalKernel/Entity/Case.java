package com.mycompany.pirate.FonctionnalKernel.Entity;

public class Case {
    private Position position;
    private boolean estOccupee;

    public Case(int ligne, int colonne) {
        position = new Position(ligne, colonne);
        estOccupee = false;
    }
}
