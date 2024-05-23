/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.POPUP_BOMBE;
import static com.mycompany.pirate.data.FileRef.POPUP_GAMBI;
import static com.mycompany.pirate.data.FileRef.POPUP_RECULER;
import static com.mycompany.pirate.data.FileRef.POPUP_REJOUER;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 *
 * @author BEN JAAFAR
 * 
 * Permet de ne pas avoir a chager les images a chaque fois et de juste directement 
 * afficher la popup, les images etant toujours chargées
 */
public class CasePopupManager {
    private ImageIcon iconBomb;
    private ImageIcon iconRejouer;
    private ImageIcon iconReculer;
    private ImageIcon iconGambi;
    private JWindow textPopup;

    public CasePopupManager() {
        iconBomb = new ImageIcon(POPUP_BOMBE);
        iconRejouer = new ImageIcon(POPUP_REJOUER);
        iconReculer = new ImageIcon(POPUP_RECULER);
        iconGambi = new ImageIcon(POPUP_GAMBI);
    }

    public void popupCaseBomb() {
        new CasePopup(iconBomb, 500);
    }

    public void popupCaseRejouer() {
        new CasePopup(iconRejouer, 1000);
    }

    public void popupCaseReculer() {
        new CasePopup(iconReculer, 1000);
    }

    public void popupCaseGambi() {
        new CasePopup(iconGambi, 1000);
    }

    public void showPopup(JFrame parent, String message) {
        textPopup = new JWindow();

        JLabel messageLabel = new JLabel(message);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajout de marges pour l'esthétique

        Dimension labelSize = messageLabel.getPreferredSize();

        textPopup.setSize(labelSize.width + 20, labelSize.height + 20);

        int x = parent.getX() + (parent.getWidth() - textPopup.getWidth()) / 2;

        int y = parent.getY() + parent.getInsets().top;

        textPopup.setLocation(x, y);
        textPopup.add(messageLabel);

        // Ajout d'un WindowListener pour fermer la popup lorsque la fenêtre parente est fermée
        parent.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                textPopup.dispose(); // Fermer la popup lorsque la fenêtre parente est fermée
            }
        });

        textPopup.setVisible(true);
    }

    public void closePopup() {
        // Fermer la popup en la rendant invisible et en la libérant de la mémoire
        if (textPopup != null) {
            textPopup.setVisible(false);
            textPopup.dispose();
        }
    }
}
