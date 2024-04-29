/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author BEN JAAFAR
 */
public class CasePanel extends javax.swing.JPanel {

    private Rectangle caseJeu; // Le rectangle représentant une case du jeu de l'oie
    private boolean pionEstDessus = false; 
    private boolean havePion = false;

    public CasePanel() {
        // Créez un rectangle pour représenter une case du jeu de l'oie
        caseJeu = new Rectangle(WIDTH, WIDTH, WIDTH, WIDTH); 
        
        addMouseListener(new MouseAdapter() {
            

            @Override
            public void mouseEntered(MouseEvent e) {
                pionEstDessus = true;
                System.out.println("Pion dans la case");
                repaint(); // Redessine le JPanel
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                pionEstDessus = false;
                System.out.println("Pion pas dans la case");
                repaint(); // Redessine le JPanel
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Récupérez les dimensions du JPanel
        int largeur = getWidth();
        int hauteur = getHeight();

        // Dessinez le rectangle en utilisant les dimensions du JPanel
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, largeur, hauteur);

        // Si un pion est dessus, dessinez un cercle rouge au centre
        if (pionEstDessus) {
            g2d.setColor(Color.RED);
            g2d.fillRect(0, 0, largeur, hauteur);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 289, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
