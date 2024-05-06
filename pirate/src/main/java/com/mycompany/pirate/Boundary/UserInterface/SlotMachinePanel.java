/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

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
    private final Timer timer;
    private final int[] finalValues = new int[3];
    private final int[] currentValues = new int[3];
    private final int maxValue = 4; // Valeur maximale pour les slots
    private final int animationDuration = 2000; // Durée de l'animation en millisecondes
    private long animationStartTime;

    public SlotMachinePanel() {
        setLayout(new GridLayout(1, 3));

        for (int i = 0; i < 3; i++) {
            slotLabels[i] = new JLabel();
            slotLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            add(slotLabels[i]);
        }

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSlots();
            }
        });

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> start());
        add(startButton);
    }

    private void start() {
        // Initialiser les valeurs actuelles à zéro
        for (int i = 0; i < 3; i++) {
            currentValues[i] = 0;
        }

        animationStartTime = System.currentTimeMillis();
        timer.start();
    }

    private void updateSlots() {
        long elapsedTime = System.currentTimeMillis() - animationStartTime;

        // Générer de nouvelles valeurs de slot pour l'animation
        if (elapsedTime < animationDuration) {
            for (int i = 0; i < 3; i++) {
                currentValues[i] = random.nextInt(maxValue + 1); // Valeurs entre 0 et maxValue
                slotLabels[i].setText(String.valueOf(currentValues[i]));
            }
        } else {
            // Arrêter l'animation et afficher les valeurs finales
            timer.stop();
            for (int i = 0; i < 3; i++) {
                finalValues[i] = random.nextInt(maxValue + 1); // ICI on mettra les valeurs donner par le controlleur
                slotLabels[i].setText(String.valueOf(finalValues[i]));
            }

            // Vérifier les gains ou autres conditions ici
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Slot Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.getContentPane().add(new SlotMachinePanel());
        frame.setVisible(true);
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
