/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author BEN JAAFAR
 */
public class PionPanel extends javax.swing.JPanel {

private int initialX;
    private int initialY;
    private int currentX; // Coordonnée x actuelle du pion
    private int currentY; // Coordonnée y actuelle du pion
    private Color pionColor;

    public PionPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialX = e.getX();
                initialY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - initialX;
                int deltaY = e.getY() - initialY;
                currentX += deltaX; // Mettre à jour les coordonnées x actuelles
                currentY += deltaY; // Mettre à jour les coordonnées y actuelles
                setLocation(currentX, currentY);
            }
        });
    }
    
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(pionColor);
        g.fillOval(0, 0, getWidth(), getHeight());
    }

    public void setColor(Color color) {
        this.pionColor = color;
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
