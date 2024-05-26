/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Interfaces.IDialogue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.*;

/**
 *
 * @author RIBEIRO
 */
public class TestControlDeplacerPion {
    private static final int BOARD_SIZE = 36; // Définir la taille du plateau

    public static void main(String[] args) {
        String cheminFichierTests = System.getProperty("user.dir") + File.separator + "tests" + File.separator + "testsControlDeplacerPion.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(cheminFichierTests));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                if (ligne.contains(":")) {
                    String[] parties = ligne.split(":", -1);
                    int positionInitiale = Integer.parseInt(parties[0]);
                    int deplacement = Integer.parseInt(parties[1]);
                    int positionAttendue = Integer.parseInt(parties[2]);

                    int positionObtenue = executerTestDeplacerPion(positionInitiale, deplacement);
                    boolean testResult = positionObtenue == positionAttendue;

                    if (testResult) {
                        System.out.println("Test PASS pour la position initiale: " + positionInitiale + ", deplacement: " + deplacement + " - Position attendue: " + positionAttendue + ", Position obtenue: " + positionObtenue);
                    } else {
                        System.out.println("Test FAIL pour la position initiale: " + positionInitiale + ", deplacement: " + deplacement + " - Position attendue: " + positionAttendue + ", Position obtenue: " + positionObtenue);
                    }
                } else {
                    System.out.println("Ligne ignorée car elle ne contient pas le format attendu 'positionInitiale:deplacement:positionAttendue': " + ligne);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de tests n'a pas été trouvé : " + cheminFichierTests);
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la lecture du fichier de tests : " + e.getMessage());
        }
    }

    private static int executerTestDeplacerPion(int positionInitiale, int deplacement) {
        // Créer une liste de pions avec un seul pion pour le test
        List<Pion> pions = new ArrayList<>();
        Pion pion = new Pion("TestPion");
        pion.setPosition(positionInitiale);
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
        PionRepository pionRepository = new PionRepository(pions);
        Plateau plateau = new Plateau(BOARD_SIZE, notificationService);
        ControlDeplacerPion controlDeplacerPion = new ControlDeplacerPion(plateau, notificationService, pionRepository);
        ControlSlotMachine controlSlotMachine = new ControlSlotMachine(notificationService);

        // Initialisation
        plateau.initialiser(controlDeplacerPion, controlSlotMachine);

        // Exécuter la méthode à tester
        controlDeplacerPion.deplacerPion(pion, deplacement);

        // Retourner la position obtenue pour comparaison
        return pion.getPosition();
    }
}