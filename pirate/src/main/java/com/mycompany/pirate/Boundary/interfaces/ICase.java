/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pirate.Boundary.interfaces;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import java.util.ArrayList;

/**
 *
 * @author BEN JAAFAR
 */
public interface ICase {
     boolean estOccupee();
     void ajouterPion(Pion pion);
     void retirerPion(Pion pion);

}
