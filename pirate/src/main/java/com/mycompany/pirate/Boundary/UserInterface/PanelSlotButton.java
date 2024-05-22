/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.FX_CLICK;
import static com.mycompany.pirate.data.FileRef.FX_OFF_SLOTMACHINE;
import static com.mycompany.pirate.data.FileRef.FX_ON_SLOTMACHINE;
import static com.mycompany.pirate.data.FileRef.IMAGE_SLOT_MACHINE;
import utils.SoundPlayer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author BEN JAAFAR
 * Panel faisant office de bouton pour lancer la machine a sous
 */
public class PanelSlotButton extends javax.swing.JPanel {
    private Image slotMachineImage;
    SlotMachineWindow window;
    private SoundPlayer fxOnSlotmachine = new SoundPlayer(FX_ON_SLOTMACHINE);
    private SoundPlayer fxOffSlotmachine = new SoundPlayer(FX_OFF_SLOTMACHINE);
    private SoundPlayer fxClick = new SoundPlayer(FX_CLICK);
    private boolean isMouseOver = false;

    public PanelSlotButton() {
        loadSlotMachineImage();
        setPreferredSize(new Dimension(200, 200)); 
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fxClick.play();
                openSlotMachineWindow();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseOver = true;
                fxOffSlotmachine.stop();
                fxOnSlotmachine.play();
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseOver = false;
                fxOnSlotmachine.stop();
                fxOffSlotmachine.play();
                repaint();
            }
        });
    }

    private void loadSlotMachineImage() {
        slotMachineImage = new ImageIcon(IMAGE_SLOT_MACHINE).getImage();
    }

    private void openSlotMachineWindow() {
        window = new SlotMachineWindow();
        window.setLocationRelativeTo(null); 
        window.setVisible(true);
    }

public void startAnimation(int[] values, Runnable onAnimationEnd) {
    // Code pour démarrer l'animation
    window.startAnimation(values, onAnimationEnd);
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        
        if (isMouseOver) {
            width = (int) (width * 1.1);
            height = (int) (height * 1.1);
        }

        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;
        
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
