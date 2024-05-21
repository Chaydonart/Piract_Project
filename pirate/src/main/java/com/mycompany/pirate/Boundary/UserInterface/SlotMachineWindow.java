/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.FX_MACHINE_ROULETTE;
import com.mycompany.pirate.data.SoundPlayer;
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
    private SlotMachinePanel slotMachinePanel;
    private SoundPlayer machineRoulette = new SoundPlayer(FX_MACHINE_ROULETTE);
    private int[] valuesOfSlotPanel;
     

    public SlotMachineWindow() {
        machineRoulette.play();
        machineRoulette.loop();
        setSize(600, 300); 

        slotMachinePanel = new SlotMachinePanel(); 
        valuesOfSlotPanel = slotMachinePanel.getFinalValues();
        add(slotMachinePanel, BorderLayout.CENTER);
        
        ((SlotMachinePanel) slotMachinePanel).start(); // lancer l'animation de la slotmachine

        //On ferme la fenetre apres 3sec
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                machineRoulette.stop();
                machineRoulette.close();
            }
        });
        timer.setRepeats(false);
        timer.start(); 
    }
    public int[] getValuesOfSlotPanel() {
		return valuesOfSlotPanel;
	}
}
