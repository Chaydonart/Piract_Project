/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author BEN JAAFAR
 */
public class PionPanel extends javax.swing.JPanel {
    private Color pionColor = Color.RED;
    private int cellPosition = 0;
    public int player_number = 0;

    public PionPanel() {
        setOpaque(false); 
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        float contourWidth = 2.0f; // épaisseur personnalisée
        g2d.setStroke(new BasicStroke(contourWidth));

        g2d.setColor(Color.WHITE);
        g2d.drawOval(0, 0, getWidth(), getHeight());

        g2d.setColor(pionColor);
        g2d.fillOval(0, 0, getWidth(), getHeight());
    }

    public void setColor(Color color) {
        this.pionColor = color;
    }
    
    public void setCellPosition(int cellPos){
        this.cellPosition = cellPos;
    }
    
    public int getCellPosition(){
        return cellPosition;
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
