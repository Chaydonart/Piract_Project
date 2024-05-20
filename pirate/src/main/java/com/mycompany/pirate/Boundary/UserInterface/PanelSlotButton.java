/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.IMAGE_SLOT_MACHINE;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author BEN JAAFAR
 */
public class PanelSlotButton extends javax.swing.JPanel {
    private Image slotMachineImage;
    private boolean isMouseOver = false;
    
     public PanelSlotButton() {
        loadSlotMachineImage();
        setPreferredSize(new Dimension(200, 200)); // Taille du bouton de la slot machine
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                openSlotMachineWindow();
            }
            
             @Override
            public void mouseEntered(MouseEvent e) {
                isMouseOver = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isMouseOver = false;
                repaint();
            }
        });
    }
     
    private void loadSlotMachineImage() {
        // Charge l'image de la machine à sous (remplacez "slot_machine.png" par le chemin de votre image)
        slotMachineImage = new ImageIcon(IMAGE_SLOT_MACHINE).getImage();
    }


    private void openSlotMachineWindow() {
        SlotMachineWindow window = new SlotMachineWindow();
        window.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        window.setVisible(true);
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        
        if (isMouseOver) {
            // Agrandir l'image de 10%
            width = (int)(width * 1.1);
            height = (int)(height * 1.1);
        }
        
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;
        
        // Dessine l'image de la machine à sous
        g.drawImage(slotMachineImage, x, y, width, height, this);
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
