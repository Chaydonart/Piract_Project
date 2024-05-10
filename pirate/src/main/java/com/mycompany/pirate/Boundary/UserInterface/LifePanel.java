/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static Utilities.FileRef.imageLifePlayer1;
import static Utilities.FileRef.imageLifePlayer2;

/**
 *
 * @author BEN JAAFAR
 */
public class LifePanel extends javax.swing.JPanel {
    private boolean isPlayer1 = true;
    private int viesRestantes = 5;
    private Color playerColor = Color.GRAY;
    private BufferedImage lifeImage; // Image pour représenter une vie

    public LifePanel() {    
        loadImage();
    }
    
    private void loadImage(){
        try {
            lifeImage = isPlayer1 ? ImageIO.read(new File(imageLifePlayer1)) : ImageIO.read(new File(imageLifePlayer2));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int bottomY = getHeight() - 20; // Position verticale basse
        int imageWidth = lifeImage.getWidth(); // Largeur de l'image de vie
        int imageHeight = lifeImage.getHeight(); // Hauteur de l'image de vie
        int imageSpacing = 10; // Espace entre chaque image de vie

        // Position horizontale en fonction de la couleur du joueur
        int startX;
        if (playerColor == Color.GREEN) {
            startX = 20; // Position à gauche si la couleur est verte
        } else {
            startX = getWidth() - 5 * (imageWidth + imageSpacing); // Position à droite pour toute autre couleur
        }

        // Dessine chaque image de vie en fonction du nombre de vies restantes
        for (int i = 0; i < viesRestantes; i++) {
            int imageX = startX + (imageWidth + imageSpacing) * i; // Position horizontale de l'image
            g.drawImage(lifeImage, imageX, bottomY - imageHeight, this); // Dessiner l'image de vie
        }
    }

    // Plus tard on va juste lire les variables du jeu
    public void perdreVie() {
        if (viesRestantes > 0) {
            viesRestantes--;
            repaint(); 
        }
    }

    public void setPlayer2() {
        this.isPlayer1 = false;
        loadImage();
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
