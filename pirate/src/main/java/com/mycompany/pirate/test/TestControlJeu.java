/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Interfaces.IDialogue;
import com.mycompany.pirate.test.mutants.ControlJeuModifie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author RIBEIRO
 */
public class TestControlJeu {
    private static final int BOARD_SIZE = 36; // Taille du plateau
    private static Pion pion;

    public static void main(String[] args) {
        String cheminFichierTests = System.getProperty("user.dir") + File.separator + "tests" + File.separator + "testsControlJeu.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichierTests))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.contains(":")) {
                    String[] parties = ligne.split(":", -1);

                    int vieInitiale = Integer.parseInt(parties[0]);
                    int positionInitiale = Integer.parseInt(parties[1]);
                    int[] forcedSpinValues = Arrays.stream(parties[2].split(","))
                                                   .mapToInt(Integer::parseInt)
                                                   .toArray();
                    int expectedVie = Integer.parseInt(parties[3]);
                    int expectedPosition = Integer.parseInt(parties[4]);
                    boolean expectedGameOver = Boolean.parseBoolean(parties[5]);

                    boolean testResult = executerTestControlJeu(vieInitiale, positionInitiale, expectedVie, expectedPosition, expectedGameOver, forcedSpinValues);

                    int deplacement = Arrays.stream(forcedSpinValues).sum();
                    boolean gameOver = pion.getVie() <= 0 || pion.getPosition() >= BOARD_SIZE;

                    String result = testResult ? "PASS" : "FAIL";

                    System.out.printf(
                        "%s - Vie initiale: %d, Position initiale: %d, Deplacement: %d, Vie attendue: %d, Position attendue: %d, Fin de partie attendue: %s, Vie obtenue: %d, Position obtenue: %d, Fin de partie obtenue: %s\n",
                        result, vieInitiale, positionInitiale, deplacement, expectedVie, expectedPosition, expectedGameOver,
                        pion.getVie(), pion.getPosition(), gameOver
                    );
                } else {
                    System.out.println("Ligne ignorée car elle ne contient pas le format attendu 'vieInitiale:positionInitiale:3 valeurs du tableau:expectedVie:expectedPosition:expectedGameOver': " + ligne);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de tests n'a pas été trouvé : " + cheminFichierTests);
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la lecture du fichier de tests : " + e.getMessage());
        }
    }

    private static boolean executerTestControlJeu(int vieInitiale, int positionInitiale, int expectedVie, int expectedPosition, boolean expectedGameOver, int[] forcedSpinValues) {
        pion = new Pion("Pion1");
        pion.setPosition(positionInitiale);
        pion.setVie(vieInitiale);

        List<Pion> pions = Arrays.asList(pion);
        PionRepository pionRepository = new PionRepository(pions);

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
        ControlJeuModifie controlJeu = new ControlJeuModifie(jeu, pionRepository, notificationService, controlDeplacerPion, controlSlotMachine);

        controlJeu.setForcedSpinValues(forcedSpinValues);
        plateau.initialiser(controlDeplacerPion, controlSlotMachine);

        controlJeu.startGame();

        boolean positionsMatch = (pion.getPosition() == expectedPosition);
        boolean vieMatch = (pion.getVie() == expectedVie);
        boolean gameOverMatch = (jeu.isGameOver() == expectedGameOver);

        return positionsMatch && vieMatch && gameOverMatch;
    }
}
