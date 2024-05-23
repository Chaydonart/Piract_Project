/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_1;
import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_1_DAMAGE;
import static com.mycompany.pirate.data.FileRef.IMAGE_PLAYER_1_VICTORY;
import static com.mycompany.pirate.data.FileRef.OST_MAINTHEME;
import static com.mycompany.pirate.data.values.RED_CUSTOM;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.Timer;
import utils.SoundPlayer;

/**
 *
 * @author BEN JAAFAR
 */
public class PanelPlayer1 extends PanelPlayerDisplay {
    private double imageY = 0;
    private double imageSpeed = 0.2;
    private boolean movingDown = true;

    public PanelPlayer1() {
        loadImages();
        setPreferredSize(new Dimension(triangleBase, triangleHeight));
        updateImage();
        colorBackground = RED_CUSTOM;
        if(playerImage != null){
            imageY = triangleHeight - (triangleBase - 20) * ((double) playerImage.getHeight() / playerImage.getWidth()) - 10;
        }
        startAnimation();
    }

    private void loadImages() {
        idleImage = loadImageFromFile(IMAGE_PLAYER_1);
        victoryImage = loadImageFromFile(IMAGE_PLAYER_1_VICTORY);
        damageImage = loadImageFromFile(IMAGE_PLAYER_1_DAMAGE);
    }

    @Override
    public void startAnimation() {
        Timer timer = new Timer(20, e -> {
            if (playerImage != null && playerImage.getWidth() != 0) {
                int imageWidth = triangleBase - 20;
                int imageHeight = playerImage.getHeight() != 0 ? (int) ((double) playerImage.getHeight() / playerImage.getWidth() * imageWidth) : 0;

                if (imageHeight > triangleHeight - 20) {
                    imageHeight = triangleHeight - 20;
                    imageWidth = (int) ((double) playerImage.getWidth() / playerImage.getHeight() * imageHeight);
                }

                if (movingDown) {
                    imageY += imageSpeed;
                    if (imageY >= triangleHeight - imageHeight) {
                        movingDown = false;
                    }
                } else {
                    imageY -= imageSpeed;
                    if (imageY <= 150) {
                        movingDown = true;
                    }
                }

                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int[] xPoints = {0, triangleBase, 0};
        int[] yPoints = {0, triangleHeight, triangleHeight};
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g2d.setColor(turnColor);
        g2d.fillPolygon(triangle);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawPolygon(triangle);

        if (playerImage != null) {
            int imageWidth = triangleBase - 20;
            int imageHeight = (int) ((double) playerImage.getHeight() / playerImage.getWidth() * imageWidth);

            if (imageHeight > triangleHeight - 20) {
                imageHeight = triangleHeight - 20;
                imageWidth = (int) ((double) playerImage.getWidth() / playerImage.getHeight() * imageHeight);
            }

            int imageX = (triangleBase - imageWidth) / 2 - 10;
            int imageYInt = (int) imageY;

            g2d.setClip(triangle);
            g2d.drawImage(playerImage, imageX, imageYInt, imageWidth, imageHeight, this);
            g2d.setClip(null);
        }

        g2d.dispose();
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}