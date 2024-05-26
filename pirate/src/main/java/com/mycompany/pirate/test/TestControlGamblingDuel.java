/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlGamblingDuel;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author RIBEIRO
 */
public class TestControlGamblingDuel {
    public static void main(String[] args) {
        String cheminFichierTests = System.getProperty("user.dir") + File.separator + "tests" + File.separator + "testsControlGamblingDuel.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(cheminFichierTests));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.contains(":")) {
                    String[] parties = ligne.split(":", -1);
                    int randomValue = Integer.parseInt(parties[0]);
                    String[] spinValuesString = parties[1].split(",");
                    int[] spinValues = new int[3];
                    for (int i = 0; i < 3; i++) {
                        spinValues[i] = Integer.parseInt(spinValuesString[i]);
                    }
                    int vieInitiale = Integer.parseInt(parties[2]);
                    int vieAttendue = Integer.parseInt(parties[3]);

                    int vieObtenue = executerTestDuelDeDes(randomValue, spinValues, vieInitiale);
                    boolean testResult = vieObtenue == vieAttendue;

                    if (testResult) {
                        System.out.println("Test PASS pour randomValue: " + randomValue + ", spinValues: " + Arrays.toString(spinValues) + "=" + Arrays.stream(spinValues).sum() + ", vieInitiale: " + vieInitiale + " - Vie attendue: " + vieAttendue + ", Vie obtenue: " + vieObtenue);
                    } else {
                        System.out.println("Test FAIL pour randomValue: " + randomValue + ", spinValues: " + Arrays.toString(spinValues) + "=" + Arrays.stream(spinValues).sum() + ", vieInitiale: " + vieInitiale + " - Vie attendue: " + vieAttendue + ", Vie obtenue: " + vieObtenue);
                    }
                } else {
                    System.out.println("Ligne ignorée car elle ne contient pas le format attendu 'randomValue:spinValues:vieInitiale:vieAttendue': " + ligne);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de tests n'a pas été trouvé : " + cheminFichierTests);
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la lecture du fichier de tests : " + e.getMessage());
        }
    }

    private static int executerTestDuelDeDes(int randomValue, int[] spinValues, int vieInitiale) {
        // Créer une liste de pions avec un seul pion pour le test
        List<Pion> pions = new ArrayList<>();
        Pion pion = new Pion("TestPion");
        pion.setVie(vieInitiale);
        pions.add(pion);

        // Initialiser les dépendances
        IDialogue notificationService = new IDialogue() {
            // Implémentation fictive pour les tests
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

        // Création d'un faux random pour ControlGamblingDuel
        Random fauxRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return randomValue - 2; // Ajuster pour que le randomValue soit retourné correctement
            }
        };

        // Création d'un faux random pour ControlSlotMachine
        Random fauxRandomSlotMachine = new Random() {
            private int callCount = 0;

            @Override
            public int nextInt(int bound) {
                int result;
                switch (callCount++) {
                    case 0: result = spinValues[0]; break;
                    case 1: result = spinValues[1]; break;
                    case 2: result = spinValues[2]; break;
                    default: throw new IllegalStateException("Unexpected call count: " + callCount);
                }
                return result;
            }
        };

        ControlSlotMachine controlSlotMachine = new ControlSlotMachine(notificationService, fauxRandomSlotMachine);
        ControlGamblingDuel controlGamblingDuel = new ControlGamblingDuel(controlSlotMachine, fauxRandom);

        // Exécuter la méthode à tester
        controlGamblingDuel.duelDeDes(pion, notificationService);

        // Retourner la vie obtenue pour comparaison
        return pion.getVie();
    }
}