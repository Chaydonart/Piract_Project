/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static Utilities.values.TRANSPARENT_COLOR_BACKGROUND;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author BEN JAAFAR
 */
public class PionPanel extends javax.swing.JPanel {

    private int initialX;
    private int initialY;
    private int currentX;
    private int currentY;
    private Color pionColor;

    public PionPanel() {
        setDoubleBuffered(true);
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
                currentX += deltaX;
                currentY += deltaY;
                setLocation(currentX, currentY);
            }
        });
        
         addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Mettez à jour les coordonnées actuelles en fonction de la nouvelle taille
                currentX = getX();
                currentY = getY();
            }
        });
        setOpaque(false); // Rend le fond transparent
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Convertir Graphics en Graphics2D pour utiliser setStroke()
        Graphics2D g2d = (Graphics2D) g;

        // Définir l'épaisseur du contour
        float contourWidth = 2.0f; // épaisseur personnalisée
        g2d.setStroke(new BasicStroke(contourWidth));

        // Dessiner le contour blanc
        g2d.setColor(Color.WHITE);
        g2d.drawOval(0, 0, getWidth(), getHeight());

        // Remplir le cercle avec la couleur du pion
        g2d.setColor(pionColor);
        g2d.fillOval(0, 0, getWidth(), getHeight());
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
            .addGap(0, 419, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
