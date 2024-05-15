/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TempBoundary;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlJeu;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControleSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Jeu;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Interfaces.IBoundary;
import com.mycompany.pirate.Interfaces.ISlotMachine;
import java.util.Arrays;

/**
 *
 * @author BEN JAAFAR
 */
public class Boundary {
    private ControleSlotMachine controlSlotMachine;
    private ControlDeplacerPion controlDeplacePion;
    private ControlJeu gameLoopController;
    private PionRepository pionRepository;

    public Boundary(ControleSlotMachine controlSlotMachine, ControlDeplacerPion controlDeplacePion, ControlJeu gameLoopController, PionRepository pionRepository) {
        this.controlSlotMachine = controlSlotMachine;
        this.controlDeplacePion = controlDeplacePion;
        this.gameLoopController = gameLoopController;
        this.pionRepository = pionRepository;
    }

    public void start() {
        afficherMessage("Le jeu commence !");
        gameLoopController.startGame();
    }

    public void lancerDe() {
        int[] values = controlSlotMachine.spinMachine();
        afficherMessage("La machine affiche = " + values[0] + " " +  values[1] + " " + values[2]);
        int resultat = Arrays.stream(values).sum();
        afficherMessage("Résultat de la machine " + resultat);
        deplacerPion(resultat);
    }

    public void deplacerPion(int resultatDe) {
        controlDeplacePion.deplacerPion(resultatDe);
        afficherEtatJeu();
    }

    public void afficherEtatJeu() {
        for (Pion pion : pionRepository.getPions()) {
            afficherMessage("Pion " + pion.getName() + " est sur la case " + pion.getPosition());
        }
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
    
}
