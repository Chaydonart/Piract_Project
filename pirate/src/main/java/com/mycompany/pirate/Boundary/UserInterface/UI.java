/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import com.mycompany.pirate.Interfaces.IPirates;
import static com.mycompany.pirate.data.FileRef.FX_CHANGE_TURN;
import static com.mycompany.pirate.data.FileRef.OST_MAINTHEME;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;
import utils.ScreenShake;
import utils.SoundPlayer;

/**
 *
 * @author BEN JAAFAR
 */
public class UI extends javax.swing.JFrame implements IPirates {
    private boolean turn = true;
    private boolean partieFini = false;
    /*
    * Mettre tout les sons et les changer dans un SoundGestionnaire
    */
    private SoundPlayer mainTheme = new SoundPlayer(OST_MAINTHEME);
    private SoundPlayer changeTurn = new SoundPlayer(FX_CHANGE_TURN);

    public UI() {
        initComponents();   
        initUIParameters();
        setLocationRelativeTo(null);
	setResizable(false);
    }
    
    private void initUIParameters(){
        //Pour changer l'image chargée (design)
        PanelLifePlayer2.setPlayer2();
        
        //Etat de base (c'est le joueur 1 qui commence)
        PanelDisplayerPlayer1.setTurn(true);
        PanelPion1.player_number = 1;
        PanelPion2.player_number = 2;
        
        //On met les bon niveau de layer 
        //LayeredPaneMain.setLayer(PanelPion1, javax.swing.JLayeredPane.POPUP_LAYER);
        //LayeredPaneMain.setLayer(PanelPion1, javax.swing.JLayeredPane.POPUP_LAYER);

        
        //On met nos pions sur la case 0 
        PanelGameboard.deplacerPion(PanelPion1, 0, null);
        PanelGameboard.deplacerPion(PanelPion2, 0, null);
        
        // On joue la musique
        mainTheme.loop();
        mainTheme.play();
        

    }
    
    public void startGUI(){
        setVisible(true);
    }
    
