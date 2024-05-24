/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.Boundary.UserInterface.PanelPlayerDisplay.PlayerState.DAMAGE;
import static com.mycompany.pirate.Boundary.UserInterface.PanelPlayerDisplay.PlayerState.VICTORY;
import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_1_VICTORY;
import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_2;
import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_2_DAMAGE;
import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_2_VICTORY;
import static com.mycompany.pirate.data.FileRef.VOICELINE_PLAYER2_DAMAGE;
import static com.mycompany.pirate.data.FileRef.VOICELINE_PLAYER2_VICTORY;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.Timer;
import utils.SoundPlayer;

/**
 *
 * @author BEN JAAFAR
 */
public class PanelPlayer2 extends PanelPlayerDisplay {
    private double imageY = 0;
    private double imageSpeed = 0.2;
    private boolean movingDown = true;
    private SoundPlayer voicelineDamage = new SoundPlayer(VOICELINE_PLAYER2_DAMAGE);
    private SoundPlayer voicelineVictory = new SoundPlayer(VOICELINE_PLAYER2_VICTORY);
    

    public PanelPlayer2() {
        loadImages();
        colorBackground = Color.BLACK;
        setPreferredSize(new Dimension(triangleBase, triangleHeight));
        updateImage();
        startAnimation();
    }

    private void loadImages() {
        idleImage = loadImageFromFile(IMAGE_PLAYER_2);
        victoryImage = loadImageFromFile(IMAGE_PLAYER_2_VICTORY);
        damageImage = loadImageFromFile(IMAGE_PLAYER_2_DAMAGE);
    }

    
    @Override
    public void startAnimation() {
        Timer timer = new Timer(20, e -> {
            if (playerImage != null && playerImage.getWidth() != 0) {
                int imageWidth = triangleBase - 20;
                int imageHeight = playerImage.getHeight() != 0 ? (int) ((double) playerImage.getHeight() / playerImage.getWidth() * imageWidth) : 0;

                if (imageHeight > triangleHeight - 20) {
                    imageHeight = triangleHeight - 20;
                    imageWidth = (int) ((double) playerImage.getWidth() / playerImage.getHeight() * imageHeight);
                }

                // Déplacer l'image en fonction de la vitesse
                if (movingDown) {
                    imageY += imageSpeed;
                    if (imageY >= triangleHeight - imageHeight - 150) {
                        movingDown = false;
                    }
                } else {
                    imageY -= imageSpeed;
                    if (imageY <= 0) {
                        movingDown = true;
                    }
                }

                repaint(); 
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        int[] xPoints = {0, triangleBase, triangleBase};
        int[] yPoints = {0, 0, triangleHeight};
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g2d.setColor(turnColor);
        g2d.fillPolygon(triangle);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawPolygon(triangle);

        if (playerImage != null) {  
            int imageWidth = triangleBase - 20; 
            int imageHeight = (int) ((double) playerImage.getHeight() / playerImage.getWidth() * imageWidth); 

            if (imageHeight > triangleHeight) {
                imageHeight = triangleHeight - 20; 
                imageWidth = (int) ((double) playerImage.getWidth() / playerImage.getHeight() * imageHeight); 
            }

            int imageX = (triangleBase - imageWidth) / 2 + 10; 
            int imageYInt = (int) imageY; 

            g2d.setClip(triangle);
            g2d.drawImage(playerImage, imageX, imageYInt, imageWidth, imageHeight, this);
            g2d.setClip(null); 
        }
    
    }
    
    @Override
    public void playVoiceline(PlayerState state){
         switch (currentState) {
            case VICTORY -> voicelineVictory.play();
            case DAMAGE ->voicelineDamage.play();
        }
    }
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
