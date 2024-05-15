/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static Utilities.values.BLACK_CUSTOM;
import static Utilities.values.GREEN_COLOR_BACKGROUND;
import static Utilities.values.GREEN_CUSTOM;
import static Utilities.values.RED_CUSTOM;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author BEN JAAFAR
 */
public class Board extends javax.swing.JFrame {
    public Board() {
        setLocationRelativeTo(null);
	setResizable(false);
        // Configuration de la fenêtre principale
        setTitle("Jeu de l'oie - Disposition Roulette");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 800);
        setLocationRelativeTo(null);

        // Panneau principal avec GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configuration des contraintes pour chaque cellule
        gbc.insets = new Insets(1, 1, 1, 1); // Marges entre les cellules
        gbc.fill = GridBagConstraints.BOTH; // Redimensionnement complet des cellules
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Créer et ajouter les cellules du plateau de jeu en disposition roulette
        int rows = 12;
        int cols = 3;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                // Créer une cellule de plateau
                int cellNumber = row * cols + col + 1;
                CellPanel cell = new CellPanel(cellNumber);
                
                panel.add(cell, gbc);
            }
        }

        // Ajouter le panneau au cadre principal
        add(panel);
    
        panel.setBackground(GREEN_CUSTOM);
    }

    public static void main(String[] args) {
        // Exécution de l'application
        SwingUtilities.invokeLater(() -> {
            Board frame = new Board();
            frame.setVisible(true);
        });
    }
    
    private class CellPanel extends JPanel {
        private int cellNumber;

        public CellPanel(int cellNumber) {
            setOpaque(false);
            this.cellNumber = cellNumber;
            this.setPreferredSize(new Dimension(80, 80)); // Taille préférée pour la cellule
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4)); // Bordure noire de 2 pixels

            // Déterminer la couleur de la cellule
            if ((cellNumber % 2 == 0 && cellNumber <= 36) || cellNumber == 0) {
                g2d.setColor(Color.RED);
            } else if (cellNumber <= 36) {
                g2d.setColor(Color.BLACK);
            } else {
                g2d.setColor(Color.GREEN); // Cellule "0"
            }

            // Dessiner le cercle
            int diameter = Math.min(getWidth(), getHeight()) - 10; // Ajuster pour les marges
            int diameter2 = (Math.min(getWidth(), getHeight()) - 10) + 20; // Ajuster pour les marges
            int x = (getWidth() - diameter2) / 2;
            int y = (getHeight() - diameter) / 2;
            g2d.fillOval(x, y, diameter2, diameter );

            // Dessiner le numéro de la cellule
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 25));
            FontMetrics fm = g2d.getFontMetrics();
            String text = Integer.toString(cellNumber);
            int textX = x + (diameter2 - fm.stringWidth(text)) / 2;
            int textY = y + ((diameter - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(text, textX, textY);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
