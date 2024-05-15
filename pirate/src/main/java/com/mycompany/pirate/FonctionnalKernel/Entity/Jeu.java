/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.util.List;

/**
 *
 * @author BEN JAAFAR
 * 
 * # DESCRIPTION
 * La classe Jeu peut suivre l'état global du jeu, comme le joueur actuel 
 * en train de jouer, le tour actuel, le nombre de tours restants, etc. 
 * En conservant cette information dans la classe Jeu, nous pouvons faciliter 
 * la coordination des différentes actions et événements qui se produisent 
 * pendant le jeu.
 * 
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
        return pion.getPosition() >= 36; 
    }
    
}
