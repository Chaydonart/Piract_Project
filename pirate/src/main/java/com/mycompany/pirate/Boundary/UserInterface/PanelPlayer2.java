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

/**
 *
 * @author BEN JAAFAR
 */
public class PanelPlayer2 extends javax.swing.JPanel {
    private String imagePath = "C:\\Users\\BEN JAAFAR\\Desktop\\player2.png";
    private BufferedImage playerImage; // Image du joueur
    private int triangleBase = 200; // Base du triangle
    private int triangleHeight = 500; // Hauteur du triangle

    public PanelPlayer2() {
        loadImage();
        setPreferredSize(new Dimension(triangleBase, triangleHeight));
    }

    private void loadImage() {
        try {
            playerImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

   @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Dessiner le triangle rectangle inversé
        int[] xPoints = {0, triangleBase, triangleBase};
        int[] yPoints = {0, 0, triangleHeight};
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g2d.setColor(Color.WHITE); // Couleur du triangle, changez selon vos besoins
        g2d.fillPolygon(triangle);

        // Vérifier si l'image du joueur est non nulle
        if (playerImage != null) {
            // Calculer les dimensions redimensionnées de l'image
            int imageWidth = triangleBase - 20; // Largeur de l'image avec une marge de 10 pixels de chaque côté
            int imageHeight = (int) ((double) playerImage.getHeight() / playerImage.getWidth() * imageWidth); // Conserver le ratio de l'image

            // Assurer que l'image ne dépasse pas la hauteur du triangle
            if (imageHeight > triangleHeight) {
                imageHeight = triangleHeight - 20; // Hauteur de l'image avec une marge de 10 pixels en haut et en bas
                imageWidth = (int) ((double) playerImage.getWidth() / playerImage.getHeight() * imageHeight); // Conserver le ratio de l'image
            }

            // Calculer la position pour centrer l'image à la base du triangle
            int imageX = (triangleBase - imageWidth) / 2; // Centrer horizontalement à la base
            int imageY = triangleHeight - imageHeight; // Placer l'image en bas du triangle

            // Dessiner l'image redimensionnée à l'intérieur du triangle
            g2d.setClip(triangle); // Définir le clip pour que l'image soit dessinée à l'intérieur du triangle
            g2d.drawImage(playerImage, imageX, imageY, imageWidth, imageHeight, this);
            g2d.setClip(null); // Réinitialiser le clip
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
