/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.values.TRANSPARENT_COLOR_BACKGROUND;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author BEN JAAFAR
 */
public class CasePanel extends javax.swing.JPanel {

    private boolean pionDropped = false;

    public CasePanel() {
        setBackground(Color.BLUE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Vérifie si un PionPanel est déposé ici
                if (isPionDropped(e.getX(), e.getY())) {
                    // Faire quelque chose lorsque le PionPanel est déposé
                    pionDropped = true;
                    repaint(); // Redessine le CasePanel si nécessaire
                }
            }
        });
    }

private boolean isPionDropped(int x, int y) {
    // Vérifie si les coordonnées (x, y) sont à l'intérieur du CasePanel
    // Compare avec les dimensions du CasePanel
    int panelWidth = getWidth();
    int panelHeight = getHeight();
    return x >= 0 && x < panelWidth && y >= 0 && y < panelHeight;
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pionDropped) {
            // Dessine un contour ou une indication visuelle pour montrer que le PionPanel est déposé ici
            g.setColor(Color.GREEN);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(new javax.swing.border.MatteBorder(null));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
