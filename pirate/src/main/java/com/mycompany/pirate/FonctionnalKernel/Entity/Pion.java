package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.awt.Color;

public class Pion {
    private int vie;
    private int position;
    public Color color;
    private boolean alive = true;
    private String name;

    public Pion(String name) {
        this.name = name;
        vie = 3;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
