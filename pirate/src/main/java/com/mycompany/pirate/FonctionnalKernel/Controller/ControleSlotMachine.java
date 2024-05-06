/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;
import com.mycompany.pirate.Boundary.interfaces.ISlotMachine;
import java.util.Random;

/**
 *
 * @author BEN JAAFAR
 */
public class ControleSlotMachine implements ISlotMachine {
    private final Random random = new Random();

    @Override
    public int[] spin() {
        int[] values = new int[3];
        values[0] = random.nextInt(5); // Valeurs entre 0 et 4 pour la première case
        values[1] = random.nextInt(4) + 1; // Valeurs entre 1 et 4 pour la deuxième case
        values[2] = random.nextInt(4) + 1; // Valeurs entre 1 et 4 pour la troisième case
        return values;
    }
}
