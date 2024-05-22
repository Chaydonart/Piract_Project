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
import java.awt.GridLayout;
import java.util.Random;
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
    private int[] finalValues;
    private final int[] currentValues = new int[3];
    private final int maxValue = 4; 
    private static final int ANIMATION_DURATION = 2000; // Animation duration in milliseconds
    private long animationStartTime;
    private Timer timer;

    public SlotMachinePanel(int[] values) {
        this.finalValues = values;
        setLayout(new GridLayout(1, 3));

        for (int i = 0; i < 3; i++) {
            slotLabels[i] = new JLabel("", SwingConstants.CENTER);
            slotLabels[i].setFont(new Font("RArial", Font.BOLD, 75));
            add(slotLabels[i]);
        }

        setOpaque(false); // Make the JPanel transparent
    }

    public void startAnimation() {
        animationStartTime = System.currentTimeMillis();
        timer = new Timer(100, e -> updateSlots());
        timer.start();
    }

    private void updateSlots() {
        long elapsedTime = System.currentTimeMillis() - animationStartTime;

        if (elapsedTime < ANIMATION_DURATION) {
            for (int i = 0; i < 3; i++) {
                currentValues[i] = random.nextInt(maxValue + 1);
                slotLabels[i].setText(String.valueOf(currentValues[i]));
            }
        } else {
            for (int i = 0; i < 3; i++) {
                slotLabels[i].setText(String.valueOf(finalValues[i]));
            }
            timer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int slotWidth = getWidth() / 3;
        int slotHeight = getHeight();

        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.BLACK);

        for (int i = 0; i < 3; i++) {
            g2d.drawRect(i * slotWidth, 0, slotWidth, slotHeight - 2);
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
