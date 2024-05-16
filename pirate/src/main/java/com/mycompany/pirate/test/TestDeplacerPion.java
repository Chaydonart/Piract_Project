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
    private Plateau p = new Plateau(36, null);
    private DeplacerPionService dps = new DeplacerPionService(p);
    private Pion p1 = new Pion("p1");
    private Pion p2 = new Pion("p2");
    // private List<Pion> lp = new ArrayList<>(){
    //     {
    //         add(p1);
    //         add(p2);
    //     };
    // };
    // private PionRepository pr = new PionRepository(lp);
    // private ControlDeplacerPion cdp = new ControlDeplacerPion(dps, pr);
    //fin initialisation
    

    public TestDeplacerPion() {
        p.poserPion(p1);
        p.poserPion(p2);
        p.initialiser(null, null);
        this.getFuncRes().put("testDeplacementValid",testDeplacementValid());
        this.getFuncRes().put("testDeplacementSurpasse",testDeplacementSurpasse());
    }
    public boolean testDeplacementValid() {
        int oldPosition = p1.getPosition();
        int deplacement = rng.nextInt(5);
        dps.deplacerPion(p1,deplacement);
        return oldPosition+deplacement == p1.getPosition();
    }

    public boolean testDeplacementSurpasse(){
        int deplacement = p.getNbCases();
        dps.deplacerPion(p2, deplacement);
        return p2.getPosition() == p.getNbCases();
    }
    
    

    

    public static void main(String[] args) {
        var t = new TestDeplacerPion();
        t.results();
    }

}
