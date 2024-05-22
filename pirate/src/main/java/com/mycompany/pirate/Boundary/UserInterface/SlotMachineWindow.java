/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.FX_MACHINE_ROULETTE;
import utils.SoundPlayer;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.Timer;

/**
 *
 * @author BEN JAAFAR
 */
class SlotMachineWindow extends JWindow{
    private SoundPlayer fxMachineRoulette = new SoundPlayer(FX_MACHINE_ROULETTE);
    private Runnable endAnimation;

    public SlotMachineWindow() {
        fxMachineRoulette.play();
        fxMachineRoulette.loop();
        setSize(600, 300);   

        //On ferme la fenetre apres 3sec
        Timer timer = new Timer(3000, (ActionEvent e) -> {
            dispose();
            fxMachineRoulette.stop();
            fxMachineRoulette.close();
            endAnimation.run();
        });
        timer.setRepeats(false);
        timer.start(); 
    }
    
    
    public void startAnimation(int[] values, Runnable onAnimationEnd) {
        SlotMachinePanel slotMachinePanel = new SlotMachinePanel(values); 
        add(slotMachinePanel, BorderLayout.CENTER); 
        slotMachinePanel.startAnimation(values);
        this.endAnimation = onAnimationEnd;
    }

}
