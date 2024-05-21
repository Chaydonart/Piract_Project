/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.FX_CLICK;
import static com.mycompany.pirate.data.FileRef.FX_OFF_SLOTMACHINE;
import static com.mycompany.pirate.data.FileRef.FX_ON_SLOTMACHINE;
import static com.mycompany.pirate.data.FileRef.IMAGE_SLOT_MACHINE;
import com.mycompany.pirate.data.SoundPlayer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.ImageIcon;

/**
 *
 * @author BEN JAAFAR
 * Panel faisant office de bouton pour lancer la machine a sous
 */
public class PanelSlotButton extends javax.swing.JPanel {
    private Image slotMachineImage;
    private SoundPlayer fxOnSlotmachine = new SoundPlayer(FX_ON_SLOTMACHINE);
    private SoundPlayer fxOffSlotmachine = new SoundPlayer(FX_OFF_SLOTMACHINE);
    private SoundPlayer fxClick = new SoundPlayer(FX_CLICK);
    private boolean isMouseOver = false;
    private int[] slotValues;
    
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
            
            /*
            * Gere l'animation interactive 
            */
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
        // Charge l'image de la machine à sous (remplacez "slot_machine.png" par le chemin de votre image)
        slotMachineImage = new ImageIcon(IMAGE_SLOT_MACHINE).getImage();
    }


    private void openSlotMachineWindow() {
        SlotMachineWindow window = new SlotMachineWindow();
        window.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        window.setVisible(true);
        this.slotValues = window.getValuesOfSlotPanel();
        
//        pour tester l'obtention des valeurs de SlotMachine
//        System.out.println("machine gave :");
//        Arrays.stream(this.slotValues).forEach(System.out::print);
//        System.out.println();
    }
    public int[] getSlotValues() {
		return slotValues;
	}
    
    
    @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        
        /*
        * Si la souris est sur l'image on l'agrandis
        */
        if (isMouseOver) {
            width = (int)(width * 1.1);
            height = (int)(height * 1.1);
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

	public void setSlotValues(int[] values) {
		// TODO Auto-generated method stub
		this.slotValues = null;
		
	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
