/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.BLANK;
import static com.mycompany.pirate.data.FileRef.FX_SAUT_PION;
import static com.mycompany.pirate.data.FileRef.POPUP_BOMBE;
import static com.mycompany.pirate.data.FileRef.POPUP_GAMBI;
import static com.mycompany.pirate.data.FileRef.POPUP_RECULER;
import static com.mycompany.pirate.data.FileRef.POPUP_REJOUER;
import static com.mycompany.pirate.data.values.GREEN_CUSTOM;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Arrays;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import utils.SoundPlayer;

/**
 * @author BEN JAAFAR & RIBEIRO
 * 
 * Panel qui trace un plateau avec un gridlayout 
 * Permet aussi de deplacer les pions grace aux coordonnes des cellules
 */
public class GameBoardPanel extends javax.swing.JPanel {
    private static final int PIECE_Y_OFFSET = -50; // Ajustement de la position verticale des pions
    private SoundPlayer fxSaut = new SoundPlayer(FX_SAUT_PION);
    private Map<Integer, ImageIcon> iconMap;

    public GameBoardPanel() {
         // Charger les icônes
        iconMap = loadIcons();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Configuration des contraintes pour chaque cellule
        gbc.insets = new Insets(1, 1, 1, 1); // Marges entre les cellules
        gbc.fill = GridBagConstraints.BOTH; // Redimensionnement complet des cellules
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        // Disposition des numéros de la roulette
        int[][] numbers = {
            { 3,  6,  9, 12, 15, 18, 21, 24, 27, 30, 33, 36 },
            { 2,  5,  8, 11, 14, 17, 20, 23, 26, 29, 32, 35 },
            { 1,  4,  7, 10, 13, 16, 19, 22, 25, 28, 31, 34 }
        };
        // Créer et ajouter les cellules du plateau de jeu en disposition roulette
        int rows = numbers.length;
        int cols = numbers[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                // Créer une cellule de plateau
                int cellNumber = numbers[row][col];
                ImageIcon icon = iconMap.get(cellNumber);
                CellPanel cell = new CellPanel(cellNumber,icon);

                add(cell, gbc);
            }
        }
        // Ajouter la cellule "0"
        gbc.gridx = 0;
        gbc.gridy = rows;
        gbc.gridwidth = cols;
        ImageIcon zeroIcon = iconMap.get(0);
        CellPanel zeroCell = new CellPanel(0, zeroIcon);
        add(zeroCell, gbc);
        setOpaque(false);
    }
    
    
    private Map<Integer, ImageIcon> loadIcons() {
        Map<Integer, ImageIcon> iconMap = new HashMap<>();             
        String imagePath;
        int iconSize = 15; // Taille souhaitée pour les icônes redimensionnées
        for (int i = 0; i < 37; i++) { // assuming you have 37 icons, one for each cell number
             switch (i) {
                case 3, 13, 21, 29, 34 -> imagePath = POPUP_BOMBE;
                case 7, 19, 31 -> imagePath = POPUP_GAMBI;
                case 8, 15, 23 -> imagePath = POPUP_REJOUER;
                case 9, 16, 24 -> imagePath = POPUP_RECULER;
                
                default -> imagePath = BLANK;
          }
         
                ImageIcon originalIcon = new ImageIcon(imagePath);
                Image originalImage = originalIcon.getImage();
                Image resizedImage = originalImage.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                iconMap.put(i, resizedIcon);
           
        }
        return iconMap;
    }
    
    public void deplacerPion(PionPanel pion, int destinationCellNumber, Runnable onAnimationEnd) {
        destinationCellNumber = Math.max(0, Math.min(destinationCellNumber, BOARD_SIZE - 1));
        int startCellNumber = pion.getCellPosition();
        List<Integer> path = createPath(startCellNumber, destinationCellNumber);
        animatePath(pion, path, 0, onAnimationEnd);
    }
    private List<Integer> createPath(int start, int end) {
        List<Integer> path = new java.util.ArrayList<>();
        if (start < end) {
            for (int i = start; i <= end; i++) {
                path.add(i);
            }
        } else {
            for (int i = start; i >= end; i--) {
                path.add(i);
            }
        }
        return path;
    }
    
    private void animatePath(PionPanel pion, List<Integer> path, int index, Runnable onAnimationEnd) {
        if (index >= path.size()) {
            if (onAnimationEnd != null) {
                onAnimationEnd.run();
            }
            return;
        }
        int cellNumber = path.get(index);
        for (Component component : getComponents()) {
            if (!(component instanceof CellPanel))
                continue;
            CellPanel cellPanel = (CellPanel) component;
            if (cellPanel.getCellNumber() == cellNumber) {
                int destinationX = cellPanel.getX() + cellPanel.getWidth() / 2;
                int destinationY = cellPanel.getY() + cellPanel.getHeight() / 2 - PIECE_Y_OFFSET;
                if (pion.player_number == 1)
                    destinationX -= 15;
                else if (pion.player_number == 2)
                    destinationX += 15;
                animatePionMovement(pion, destinationX, destinationY, cellNumber, () -> animatePath(pion, path, index + 1, onAnimationEnd));
                break;
            }
        }
    }
    private void animatePionMovement(PionPanel pion, int destinationX, int destinationY, int destinationCellNumber, Runnable onAnimationEnd) {
        int startX = pion.getX();
        int startY = pion.getY();
        int deltaX = destinationX - startX;
        int deltaY = destinationY - startY;
        int steps = 20; // - de steps = + de vitesse pour le pion
        int delay = 1; // délais entre les steps (1 par defaut pour pas dépasser d'autres evenements)
        double arcHeightFactor = 0.2; // ajuster la hauteur des sauts

        Timer timer = new Timer(delay, new ActionListener() {
            int step = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                double t = (double) step / steps;
                double arcX = startX + deltaX * t + Math.sin(Math.PI * t) * (Math.abs(deltaY) * arcHeightFactor);
                double arcY = startY + deltaY * t;
                pion.setLocation((int) arcX, (int) arcY);

                if (step >= steps) {
                    ((Timer) e.getSource()).stop();
                    pion.setLocation(destinationX, destinationY);
                    pion.setCellPosition(destinationCellNumber);
                    fxSaut.stop();
                    fxSaut.play();
                    Timer delayTimer = new Timer(10, (ActionEvent ev) -> {
                        if (onAnimationEnd != null) {
                            onAnimationEnd.run();
                        }
                    });
                    delayTimer.setRepeats(false);
                    delayTimer.start();
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }


    private class CellPanel extends JPanel {
        private int cellNumber;
        private ImageIcon icon;
        private static final List<Integer> RED_NUMBERS = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);
        public CellPanel(int cellNumber, ImageIcon icon) {


            setOpaque(false);
            this.icon = icon;

            this.cellNumber = cellNumber;
            this.setPreferredSize(new Dimension(80, 80)); // Taille préférée pour la cellule
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4)); // Bordure noire de 2 pixels
            // Déterminer la couleur de la cellule
            if (cellNumber == 0) {
                g2d.setColor(GREEN_CUSTOM);
            } else if (isRed(cellNumber)) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.BLACK);
            }
            // Dessiner le cercle
            int diameter = Math.min(getWidth(), getHeight()) - 10; // Ajuster pour les marges
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;
            g2d.fillOval(x, y, diameter, diameter);
            // Dessiner le numéro de la cellule
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 25));
            FontMetrics fm = g2d.getFontMetrics();
            String text = Integer.toString(cellNumber);
            int textX = x + (diameter - fm.stringWidth(text)) / 2;
            int textY = y + ((diameter - fm.getHeight()) / 2) + fm.getAscent();
            g2d.drawString(text, textX, textY);
            // Dessiner l'icône dans le coin inférieur droit
            if (icon != null) {
                int iconWidth = icon.getIconWidth();
                int iconHeight = icon.getIconHeight();
                int iconX = getWidth() - iconWidth - 5; // 5px padding from the edge
                int iconY = getHeight() - iconHeight - 5; // 5px padding from the edge
                icon.paintIcon(this, g2d, iconX, iconY);
            }

        }
        private boolean isRed(int number) {
            return RED_NUMBERS.contains(number);
        }
        public int getCellNumber() {
            return cellNumber;
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
