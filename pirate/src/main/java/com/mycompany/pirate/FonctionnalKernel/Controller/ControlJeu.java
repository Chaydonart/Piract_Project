package com.mycompany.pirate.FonctionnalKernel.Controller;

import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;

public class ControlJeu {
      private boolean jeuTermine = false;
      private Pion joueur1 = new Pion("Joueur 1");
      private Pion joueur2 = new Pion("Joueur 2");
      private Pion[] joueurs = { joueur1, joueur2 };
      private Plateau plateau = new Plateau(36);
      private ControlDeplacerPion controlDeplacerPion = new ControlDeplacerPion();
      private ControleSlotMachine controleSlotMachine = new ControleSlotMachine();

      public void initialiserJeu() {
            plateau.poserPion(joueur1);
            plateau.poserPion(joueur2);
      }

      public void jouerTour(Pion joueurCourant) {
            controleSlotMachine.spin();
            int deplacement = controleSlotMachine.getSumValues();
            System.out.println(joueurCourant.getName() + " avance de " + deplacement + " cases");
            int nouvellePosition = controlDeplacerPion.deplacerPion(joueurCourant, plateau, deplacement);
            System.out.println(joueurCourant.getName() + " est à la case " + nouvellePosition);
            if(joueurCourant.getVie() <= 0){
                System.out.println(joueurCourant.getName() + " n'a plus de vie");
                jeuTermine = true;
            }
            if (nouvellePosition == plateau.getNbCases()) {
                  jeuTermine = true;
                  System.out.println(joueurCourant.getName() + " a gagné");
            }
      }

      public void jouer() {
            while (!jeuTermine) {
                  System.out.println("Nouveau tour");
                  for (int i = 0; i < joueurs.length; i++) {
                        Pion joueurCourant = joueurs[i];
                        jouerTour(joueurCourant);
                        if (jeuTermine) {
                              break;
                        }
                  }
                  System.out.println("Fin du tour\n");
            }
      }

      public static void main(String[] args) {
            ControlJeu controlJeu = new ControlJeu();
            controlJeu.initialiserJeu();
            controlJeu.jouer();
      }

}