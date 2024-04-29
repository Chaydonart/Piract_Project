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
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 50;
        int startAngle = 90; // Angle de départ (en degrés)

        // Dessine chaque part du cercle
        for (int i = 0; i < viesRestantes; i++) {
            g.setColor(playerColor); // Couleur des vies restantes
            g.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius,
                    startAngle + i * 72, 72); // 72 degrés pour chaque part
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
