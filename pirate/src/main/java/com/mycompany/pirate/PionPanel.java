/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author BEN JAAFAR
 */
public class PionPanel extends JPanel{
    private int initialX;
    private int initialY;
    private Color pionColor;
    public PionPanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialX = e.getX();
                initialY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - initialX;
                int deltaY = e.getY() - initialY;
                setLocation(getX() + deltaX, getY() + deltaY);
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(pionColor); // Choisissez la couleur du rond
        g.fillOval(0, 0, getWidth(), getHeight()); // Dessinez un rond
    }
    
    public void setColor(Color color){
        this.pionColor = color;
    }
    
}
