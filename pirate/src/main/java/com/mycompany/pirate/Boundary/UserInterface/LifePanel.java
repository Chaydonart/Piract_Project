/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author BEN JAAFAR
 */
public class LifePanel extends javax.swing.JPanel {
   
   
   private int viesRestantes = 5;
   private Color playerColor = Color.GRAY ;
   
    @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int bottomY = getHeight() - 20; // Position verticale basse
    int radius = 20;
    int circleSpacing = 10;

    // Position horizontale en fonction de la couleur du joueur
    int startX;
    if (playerColor == Color.GREEN) {
        startX = 20; // Position à gauche si la couleur est Verte
    } else {
        startX = getWidth() - 5 * (radius * 2 + circleSpacing); // Position à droite pour toute autre couleur
    }

    // Dessine chaque cercle en fonction du nombre de vies restantes
    g.setColor(playerColor); // Couleur des vies restantes
    for (int i = 0; i < viesRestantes; i++) {
        int circleX = startX + ((radius * 2 + circleSpacing) * i); // Calcul de la position en commençant par le cercle le plus à gauche ou à droite
        g.fillOval(circleX, bottomY - radius * 2, radius * 2, radius * 2); // Position Y ajustée pour placer les cercles en bas
    }
}

    // Méthode pour décrémenter le nombre de vies
    public void perdreVie() {
        if (viesRestantes > 0) {
            viesRestantes--;
            repaint(); // Redessine le panneau
        }
    }
    
    public void setColot(Color color){
        this.playerColor = color;
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
