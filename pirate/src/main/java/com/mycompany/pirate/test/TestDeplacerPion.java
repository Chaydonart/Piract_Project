package com.mycompany.pirate.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.RowFilter.Entry;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;
import com.mycompany.pirate.Services.DeplacerPionService;

public class TestDeplacerPion extends Tester{
    //initialisation pour les tests
    private Random rng = new Random();
    private Plateau p = TestRes.plateau;
    private DeplacerPionService dps = TestRes.deplacerPionService;
    private Pion p1 = TestRes.joueur1;
    private Pion p2 = TestRes.joueur2;
    private List<Pion> lp = new ArrayList<>(){
        {
            add(p1);
            add(p2);
        };
    };
    
    private PionRepository pr = TestRes.pionRepository;
    private ControlDeplacerPion cdp = new ControlDeplacerPion(dps, pr);
    // fin initialisation
    

    public TestDeplacerPion() {
        //ajout des pions dans plateau
        super();
        this.getFuncRes().put("testDeplacementValid",testDeplacementValid());
        this.getFuncRes().put("testDeplacementSurpasse",testDeplacementSurpasse());
    }
    public boolean testDeplacementValid() {
        int oldPosition = TestRes.joueur1.getPosition();
        int deplacement = rng.nextInt(5);
        TestRes.deplacerPionService.deplacerPion(TestRes.joueur1, deplacement);
        
        return oldPosition+deplacement == TestRes.joueur1.getPosition();
    }

    public boolean testDeplacementSurpasse(){
        int deplacement = TestRes.plateau.getNbCases();
        TestRes.deplacerPionService.deplacerPion(TestRes.joueur2, deplacement);
        return TestRes.joueur2.getPosition() == TestRes.plateau.getNbCases();
    }
    
    

    

    

}
