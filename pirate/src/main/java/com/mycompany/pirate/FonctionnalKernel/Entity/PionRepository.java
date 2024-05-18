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
 * Le PionRepository agit comme une couche d'abstraction entre les entités (les pions)
 * et les contrôleurs qui ont besoin d'accéder à ces entités. Il fournit des méthodes 
 * pour manipuler la collection de pions de manière cohérente.
 * Cela permet notammenet d'avoir le joueur courant.
 * 
 */

public class PionRepository {
    private List<Pion> pions;
    private int pionActuelIndex;

    public PionRepository(List<Pion> pions) {
        this.pions = pions;
        this.pionActuelIndex = 0;
    }

    public Pion getPionActuel() {
        return pions.get(pionActuelIndex);
    }

    public void save(Pion pion) {
        // Sauvegarder l'état du pion si nécessaire (pas nécessaire dans ce cas)
    }

    public void nextPion() {
        pionActuelIndex = (pionActuelIndex + 1) % pions.size();
    }

    public List<Pion> getPions() {
        return pions;
    }
}
