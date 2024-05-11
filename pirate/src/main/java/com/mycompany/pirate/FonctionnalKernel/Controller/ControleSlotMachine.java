/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Boundary.interfaces.ISlotMachine;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author BEN JAAFAR
 */
public class ControleSlotMachine implements ISlotMachine {
    private final Random random = new Random();
    private int[] values = new int[3];

    @Override
    public int[] spin() {
        values = IntStream.generate(() -> random.nextInt(5)) // Valeurs entre 0 et 4
                .limit(3)
                .map(val -> val < 2 ? val : val + 1) // Assure que les deuxième et troisième cases ont des valeurs entre
                                                     // 1 et 4
                .toArray();
        return values;
    }

    public int getSumValues() {
        return Arrays.stream(values).sum();
    }

}
