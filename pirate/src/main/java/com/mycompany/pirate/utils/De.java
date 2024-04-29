package com.mycompany.pirate.utils;
import java.util.Random;

public class De {

    private Random rand;

    public De() {
        rand = new Random();
    }

    public int lancer() {
        return rand.nextInt(6) + 1;
    }
}
