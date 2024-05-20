/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author BEN JAAFAR
 */
public class SlotMachinePanel extends javax.swing.JPanel {
   private final JLabel[] slotLabels = new JLabel[3];
    private final Random random = new Random();
    private final int[] finalValues = new int[3];
    private final int[] currentValues = new int[3];
    private static final int maxValue = 4; // Valeur maximale pour les slots
    private static final int animationDuration = 2000; // Durée de l'animation en millisecondes
    private long animationStartTime;
    private Timer timer;

    public SlotMachinePanel() {
        setLayout(new GridLayout(1, 3));

        for (int i = 0; i < 3; i++) {
            slotLabels[i] = new JLabel();
            slotLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            slotLabels[i].setFont(new Font("Arial", Font.BOLD, 36));
            add(slotLabels[i]);
        }

        setOpaque(false); // Rend le JPanel transparent
    }

    public void start() {
        animationStartTime = System.currentTimeMillis();
        timer = new Timer(100, e -> updateSlots());
        timer.start();
    }

    private void updateSlots() {
        long elapsedTime = System.currentTimeMillis() - animationStartTime;

        // Générer de nouvelles valeurs de slot pour l'animation
        if (elapsedTime < animationDuration) {
            for (int i = 0; i < 3; i++) {
                currentValues[i] = random.nextInt(maxValue + 1);
                slotLabels[i].setText(String.valueOf(currentValues[i]));
            }
        } else {
            // Arrêter l'animation et afficher les valeurs finales
            timer.stop();
            for (int i = 0; i < 3; i++) {
                finalValues[i] = random.nextInt(maxValue + 1); // Remplacez par les valeurs du contrôleur
                slotLabels[i].setText(String.valueOf(finalValues[i]));
            }
        }
    }
    
     @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // Conversion en Graphics2D
        
        int slotWidth = getWidth() / 3;
        int slotHeight = getHeight();
        
        // Augmenter la grosseur du trait du rectangle
        g2d.setStroke(new BasicStroke(10)); // 3 pixels
        
        for (int i = 0; i < 3; i++) {
            int x = i * slotWidth;
            int y = 0;
            g.setColor(Color.BLACK); // Couleur des contours
            g.drawRect(x, y, slotWidth, slotHeight - 2); // Dessin du rectangle autour de chaque case
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
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
