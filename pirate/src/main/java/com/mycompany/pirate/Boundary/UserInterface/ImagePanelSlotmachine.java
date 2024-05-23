/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static com.mycompany.pirate.data.FileRef.IMAGE_SLOT_MACHINE;
import static com.mycompany.pirate.data.values.TRANSPARENT_COLOR_BACKGROUND;
import java.awt.Graphics;

/**
 *
 * @author BEN JAAFAR
 */
public class ImagePanelSlotmachine extends javax.swing.JPanel {

    private Image backgroundImage;

    public ImagePanelSlotmachine() {
        try {
            backgroundImage = ImageIO.read(new File(IMAGE_SLOT_MACHINE));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (backgroundImage != null) {
            Dimension size = new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null); // Pour ne pas utiliser de layout manager et permettre de positionner les composants manuellement
        }
        
        setBackground(TRANSPARENT_COLOR_BACKGROUND);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
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
            .addGap(0, 461, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
