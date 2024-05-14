/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author BEN JAAFAR
 */
public class AnimatedGIF extends javax.swing.JPanel {
 private ImageIcon gifIcon;
    private JLabel gifLabel;

    public AnimatedGIF() {
        setOpaque(false);
        // Charger le GIF depuis le fichier
        gifIcon = new ImageIcon("C:\\Users\\BEN JAAFAR\\Desktop\\roulette.gif");
        
        // Créer un JLabel pour afficher le GIF
        gifLabel = new JLabel(gifIcon);
        
        // Ajouter le JLabel au JPanel
        add(gifLabel);
        
        // Démarrer l'animation du GIF
        ((ImageIcon) gifLabel.getIcon()).setImageObserver(gifLabel);
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
