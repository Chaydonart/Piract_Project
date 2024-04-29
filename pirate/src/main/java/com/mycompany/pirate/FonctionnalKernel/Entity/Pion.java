package com.mycompany.pirate.FonctionnalKernel.Entity;

import java.awt.Color;

public class Pion {
    private int vie;
    private Position position;
    public Color color; 

    public Pion() {
        vie = 3;
        position = new Position(0, 0);
    }
    
    public void setColor(Color color){
        this.color = color;
    }
}
