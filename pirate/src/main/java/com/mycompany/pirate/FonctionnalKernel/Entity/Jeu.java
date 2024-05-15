/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.util.List;

/**
 *
 * @author BEN JAAFAR
 */
public class Jeu {
    private List<Pion> pions;
    private boolean gameOver;

    public Jeu(List<Pion> pions) {
        this.pions = pions;
        this.gameOver = false;
    }

    public List<Pion> getPions() {
        return pions;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean checkVictory(Pion pion) {
        // Logique de vérification de la victoire
        return pion.getPosition() >= 36; // Example condition
    }
    
}
