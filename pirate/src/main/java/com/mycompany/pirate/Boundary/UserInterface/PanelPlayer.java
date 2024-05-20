/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.values.TRANSPARENT_COLOR_BACKGROUND;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author BEN JAAFAR
 */
public class PanelPlayer extends javax.swing.JPanel {
    private String imagePath = "C:\\Users\\BEN JAAFAR\\Desktop\\player.png";
    private BufferedImage playerImage;
    private int triangleBase = 200;
    private int triangleHeight = 500;
    private Color turnColor = Color.white;
    private double imageY = 0; // Position verticale de l'image (utilisation de double pour la précision)
    private double imageSpeed = 0.1; // Vitesse de déplacement de l'image (plus la valeur est petite, plus le mouvement est lent)
    private boolean movingDown = true; // Indique si l'image se déplace vers le bas


    public PanelPlayer() {
        loadImage();
        setPreferredSize(new Dimension(triangleBase, triangleHeight));
    }

    private void loadImage() {
        try {
            playerImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            // Gérer l'erreur de chargement de l'image
            System.err.println("Erreur lors du chargement de l'image : " + ex.getMessage());
        }
        
        startAnimation();
    }

    private void startAnimation() {
        Timer timer = new Timer(10, e -> {
            // Mettre à jour la position verticale de l'image
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
                    if (imageY >= triangleHeight - imageHeight) {
                        // Inverser la direction lorsque l'image atteint le bas
                        movingDown = false;
                    }
                } else {
                    imageY -= imageSpeed;
                    if (imageY <= 0) {
                        // Inverser la direction lorsque l'image atteint le haut
                        movingDown = true;
                    }
                }

                repaint(); // Redessiner le composant avec la nouvelle position de l'image
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Dessiner le triangle rectangle
        int[] xPoints = {0, triangleBase, 0};
        int[] yPoints = {0, triangleHeight, triangleHeight};
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g2d.setColor(turnColor);
        g2d.fillPolygon(triangle);

        // Dessiner l'image du joueur avec l'animation
        if (playerImage != null) {
            int imageWidth = triangleBase - 20;
            int imageHeight = (int) ((double) playerImage.getHeight() / playerImage.getWidth() * imageWidth);

            if (imageHeight > triangleHeight - 20) {
                imageHeight = triangleHeight - 20;
                imageWidth = (int) ((double) playerImage.getWidth() / playerImage.getHeight() * imageHeight);
            }

            int imageX = (triangleBase - imageWidth) / 2;

            // Convertir imageY en int pour dessiner l'image
            int imageYInt = (int) imageY;

            g2d.setClip(triangle);
            g2d.drawImage(playerImage, imageX, imageYInt, imageWidth, imageHeight, this);
            g2d.setClip(null);
        }

        g2d.dispose();
    }
    
    public void setTurn(boolean bool) {
        if (bool) {
            this.turnColor = Color.blue;
        } else {
            this.turnColor = Color.white;
        }
        repaint(); // Redessine le composant avec les nouvelles dimensions du triangle
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
