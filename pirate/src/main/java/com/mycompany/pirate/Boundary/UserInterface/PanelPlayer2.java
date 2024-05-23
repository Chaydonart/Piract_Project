/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_2;
import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_2_DAMAGE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.Timer;

/**
 *
 * @author BEN JAAFAR
 */
public class PanelPlayer2 extends PanelPlayerDisplay {
    private double imageY = 0;
    private double imageSpeed = 0.2;
    private boolean movingDown = true;

    public PanelPlayer2() {
        loadImages();
        colorBackground = Color.BLACK;
        setPreferredSize(new Dimension(triangleBase, triangleHeight));
        updateImage();
        startAnimation();
    }

    private void loadImages() {
        idleImage = loadImageFromFile(IMAGE_PLAYER_2);
        victoryImage = loadImageFromFile(IMAGE_PLAYER_2_DAMAGE);
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

        if (playerImage != null) {  
            int imageWidth = triangleBase - 20; 
            int imageHeight = (int) ((double) playerImage.getHeight() / playerImage.getWidth() * imageWidth); 

            // Assurer que l'image ne dépasse pas la hauteur du triangle
            if (imageHeight > triangleHeight) {
                imageHeight = triangleHeight - 20; 
                imageWidth = (int) ((double) playerImage.getWidth() / playerImage.getHeight() * imageHeight); 
            }

            // Calculer la position pour centrer l'image à la base du triangle
            int imageX = (triangleBase - imageWidth) / 2 + 10; 
            int imageYInt = (int) imageY; 

            // Dessiner l'image redimensionnée à l'intérieur du triangle
            g2d.setClip(triangle);
            g2d.drawImage(playerImage, imageX, imageYInt, imageWidth, imageHeight, this);
            g2d.setClip(null); 
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
