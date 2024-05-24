/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.values.RED_CUSTOM;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author BEN JAAFAR
 * PanelDisplayer qui permet d'avoir les methodes generique pour les PanelPlayer
 */
public abstract class PanelPlayerDisplay extends javax.swing.JPanel {
    protected BufferedImage playerImage;
    protected BufferedImage idleImage;
    protected BufferedImage victoryImage;
    protected BufferedImage damageImage;
    protected int triangleBase = 200;
    protected int triangleHeight = 500;
    protected Color colorBackground = Color.white;
    

    // Etat de tour
    protected Color turnColor = Color.white;

    public enum PlayerState {
        IDLE,
        VICTORY,
        DAMAGE
    }

    protected PlayerState currentState = PlayerState.IDLE;

    protected void loadImage(String imagePath) {
        try {
            playerImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected BufferedImage loadImageFromFile(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setState(PlayerState state) {
        this.currentState = state;
        updateImage();
    }

    protected void updateImage() {
        switch (currentState) {
             case IDLE -> playerImage = idleImage;
            case VICTORY -> playerImage = victoryImage;
            case DAMAGE -> playerImage = damageImage;
        }
        repaint();
    }

    protected abstract void startAnimation();
    
    public void setTurn(boolean bool) {
        if (bool) {
            this.turnColor = colorBackground;
        } else {
            this.turnColor = Color.white;
        }
        repaint(); 
    }
    
    public abstract void playVoiceline(PlayerState state);
}

