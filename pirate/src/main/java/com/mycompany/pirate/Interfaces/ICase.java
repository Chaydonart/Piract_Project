/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pirate.Interfaces;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;

/**
 *
 * @author BEN JAAFAR
 */
public interface ICase {
    public boolean estOccupee();
     
    public void ajouterPion(Pion pion);
    
    public void retirerPion(Pion pion);
    
    public boolean isSpecial();
}
