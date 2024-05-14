/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static Utilities.FileRef.BACKGROUND_IMAGE;
import static Utilities.values.GREEN_COLOR_BACKGROUND;
import java.awt.Color;
import java.awt.Image;
import static javax.swing.JLayeredPane.DEFAULT_LAYER;
/**
 *
 * @author BEN JAAFAR
 */
public class BackgroundPanel extends JPanel {
 private Image backgroundImage;

    public BackgroundPanel() {
        // Charger l'image de fond
        try {
            backgroundImage = ImageIO.read(new File(BACKGROUND_IMAGE)); 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        // Créer un panneau pour contenir l'image de fond
        JPanel backgroundPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Dessiner l'image de fond redimensionnée pour remplir le panneau
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                if (backgroundImage != null) {
                    return new Dimension(1280, 720);
                } else {
                    return super.getPreferredSize();
                }
            }
        };
        
        // Ajouter le panneau de fond à la couche de fond
        add(backgroundPane, DEFAULT_LAYER);
        setBackground(GREEN_COLOR_BACKGROUND);
    }

    @Override
    public Dimension getPreferredSize() {
        // La taille préférée du JLayeredPane sera la même que celle du panneau de fond
        return getComponent(0).getPreferredSize();
    }

}
