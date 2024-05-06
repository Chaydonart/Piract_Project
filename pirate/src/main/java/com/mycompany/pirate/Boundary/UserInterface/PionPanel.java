/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
            
           @Override
public void mouseReleased(MouseEvent e) {
    // Obtenez les coordonnées du relâchement de la souris
    Point releasePoint = e.getPoint();
    // Convertissez les coordonnées du PionPanel dans le coordinateur de son parent
    Point convertedReleasePoint = SwingUtilities.convertPoint(PionPanel.this, releasePoint, getParent());
    
    // Vérifiez si le PionPanel a été relâché dans le CasePanel
    if (getParent() instanceof CustomLayeredPanel && getParent().getBounds().contains(convertedReleasePoint)) {
        System.out.println("PionPanel déposé sur CasePanel !");
    }
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
