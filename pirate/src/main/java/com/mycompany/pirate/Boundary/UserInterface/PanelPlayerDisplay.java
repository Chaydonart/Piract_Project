/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_1;
import static com.mycompany.pirate.data.values.RED_CUSTOM;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author BEN JAAFAR
 */
public abstract class PanelPlayerDisplay extends javax.swing.JPanel {
    protected BufferedImage playerImage;
    protected int triangleBase = 200;
    protected int triangleHeight = 500;

    // Etat de tour
    protected Color turnColor = Color.white;
    
    protected void loadImage(String imagePath) {
        try {
            playerImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected abstract void startAnimation();
    
    public void setTurn(boolean bool) {
        if (bool) {
            this.turnColor = RED_CUSTOM;
        } else {
            this.turnColor = Color.white;
        }
        repaint(); 
    }
   
}
