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
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

/**
 *
 * @author BEN JAAFAR
 * Panel faisant office de bouton pour lancer la machine a sous
 * Il comprends des animations et fx sonore 
 */
public class PanelSlotButton extends javax.swing.JPanel {
    private Image slotMachineImage;
    private SlotMachineWindow window;
    private final SoundPlayer fxOnSlotmachine;
    private final SoundPlayer fxOffSlotmachine;
    private final SoundPlayer fxClick;
    private boolean isMouseOver = false;
    private final MouseAdapter mouseAdapter;

    public PanelSlotButton() {
        fxOnSlotmachine = new SoundPlayer(FX_ON_SLOTMACHINE);
        fxOffSlotmachine = new SoundPlayer(FX_OFF_SLOTMACHINE);
        fxClick = new SoundPlayer(FX_CLICK);

        loadSlotMachineImage();
        setPreferredSize(new Dimension(200, 200));

        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                handleMouseEnter();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                handleMouseExit();
            }
        };
        addMouseListener(mouseAdapter);
    }

    private void loadSlotMachineImage() {
        slotMachineImage = new ImageIcon(IMAGE_SLOT_MACHINE).getImage();
    }

    private void handleMouseClick() {
        fxClick.play();
        openSlotMachineWindow();
    }

    private void handleMouseEnter() {
        isMouseOver = true;
        fxOffSlotmachine.stop();
        fxOnSlotmachine.play();
        repaint();
    }

    private void handleMouseExit() {
        isMouseOver = false;
        fxOnSlotmachine.stop();
        fxOffSlotmachine.play();
        repaint();
    }

    private void openSlotMachineWindow() {
        window = new SlotMachineWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void startAnimation(int[] values, Runnable onAnimationEnd) {
        if (window != null) {
            window.startAnimation(values, onAnimationEnd);
        }
        isMouseOver = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        if (isMouseOver) {
            width *= 1.1;
            height *= 1.1;
        }

        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;

        g.drawImage(slotMachineImage, x, y, width, height, this);
    }

    public void activateListeners() {
        addMouseListener(mouseAdapter);
    }

    public void deactivateListeners() {
        removeMouseListener(mouseAdapter);
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