    //Permet de gerer le deplacerPion
    public void deplacerPion(int destinationCellNumber, String name){
        
        PionPanel currentPlayer = null;
        switch(name){
            case "Joueur 1":
                currentPlayer = PanelPion1;
                break;
            case "Joueur 2":
                currentPlayer = PanelPion2;
                break;
        }     
        int currentIndex = currentPlayer.getCellPosition();
        CountDownLatch latchAnimationEnd = new CountDownLatch(1);
        PanelGameboard.deplacerPion(currentPlayer,currentIndex + destinationCellNumber,() -> latchAnimationEnd.countDown());
        try {
            latchAnimationEnd.await();
            currentPlayer.setCellPosition(currentIndex + destinationCellNumber);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    //Permet de gerer la machine a sous son animation etc...
    public void spinMachine(int[] values) {
        CountDownLatch latchClick = new CountDownLatch(1);
        CountDownLatch latchAnimationEnd = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            try {
                // Code pour démarrer l'animation de la machine à sous
                setPanelClickListener(() -> latchClick.countDown());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            latchClick.await();  // Attend que le joueur appuie sur le bouton
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        PanelButtonSlotMachine.startAnimation(values, () -> latchAnimationEnd.countDown());

        try {
            latchAnimationEnd.await();  // Attend que l'animation soit terminée
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void finPartie() {
    	this.PanelButtonSlotMachine.setEnabled(false);
    }
    
    private void setPanelClickListener(Runnable listener) {
        PanelButtonSlotMachine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.run();
            }
        });
    }
    
    public void nouveauTour(){
        PanelDisplayerPlayer1.setTurn(turn);
        PanelDisplayPlayer2.setTurn(!turn);
        turn = !turn;
        changeTurn.play();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        LayeredPaneMain = new javax.swing.JLayeredPane();
        PanelPion1 = new com.mycompany.pirate.Boundary.UserInterface.PionPanel();
        PanelPion2 = new com.mycompany.pirate.Boundary.UserInterface.PionPanel();
        PanelPion2.setColor(Color.BLACK);
        PanelGameboard = new com.mycompany.pirate.Boundary.UserInterface.GameBoardPanel();
        PanelButtonSlotMachine = new com.mycompany.pirate.Boundary.UserInterface.PanelSlotButton();
        LayeredPanePlayer1 = new javax.swing.JLayeredPane();
        PanelDisplayerPlayer1 = new com.mycompany.pirate.Boundary.UserInterface.PanelPlayer1();
        PanleLifePlayer2 = new com.mycompany.pirate.Boundary.UserInterface.LifePanel();
        LayeredPanePlayer2 = new javax.swing.JLayeredPane();
        PanelDisplayPlayer2 = new com.mycompany.pirate.Boundary.UserInterface.PanelPlayer2();
        PanelLifePlayer2 = new com.mycompany.pirate.Boundary.UserInterface.LifePanel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LayeredPaneMain.setBackground(new java.awt.Color(51, 204, 0));

        PanelPion1.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout PanelPion1Layout = new javax.swing.GroupLayout(PanelPion1);
        PanelPion1.setLayout(PanelPion1Layout);
        PanelPion1Layout.setHorizontalGroup(
            PanelPion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        PanelPion1Layout.setVerticalGroup(
            PanelPion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        PanelPion2.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout PanelPion2Layout = new javax.swing.GroupLayout(PanelPion2);
        PanelPion2.setLayout(PanelPion2Layout);
        PanelPion2Layout.setHorizontalGroup(
            PanelPion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        PanelPion2Layout.setVerticalGroup(
            PanelPion2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelButtonSlotMachineLayout = new javax.swing.GroupLayout(PanelButtonSlotMachine);
        PanelButtonSlotMachine.setLayout(PanelButtonSlotMachineLayout);
        PanelButtonSlotMachineLayout.setHorizontalGroup(
            PanelButtonSlotMachineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );
        PanelButtonSlotMachineLayout.setVerticalGroup(
            PanelButtonSlotMachineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );

        LayeredPaneMain.setLayer(PanelPion1, javax.swing.JLayeredPane.POPUP_LAYER);
        LayeredPaneMain.setLayer(PanelPion2, javax.swing.JLayeredPane.POPUP_LAYER);
        LayeredPaneMain.setLayer(PanelGameboard, javax.swing.JLayeredPane.DEFAULT_LAYER);
        LayeredPaneMain.setLayer(PanelButtonSlotMachine, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout LayeredPaneMainLayout = new javax.swing.GroupLayout(LayeredPaneMain);
        LayeredPaneMain.setLayout(LayeredPaneMainLayout);
        LayeredPaneMainLayout.setHorizontalGroup(
            LayeredPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredPaneMainLayout.createSequentialGroup()
                .addComponent(PanelPion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221)
                .addComponent(PanelButtonSlotMachine, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelPion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(LayeredPaneMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelGameboard, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        LayeredPaneMainLayout.setVerticalGroup(
            LayeredPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredPaneMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LayeredPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayeredPaneMainLayout.createSequentialGroup()
                        .addComponent(PanelGameboard, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PanelButtonSlotMachine, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelPion1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelPion2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        LayeredPanePlayer1.setBackground(new java.awt.Color(204, 255, 204));
        LayeredPanePlayer1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout PanleLifePlayer2Layout = new javax.swing.GroupLayout(PanleLifePlayer2);
        PanleLifePlayer2.setLayout(PanleLifePlayer2Layout);
        PanleLifePlayer2Layout.setHorizontalGroup(
            PanleLifePlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        PanleLifePlayer2Layout.setVerticalGroup(
            PanleLifePlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelDisplayerPlayer1Layout = new javax.swing.GroupLayout(PanelDisplayerPlayer1);
        PanelDisplayerPlayer1.setLayout(PanelDisplayerPlayer1Layout);
        PanelDisplayerPlayer1Layout.setHorizontalGroup(
            PanelDisplayerPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDisplayerPlayer1Layout.createSequentialGroup()
                .addComponent(PanleLifePlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelDisplayerPlayer1Layout.setVerticalGroup(
            PanelDisplayerPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(PanelDisplayerPlayer1Layout.createSequentialGroup()
                .addGap(0, 450, Short.MAX_VALUE)
                .addComponent(PanleLifePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        LayeredPanePlayer1.add(PanelDisplayerPlayer1, java.awt.BorderLayout.PAGE_END);

        LayeredPanePlayer2.setBackground(new java.awt.Color(0, 105, 91));
        LayeredPanePlayer2.setForeground(new java.awt.Color(0, 105, 91));
        LayeredPanePlayer2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout PanelLifePlayer2Layout = new javax.swing.GroupLayout(PanelLifePlayer2);
        PanelLifePlayer2.setLayout(PanelLifePlayer2Layout);
        PanelLifePlayer2Layout.setHorizontalGroup(
            PanelLifePlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        PanelLifePlayer2Layout.setVerticalGroup(
            PanelLifePlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelDisplayPlayer2Layout = new javax.swing.GroupLayout(PanelDisplayPlayer2);
        PanelDisplayPlayer2.setLayout(PanelDisplayPlayer2Layout);
        PanelDisplayPlayer2Layout.setHorizontalGroup(
            PanelDisplayPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelLifePlayer2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        PanelDisplayPlayer2Layout.setVerticalGroup(
            PanelDisplayPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDisplayPlayer2Layout.createSequentialGroup()
                .addComponent(PanelLifePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 456, Short.MAX_VALUE))
        );

        LayeredPanePlayer2.add(PanelDisplayPlayer2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LayeredPanePlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LayeredPaneMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LayeredPanePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(LayeredPanePlayer1)
            .addComponent(LayeredPaneMain, javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(LayeredPanePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LayeredPaneMain;
    private javax.swing.JLayeredPane LayeredPanePlayer1;
    private javax.swing.JLayeredPane LayeredPanePlayer2;
    private com.mycompany.pirate.Boundary.UserInterface.PanelSlotButton PanelButtonSlotMachine;
    private com.mycompany.pirate.Boundary.UserInterface.PanelPlayer2 PanelDisplayPlayer2;
    private com.mycompany.pirate.Boundary.UserInterface.PanelPlayer1 PanelDisplayerPlayer1;
    private com.mycompany.pirate.Boundary.UserInterface.GameBoardPanel PanelGameboard;
    private com.mycompany.pirate.Boundary.UserInterface.LifePanel PanelLifePlayer2;
    private com.mycompany.pirate.Boundary.UserInterface.PionPanel PanelPion1;
    private com.mycompany.pirate.Boundary.UserInterface.PionPanel PanelPion2;
    private com.mycompany.pirate.Boundary.UserInterface.LifePanel PanleLifePlayer2;
    private javax.swing.JInternalFrame jInternalFrame1;
    // End of variables declaration//GEN-END:variables
}
