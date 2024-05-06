package com.mycompany.pirate.FonctionnalKernel.Controller;

import java.util.Random;

public class ControlLancerDe {
    private Random rand;

    public ControlLancerDe() {
        rand = new Random();
    }

    public int lancer() {
        return rand.nextInt(6) + 1;
    }

    // main method for testing
    public static void main(String[] args) {
        ControlLancerDe controlLancerDe = new ControlLancerDe();
        System.out.println(controlLancerDe.lancer());
    }
}
