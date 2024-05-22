/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author BEN JAAFAR
 * 
 * Effet qui permet de shake un Panel (animation)
 */
public class ScreenShake {
      public static void shakePanel(JPanel panel, int shakeDuration, int shakeDistance) {
        Point originalLocation = panel.getLocation();    
        int shakePeriod = 20; 
        int shakeCount = shakeDuration / shakePeriod;

        Timer timer = new Timer(shakePeriod, new ActionListener() {
            int count = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                double angle = Math.random() * 2 * Math.PI;
                int deltaX = (int) (Math.sin(angle) * shakeDistance);
                int deltaY = (int) (Math.cos(angle) * shakeDistance);
                panel.setLocation(originalLocation.x + deltaX, originalLocation.y + deltaY);

                count++;
                if (count >= shakeCount) {
                    ((Timer) e.getSource()).stop();
                    panel.setLocation(originalLocation);
                }
            }
        });

        timer.start();
    }
}
