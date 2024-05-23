/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_1_WIN_SCREEN;
import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_2_WIN_SCREEN;
import static com.mycompany.pirate.data.FileRef.VOICELINE_PLAYER1_WIN;
import static com.mycompany.pirate.data.FileRef.VOICELINE_PLAYER2_WIN;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import utils.SoundPlayer;

/**
 *
 * @author BEN JAAFAR
 */
public class PanelImagePlayerWinScreen extends javax.swing.JPanel {
    private Image image = null;
    private String imagePath = null ;
    
    private int imageX; 
    private int imageY; 
    private int targetX;
    private int speed = 40; 
    private Timer timer; 
    
    private SoundPlayer voiceline; 

    public PanelImagePlayerWinScreen() {
        setOpaque(false);     
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, imageX, imageY, this);
    }
       
    /*load image and voiceline*/
    public void loadImage(String name){
        switch(name){
            case "Joueur 1" : 
                this.imagePath = IMAGE_PLAYER_1_WIN_SCREEN;
                this.image = new ImageIcon(imagePath).getImage();
                this.voiceline = new SoundPlayer(VOICELINE_PLAYER1_WIN);
                break;
            case "Joueur 2" : 
                this.imagePath = IMAGE_PLAYER_2_WIN_SCREEN;
                this.image = new ImageIcon(imagePath).getImage();
                this.voiceline = new SoundPlayer(VOICELINE_PLAYER2_WIN);
                break;
        }
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        
        imageX = getWidth(); 
        imageY = 0;
        targetX = 0; 
        
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(30, e -> animateImage());
        timer.start();
        
        repaint();
        voiceline.play();
    }

    private void animateImage() {
        if (imageX > targetX) {
            imageX -= speed;
            if (imageX < targetX) {
                imageX = targetX; 
            }
            repaint();
        } else {
            timer.stop(); 
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
