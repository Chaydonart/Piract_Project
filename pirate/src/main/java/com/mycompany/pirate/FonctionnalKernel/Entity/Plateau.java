package com.mycompany.pirate.FonctionnalKernel.Entity;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import java.util.function.Supplier;
import com.mycompany.pirate.Interfaces.INotificationService;

/**
 *
 * @author RIBEIRO
 */

public class Plateau {
    private final int nbCases;
    private Case[] cases;
    private INotificationService notificationService;

    public Plateau(int nbCases, INotificationService notificationService) {
        this.nbCases = nbCases;
        this.cases = new Case[nbCases];
        this.notificationService = notificationService;
    }

    public void poserPion(Pion pion) {
        int position = pion.getPosition() - 1;
        cases[position].ajouterPion(pion);
    }

    public void retirerPion(Pion pion) {
        int position = pion.getPosition() - 1;
        cases[position].retirerPion(pion);
    }

    public int getNbCases() {
        return nbCases;
    }
    
    public Case getCase(Pion pion){
        int position = pion.getPosition() - 1;
        return cases[position];
    }
    
    public void initialiser(ControlDeplacerPion controlDeplacerPion, ControlSlotMachine controlSlotMachine){
        // Définition des fournisseurs de cases spéciales
        Supplier<Case> caseNormale = Case::new;
        Supplier<Case> caseSpeciale1 = () -> new CaseDegat(this.notificationService); 
        Supplier<Case> caseSpeciale2 = () -> new CaseGambling(this.notificationService); 
        Supplier<Case> caseSpeciale3 = () -> new CaseRejouer(controlDeplacerPion,controlSlotMachine, this.notificationService); 
        Supplier<Case> caseSpeciale4 = () -> new CaseReculer(controlDeplacerPion,controlSlotMachine, this.notificationService);
        
        for (int i = 0; i < nbCases; i++) {
            Supplier<Case> caseSupplier;

            //ATTENTION LES i SONT DECALES DE 1 !!! il faut faire -1 (case 10 = case 9 en vrai)
            switch (i) {
                case 3, 13, 21, 29, 30 -> caseSupplier = caseSpeciale1;
                case 7, 19, 31 -> caseSupplier = caseSpeciale2;
                case 8, 15, 23 -> caseSupplier = caseSpeciale3;
                case 9, 16, 24 -> caseSupplier = caseSpeciale4;
                
                default -> caseSupplier = caseNormale;
            }

            this.cases[i] = caseSupplier.get();
        }
        
    }
    

}
