package com.mycompany.pirate.FonctionnalKernel.Entity;

import static com.mycompany.pirate.data.values.MAX_LIFE;
import java.awt.Color;

/**
 *
 * @author RIBEIRO
 */

public class Pion {
    private int vie;
    private int position;
    public Color color;
    private boolean alive = true;
    private final String name;

    public Pion(String name) {
        this.name = name;
        vie = MAX_LIFE;
        position = 1;
    }

    public String getName() {
        return name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public boolean isAlive() {
        return alive;
    }
    
    public void setAlive(boolean isIt) {
        this.alive = isIt;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
