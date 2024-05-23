/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static com.mycompany.pirate.data.FileRef.IMAGE_LIFE_PLAYER_1;
import static com.mycompany.pirate.data.FileRef.IMAGE_LIFE_PLAYER_2;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author BEN JAAFAR
 */
public class LifePanel extends javax.swing.JPanel {
 private boolean isPlayer1 = true;
    private int viesRestantes = 5;
    private BufferedImage lifeImage;
    private final int lifeImageWidth = 30;
    private final int lifeImageHeight = 30;
    private LifeAnimation[] animations;

    public LifePanel() {
        loadImage();
        setDoubleBuffered(true);
        setOpaque(false);
        setPreferredSize(new Dimension(150, 50));
        startAnimations();
    }

    private void startAnimations() {
        animations = new LifeAnimation[viesRestantes];
        for (int i = 0; i < viesRestantes; i++) {
            animations[i] = new LifeAnimation(i * 100);
            animations[i].start();
        }
    }

    private void loadImage() {
        try {
            lifeImage = isPlayer1 ? ImageIO.read(new File(IMAGE_LIFE_PLAYER_1)) : ImageIO.read(new File(IMAGE_LIFE_PLAYER_2));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (lifeImage != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            int bottomY = getHeight() - 20;
            int imageSpacing = 5;
            // Ajustez le calcul de startX pour centrer les images
            int totalWidth = (lifeImageWidth + imageSpacing) * viesRestantes - imageSpacing;
            int startX = (getWidth() - totalWidth) / 2;

            for (int i = 0; i < viesRestantes; i++) {
                int imageX = startX + (lifeImageWidth + imageSpacing) * i;
                int imageY = bottomY - lifeImageHeight / 2 + animations[i].getYOffset();
                g2d.drawImage(lifeImage, imageX, imageY, lifeImageWidth, lifeImageHeight, this);
            }
        }
    }

    public void perdreVie() {
        if (viesRestantes > 0) {
            viesRestantes--;
            repaint();
        }
    }

    public void setPlayer2() {
        this.isPlayer1 = false;
        loadImage();
    }

    private class LifeAnimation extends Thread {
        private int yOffset = 0;
        private boolean running = true;

        public LifeAnimation(int delay) {
            setDaemon(true);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            double time = 0.0;
            double amplitude = 5.0; 
            double frequency = 0.02; 
            while (running) {
                try {
                    Thread.sleep(16); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                yOffset = (int) (amplitude * Math.sin(frequency * time)); 
                time += 1.0;
                repaint();
            }
        }

        public int getYOffset() {
            return yOffset;
        }

        public void stopAnimation() {
            running = false;
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
