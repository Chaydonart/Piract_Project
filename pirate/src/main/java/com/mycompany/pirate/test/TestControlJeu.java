/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author RIBEIRO
 */
public class TestControlJeu {
    private static final int BOARD_SIZE = 36; // Taille du plateau

    public static void main(String[] args) {
        String cheminFichierTests = System.getProperty("user.dir") + File.separator + "tests" + File.separator + "testsControlJeu.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichierTests))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.contains(":")) {
                    String[] parties = ligne.split(":", -1);
                    int positionInitialePion1 = Integer.parseInt(parties[0]);
                    int positionInitialePion2 = Integer.parseInt(parties[1]);
                    int expectedPositionPion1 = Integer.parseInt(parties[2]);
                    int expectedPositionPion2 = Integer.parseInt(parties[3]);
                    boolean expectedGameOver = Boolean.parseBoolean(parties[4]);

                    boolean testResult = executerTestControlJeu(positionInitialePion1, positionInitialePion2, expectedPositionPion1, expectedPositionPion2, expectedGameOver);

                    if (testResult) {
                        System.out.println("Test PASS - Positions initiales: Pion1=" + positionInitialePion1 + ", Pion2=" + positionInitialePion2 + " - Positions attendues: Pion1=" + expectedPositionPion1 + ", Pion2=" + expectedPositionPion2 + " - Game Over attendu: " + expectedGameOver);
                    } else {
                        System.out.println("Test FAIL - Positions initiales: Pion1=" + positionInitialePion1 + ", Pion2=" + positionInitialePion2 + " - Positions attendues: Pion1=" + expectedPositionPion1 + ", Pion2=" + expectedPositionPion2 + " - Game Over attendu: " + expectedGameOver);
                    }
                } else {
                    System.out.println("Ligne ignorée car elle ne contient pas le format attendu 'positionInitialePion1:positionInitialePion2:expectedPositionPion1:expectedPositionPion2:expectedGameOver': " + ligne);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de tests n'a pas été trouvé : " + cheminFichierTests);
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la lecture du fichier de tests : " + e.getMessage());
        }
    }

    private static boolean executerTestControlJeu(int positionInitialePion1, int positionInitialePion2, int expectedPositionPion1, int expectedPositionPion2, boolean expectedGameOver) {
        // Initialiser les pions
        Pion pion1 = new Pion("Pion1");
        pion1.setPosition(positionInitialePion1);
        Pion pion2 = new Pion("Pion2");
        pion2.setPosition(positionInitialePion2);

        List<Pion> pions = Arrays.asList(pion1, pion2);
        PionRepository pionRepository = new PionRepository(pions);

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

        Plateau plateau = new Plateau(BOARD_SIZE, notificationService);
        ControlDeplacerPion controlDeplacerPion = new ControlDeplacerPion(plateau, notificationService, pionRepository);
        ControlSlotMachine controlSlotMachine = new ControlSlotMachine(notificationService);
        Jeu jeu = new Jeu(pions);

        ControlJeu controlJeu = new ControlJeu(jeu, pionRepository, notificationService, controlDeplacerPion, controlSlotMachine);

        // Exécuter la méthode à tester
        controlJeu.startGame();

        // Vérifier les résultats
        boolean positionsMatch = (pion1.getPosition() == expectedPositionPion1) && (pion2.getPosition() == expectedPositionPion2);
        boolean gameOverMatch = jeu.isGameOver() == expectedGameOver;

        return positionsMatch && gameOverMatch;
    }
}
