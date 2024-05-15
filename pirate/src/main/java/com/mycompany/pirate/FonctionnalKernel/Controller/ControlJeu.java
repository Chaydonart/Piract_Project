package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.Interfaces.IControlJeu;
import com.mycompany.pirate.Interfaces.IDeplacerPion;
import com.mycompany.pirate.Interfaces.ISlotMachine;

import java.util.Scanner;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;

public class ControlJeu implements IControlJeu {
      private boolean jeuTermine = false;
      private Pion joueur1 = new Pion("Joueur 1");
      private Pion joueur2 = new Pion("Joueur 2");
      private Pion[] joueurs = { joueur1, joueur2 };
      private Plateau plateau = new Plateau(36);
      private Scanner scanner = new Scanner(System.in);

      // appelle des controlleurs
      private IDeplacerPion controlDeplacerPion = new ControlDeplacerPion();
      private ISlotMachine controleSlotMachine = new ControleSlotMachine();

      public void initialiserJeu() {
            plateau.poserPion(joueur1);
            plateau.poserPion(joueur2);
      }

      public void attendreTournerSlotMachine() {
            System.out.println("Appuyez sur entrée pour tourner la slot machine");
            scanner.nextLine();
            int[] res = controleSlotMachine.spin();
            System.out.println("Résultat du spin : " + res[0] + " " + res[1] + " " + res[2]);
      }

      public void jouerTour(Pion joueurCourant) {
            attendreTournerSlotMachine();
            int deplacement = controleSlotMachine.getSumValues();
            System.out.println(joueurCourant.getName() + " avance de " + deplacement + " cases et atterrit a la case "
                        + (joueurCourant.getPosition() + deplacement));
            int nouvellePosition = controlDeplacerPion.deplacerPion(joueurCourant, plateau, deplacement);
            System.out.println(joueurCourant.getName() + " est a la case " + nouvellePosition);
            if (joueurCourant.getVie() <= 0) {
                  System.out.println(joueurCourant.getName() + " n'a plus de vie");
                  jeuTermine = true;
            }
            if (nouvellePosition == plateau.getNbCases()) {
                  jeuTermine = true;
                  System.out.println(joueurCourant.getName() + " a gagne");
            }
      }

      public void jouer() {
            while (!jeuTermine) {
                  System.out.println("Nouveau tour");
                  for (int i = 0; i < joueurs.length; i++) {
                        Pion joueurCourant = joueurs[i];
                        jouerTour(joueurCourant);
                        if (jeuTermine) {
                              System.out.println("FIN DE PARTIE");
                              break;
                        } else {
                              System.out.println("Fin du tour\n");
                        }
                  }

            }
      }

      @Override
      public int deplacerPion(Pion pion, Plateau plateau, int deplacement) {
            return controlDeplacerPion.deplacerPion(pion, plateau, deplacement);
      }

      @Override
      public int[] spin() {
            return controleSlotMachine.spin();
      }

      @Override
      public int getSumValues() {
            return controleSlotMachine.getSumValues();
      }

      public static void main(String[] args) {
            ControlJeu controlJeu = new ControlJeu();
            controlJeu.initialiserJeu();
            controlJeu.jouer();
      }

      public Pion getJoueur1() {
            return this.joueur1;
      }

      public Pion getJoueur2() {
            return this.joueur2;
      }
}