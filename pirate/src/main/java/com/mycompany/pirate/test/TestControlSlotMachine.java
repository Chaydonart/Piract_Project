/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author RIBEIRO
 */
public class TestControlSlotMachine {
    public static void main(String[] args) {
        String cheminFichierTests = System.getProperty("user.dir") + File.separator + "tests" + File.separator + "testsControlSlotMachine.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(cheminFichierTests));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.contains(":")) {
                    String[] parties = ligne.split(":", -1);
                    int[] spinValuesInitiales = Arrays.stream(parties[0].split(",")).mapToInt(Integer::parseInt).toArray();
                    int[] spinValuesExpected = Arrays.stream(parties[1].split(",")).mapToInt(Integer::parseInt).toArray();

                    int[] spinValuesObtained = executerTestSpin(spinValuesInitiales);
                    boolean testResult = Arrays.equals(spinValuesObtained, spinValuesExpected);

                    if (testResult) {
                        System.out.println("Test PASS pour valeurs initiales: " + Arrays.toString(spinValuesInitiales) + ", valeurs obtenues: " + Arrays.toString(spinValuesObtained) + ", valeurs attendues: " + Arrays.toString(spinValuesExpected));
                    } else {
                        System.out.println("Test FAIL pour valeurs initiales: " + Arrays.toString(spinValuesInitiales) + ", valeurs obtenues: " + Arrays.toString(spinValuesObtained) + ", valeurs attendues: " + Arrays.toString(spinValuesExpected));
                    }
                } else {
                    System.out.println("Ligne ignorée car elle ne contient pas le format attendu 'spinValuesInitiales:spinValuesExpected': " + ligne);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de tests n'a pas été trouvé : " + cheminFichierTests);
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la lecture du fichier de tests : " + e.getMessage());
        }
    }

    private static int[] executerTestSpin(int[] spinValuesInitiales) {
        // Initialiser les dépendances
        IDialogue notificationService = new IDialogue() {
            @Override
            public void notifySpin(int[] values) {}
            @Override
            public void notifyEtatJeu() {}
            @Override
            public void notifyCaseDegat(String name, int vie) {}
            @Override
            public void notifyCaseRejouer(String name) {}
            @Override
            public void notifyCaseReculer() {}
            @Override
            public void notifyCaseGambling(int randomValue) {}
            @Override
            public void notifyDuelResult(String name, boolean win) {}
            @Override
            public void notifyDeplacerPion(int deplacement, String name) {}
            @Override
            public void notifyNouveauTour(String name) {}
            @Override
            public void notifyFinDeJeu(String name) {}
        };

        Random fauxRandom = new Random() {
            private int callCount = 0;
            @Override
            public int nextInt(int bound) {
                int result;
                if (callCount == 0) {
                    result = spinValuesInitiales[callCount];
                } else {
                    result = spinValuesInitiales[callCount] - 1;
                }
                callCount++;
                return result;
            }
        };

        ControlSlotMachine controlSlotMachine = new ControlSlotMachine(notificationService, fauxRandom);

        return controlSlotMachine.spin();
    }
}