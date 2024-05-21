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

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;

/**
 *
 * @author BEN JAAFAR
 */
public class SlotMachinePanel extends javax.swing.JPanel {
   private final JLabel[] slotLabels = new JLabel[3];
    private final Random random = new Random();
    private int[] finalValues = new int[3];
    private final int[] currentValues = new int[3];
    private static final int maxValue = 4; // Valeur maximale pour les slots
    private static final int animationDuration = 2000; // Durée de l'animation en millisecondes
    private long animationStartTime;
    private Timer timer;
    private ControlSlotMachine csm = new ControlSlotMachine(null);

    public SlotMachinePanel() {
        setLayout(new GridLayout(1, 3));
        finalValues = csm.spin();

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
    
    /*
    * Va gerer "l'animation", toutes les 100ms on va changer la valeurs des labels
    */
    private void updateSlots() {
        long elapsedTime = System.currentTimeMillis() - animationStartTime;
        int values[] = csm.spin();
        if (elapsedTime < animationDuration) {
            for (int i = 0; i < 3; i++) {
                currentValues[i] = random.nextInt(maxValue + 1);
                slotLabels[i].setText(String.valueOf(currentValues[i]));
            }
        } else {
            // Arrêter l'animation et afficher les valeurs finales
            timer.stop();
            for (int i = 0; i < 3; i++) {
            	slotLabels[i].setText(String.valueOf(finalValues[i]));
//                finalValues[i] = random.nextInt(maxValue + 1);
//                slotLabels[i].setText(String.valueOf(finalValues[i]));
            }
        }
    }
    
    /*
    * Permet de faire le cadrage pour simuler la vitre d'une machine a sous
    */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        int slotWidth = getWidth() / 3;
        int slotHeight = getHeight();
        
        g2d.setStroke(new BasicStroke(10));
        
        for (int i = 0; i < 3; i++) {
            int x = i * slotWidth;
            int y = 0;
            g.setColor(Color.BLACK); 
            g.drawRect(x, y, slotWidth, slotHeight - 2);
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

	public int[] getFinalValues() {
		// TODO Auto-generated method stub
		return finalValues;
	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
