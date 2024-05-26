/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlReculer;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
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
public class TestControlReculer {
    private static final int BOARD_SIZE = 36; // Définir la taille du plateau
    
    public static void main(String[] args) {
        String cheminFichierTests = System.getProperty("user.dir") + File.separator + "tests" + File.separator + "testsControlReculer.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(cheminFichierTests));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.contains(":")) {
                    String[] parties = ligne.split(":", -1);
                    int positionInitiale = Integer.parseInt(parties[0]);
                    String[] spinValuesString = parties[1].split(",");
                    int[] spinValues = new int[3];
                    for (int i = 0; i < 3; i++) {
                        spinValues[i] = Integer.parseInt(spinValuesString[i]);
                    }
                    int positionAttendue = Integer.parseInt(parties[2]);

                    int positionObtenue = executerTestReculer(positionInitiale, spinValues);
                    boolean testResult = positionObtenue == positionAttendue;

                    if (testResult) {
                        System.out.println("Test PASS pour positionInitiale: " + positionInitiale + ", spinValues: " + Arrays.toString(spinValues) + "=" + Arrays.stream(spinValues).sum() + ", positionAttendue: " + positionAttendue + ", positionObtenue: " + positionObtenue);
                    } else {
                        System.out.println("Test FAIL pour positionInitiale: " + positionInitiale + ", spinValues: " + Arrays.toString(spinValues) + "=" + Arrays.stream(spinValues).sum() + ", positionAttendue: " + positionAttendue + ", positionObtenue: " + positionObtenue);
                    }
                } else {
                    System.out.println("Ligne ignorée car elle ne contient pas le format attendu 'positionInitiale:spinValues:positionAttendue': " + ligne);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de tests n'a pas été trouvé : " + cheminFichierTests);
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la lecture du fichier de tests : " + e.getMessage());
        }
    }

    private static int executerTestReculer(int positionInitiale, int[] spinValues) {
        // Créer une liste de pions avec un seul pion pour le test
        List<Pion> pions = new ArrayList<>();
        Pion pion = new Pion("TestPion");
        pion.setPosition(positionInitiale);
        pions.add(pion);

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

        // Création d'un faux random pour ControlSlotMachine
        Random fauxRandomSlotMachine = new Random() {
            private int callCount = 0;

            @Override
            public int nextInt(int bound) {
                int result;
                switch (callCount++) {
                    case 0: result = spinValues[0]; break;
                    case 1: result = spinValues[1] - 1; break;
                    case 2: result = spinValues[2] - 1; break;
                    default: throw new IllegalStateException("Unexpected call count: " + callCount);
                }
                return result;
            }
        };

        PionRepository pionRepository = new PionRepository(pions);
        ControlSlotMachine controlSlotMachine = new ControlSlotMachine(notificationService, fauxRandomSlotMachine);
        Plateau plateau = new Plateau(BOARD_SIZE, notificationService);
        ControlDeplacerPion controlDeplacerPion = new ControlDeplacerPion(plateau, notificationService, pionRepository);
        ControlReculer controlReculer = new ControlReculer(controlDeplacerPion, controlSlotMachine, notificationService);

        // Initialisation
        plateau.initialiser(controlDeplacerPion, new ControlSlotMachine(notificationService));

        // Exécuter la méthode à tester
        controlReculer.reculer(pion);

        // Retourner la position obtenue pour comparaison
        return pion.getPosition();
    }
}
