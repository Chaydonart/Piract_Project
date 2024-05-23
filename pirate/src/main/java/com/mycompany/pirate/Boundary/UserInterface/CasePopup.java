package com.mycompany.pirate.Boundary.UserInterface;

import static com.mycompany.pirate.data.FileRef.FX_DAMAGE;
import static com.mycompany.pirate.data.FileRef.FX_POPUP_CASE;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JWindow;
import javax.swing.Timer;
import utils.SoundPlayer;

/**
 *
 * @author BEN JAAFAR
 */
public class CasePopup extends JWindow {
    private ImageIcon imageIcon;
    protected float alpha = 1f;
    private Timer timer;
    int imageSizeScaled = 250;
    private SoundPlayer popupSound = new SoundPlayer(FX_POPUP_CASE);

    public CasePopup(ImageIcon imageIcon, int displayTime) {
        this.imageIcon = imageIcon;

        setLayout(null); 

        timer = new Timer(displayTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fadeOut();
            }
        });
        timer.setRepeats(false);
        timer.start();

        // Taille de la popup basée sur la taille de l'image
        Dimension imageSize = new Dimension(imageSizeScaled,imageSizeScaled);
        setSize(imageSize.width, imageSize.height);
        setLocationRelativeTo(null); 
        setAlwaysOnTop(true); 
        setBackground(new Color(0, 0, 0, 0)); 
        setVisible(true); 
        popupSound.play();
    }

    private void fadeOut() {
        Timer animationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= 0.05f;
                if (alpha <= 0) {
                    alpha = 0;
                    ((Timer) e.getSource()).stop();
                    dispose(); // Ferme la popup une fois que le fondu est terminé
                }
                repaint();
            }
        });
        animationTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
    
        g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));

        Image image = imageIcon.getImage();
        int x = (getWidth() - imageSizeScaled) / 2;
        int y = (getHeight() - imageSizeScaled) / 2;
        g2d.drawImage(image,  x, y, imageSizeScaled, imageSizeScaled, this); // Ajuste la taille de l'image à la taille de la popup

        g2d.dispose();
    }
}
