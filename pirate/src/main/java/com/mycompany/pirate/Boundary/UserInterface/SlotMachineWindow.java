/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 *
 * @author BEN JAAFAR
 */
class SlotMachineWindow extends JWindow{
     private final JPanel slotMachinePanel;

    public SlotMachineWindow() {
        setSize(600, 300); // Taille de la fenêtre de la slot machine

        slotMachinePanel = new SlotMachinePanel(); // Utilisez le SlotMachinePanel que vous avez déjà créé
        add(slotMachinePanel, BorderLayout.CENTER);
        
        // Lancer automatiquement l'animation de la slot machine
        ((SlotMachinePanel) slotMachinePanel).start();

        // Ajouter un écouteur pour fermer la fenêtre après un délai
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre
            }
        });
        timer.setRepeats(false); // Ne répétez pas le délai
        timer.start(); // Démarrer le décompte
    }
}
