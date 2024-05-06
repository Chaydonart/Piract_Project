package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.awt.Color;

public class Pion {
    private int vie;
    private Position position;
    public Color color; 
    private boolean alive = true;

    public Pion() {
        vie = 3;
        position = new Position(0, 0);
    }
    
    public void setColor(Color color){
        this.color = color;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }
    
    public boolean isAlive(){
        return alive;
    }
}
