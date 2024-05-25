/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.test;

import com.mycompany.pirate.test.mutants.ControlGamblingDuelModifie;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author RIBEIRO
 */
public class TestControlGamblingDuel {
    public static void main(String[] args) {
        String cheminFichierTests = System.getProperty("user.dir") + File.separator + "tests" + File.separator + "testsControlGamblingDuel.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichierTests))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.contains(":")) {
                    String[] parties = ligne.split(":", -1);
                    int vieInitiale = Integer.parseInt(parties[0]);
                    int forcedRandomValue = Integer.parseInt(parties[1]);
                    int[] forcedSpinValues = Arrays.stream(parties[2].split(",")).mapToInt(Integer::parseInt).toArray();
                    int vieAttendue = Integer.parseInt(parties[3]);

                    // Instanciation du pion ici
                    Pion pion = new Pion("TestPion");
                    pion.setVie(vieInitiale); // Assurez-vous que la classe Pion a une méthode setVie

                    boolean testResult = executerTestGamblingDuel(pion, forcedRandomValue, forcedSpinValues, vieAttendue);

                    if (testResult) {
                        System.out.printf("Test PASS - Vie initiale : %d, Random Value : %d, Spin : %s, Res : %d - Vie attendue : %d - Resultat Vie : %d, Duel Gagne : %s\n",
                                vieInitiale, forcedRandomValue, Arrays.toString(forcedSpinValues), Arrays.stream(forcedSpinValues).sum(), vieAttendue, vieAttendue, vieAttendue > vieInitiale - 1 ? "Oui" : "Non");
                    } else {
                        System.out.printf("Test FAIL - Vie initiale : %d, Random Value : %d, Spin : %s, Res : %d - Vie attendue : %d - Resultat Vie : %d, Duel Gagne : %s\n",
                                vieInitiale, forcedRandomValue, Arrays.toString(forcedSpinValues), Arrays.stream(forcedSpinValues).sum(), vieAttendue, pion.getVie(), vieAttendue > vieInitiale - 1 ? "Oui" : "Non");
                    }
                } else {
                    System.out.println("Ligne ignorée car elle ne contient pas le format attendu 'vieInitiale:forcedRandomValue:forcedSpinValues:vieAttendue': " + ligne);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de tests n'a pas été trouvé : " + cheminFichierTests);
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la lecture du fichier de tests : " + e.getMessage());
        }
    }

    private static boolean executerTestGamblingDuel(Pion pion, int forcedRandomValue, int[] forcedSpinValues, int vieAttendue) {
        // Initialiser les dépendances
        IDialogue notificationService = new IDialogue() {
            @Override
            public void notifySpin(int[] values) {
            }

            @Override
            public void notifyEtatJeu() {
            }

            @Override
            public void notifyCaseDegat(String name, int vie) {
            }

            @Override
            public void notifyCaseRejouer(String name) {
            }

            @Override
            public void notifyCaseReculer() {
            }

            @Override
            public void notifyCaseGambling(int randomValue) {
            }

            @Override
            public void notifyDuelResult(String name, boolean win) {
            }

            @Override
            public void notifyDeplacerPion(int deplacement, String name) {
            }

            @Override
            public void notifyNouveauTour(String name) {
            }

            @Override
            public void notifyFinDeJeu(String name) {
            }
            // Implémentations des méthodes
        };

        ControlSlotMachine controlSlotMachine = new ControlSlotMachine(notificationService);
        ControlGamblingDuelModifie controlGamblingDuelModifie = new ControlGamblingDuelModifie(controlSlotMachine);
        controlGamblingDuelModifie.setForcedRandomValue(forcedRandomValue);
        controlGamblingDuelModifie.setForcedSpinValues(forcedSpinValues);

        // Exécuter la méthode à tester
        int result = controlGamblingDuelModifie.duelDeDes(pion, notificationService);

        // Vérifier les résultats
        boolean success = (pion.getVie() == vieAttendue);
        return success;
    }
}

