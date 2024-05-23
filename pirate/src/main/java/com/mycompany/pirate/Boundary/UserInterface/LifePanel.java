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
import static com.mycompany.pirate.data.FileRef.IMAGE_LIFE_PLAYER_1;
import static com.mycompany.pirate.data.FileRef.IMAGE_LIFE_PLAYER_2;
import static com.mycompany.pirate.data.values.TRANSPARENT_COLOR_BACKGROUND;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author BEN JAAFAR
 */
public class LifePanel extends javax.swing.JPanel {
  private boolean isPlayer1 = true;
    private int viesRestantes = 5;
    private Color playerColor = Color.GRAY;
    private BufferedImage lifeImage; // Image pour représenter une vie
    private int lifeImageWidth = 30; // Largeur souhaitée pour l'image de vie
    private int lifeImageHeight = 30; // Hauteur souhaitée pour l'image de vie

    public LifePanel() {
        loadImage();
        setBackground(TRANSPARENT_COLOR_BACKGROUND);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(150, 50)); // Taille fixe pour le panneau
    }

    private void loadImage() {
        try {
            lifeImage = isPlayer1 ? ImageIO.read(new File(IMAGE_LIFE_PLAYER_1)) : ImageIO.read(new File(IMAGE_LIFE_PLAYER_2));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Vérifier si lifeImage est nul
        if (lifeImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            int bottomY = getHeight() - 20; // Position verticale basse
            int imageSpacing = 5; // Espace entre chaque image de vie

            // Position horizontale en fonction de la couleur du joueur
            int startX;
            if (playerColor == Color.GREEN) {
                startX = 20; // Position à gauche si la couleur est verte
            } else {
                startX = getWidth() - 5 * (lifeImageWidth + imageSpacing); // Position à droite pour toute autre couleur
            }

            // Dessine chaque image de vie en fonction du nombre de vies restantes
            for (int i = 0; i < viesRestantes; i++) {
                int imageX = startX + (lifeImageWidth + imageSpacing) * i; // Position horizontale de l'image
                g2d.drawImage(lifeImage, imageX, bottomY - lifeImageHeight, lifeImageWidth, lifeImageHeight, this); // Dessiner l'image de vie redimensionnée
            }
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
