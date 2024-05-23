/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import com.mycompany.pirate.Boundary.UserInterface.PanelPlayerDisplay.PlayerState;
import com.mycompany.pirate.Interfaces.IPirates;
import static com.mycompany.pirate.data.FileRef.FX_CHANGE_TURN;
import static com.mycompany.pirate.data.FileRef.FX_DAMAGE;
import static com.mycompany.pirate.data.FileRef.FX_GAMBLING_DUEL_VICTORY;
import static com.mycompany.pirate.data.FileRef.OST_MAINTHEME;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;
import utils.ScreenShake;
import utils.SoundPlayer;

/**
 *
 * @author BEN JAAFAR
 */
public class UI extends javax.swing.JFrame implements IPirates {
    private boolean testingTurn = true;
    private Map<String, LifePanel> lifePanels;
    private Map<String, PanelPlayerDisplay> displayPanels;
    private Map<String, PionPanel> pionPanels;
    private CasePopupManager casePopupManager = new CasePopupManager();

    /*
    * Put all the sounds and change them in a SoundManager
    */
    private SoundPlayer mainTheme = new SoundPlayer(OST_MAINTHEME);
    private SoundPlayer changeTurn = new SoundPlayer(FX_CHANGE_TURN);
    private SoundPlayer takeDamage = new SoundPlayer(FX_DAMAGE);
    private SoundPlayer gamblingDuelVictory = new SoundPlayer(FX_GAMBLING_DUEL_VICTORY);
    

    public UI() {
        initComponents();   
        initUIParameters();
        setLocationRelativeTo(null);
        setResizable(false);
        
        this.lifePanels = Map.of(
                "Joueur 1", PanelLifePlayer1,
                "Joueur 2", PanelLifePlayer2
        );
        this.displayPanels = Map.of(
                "Joueur 1", PanelDisplayerPlayer1,
                "Joueur 2", PanelDisplayPlayer2
        );
        this.pionPanels = Map.of(
                "Joueur 1", PanelPion1,
                "Joueur 2", PanelPion2
        );
    }

    private void initUIParameters() {
        // To change the loaded image (design)
        PanelLifePlayer2.setPlayer2();

        // Default state (Player 1 start)
        PanelDisplayerPlayer1.setTurn(true);
        PanelPion1.player_number = 1;
        PanelPion2.player_number = 2;

        // Place the pieces on cell 0
        PanelGameboard.deplacerPion(PanelPion1, 0, null);
        PanelGameboard.deplacerPion(PanelPion2, 0, null);

        // Play the music
        mainTheme.loop();
        mainTheme.play();
    }
    
    @Override
    public void startGUI() {
        setVisible(true);
    }

    @Override
    public void movePiece(int destinationCellNumber, String name) {
        PionPanel currentPlayer = pionPanels.get(name);
        if (currentPlayer != null) {
            int currentIndex = currentPlayer.getCellPosition();
            CountDownLatch latchAnimationEnd = new CountDownLatch(1);
            PanelGameboard.deplacerPion(currentPlayer, currentIndex + destinationCellNumber, () -> latchAnimationEnd.countDown());
            try {
                latchAnimationEnd.await();
                currentPlayer.setCellPosition(currentIndex + destinationCellNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Manage the slot machine, its animation, etc.
    public void spinMachine(int[] values) {
        CountDownLatch latchClick = new CountDownLatch(1);
        CountDownLatch latchAnimationEnd = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            try {
                // Code to start the slot machine animation
                setPanelClickListener(() -> latchClick.countDown());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            latchClick.await();  
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        PanelButtonSlotMachine.startAnimation(values, () -> latchAnimationEnd.countDown());

        try {
            latchAnimationEnd.await();
            PanelButtonSlotMachine.activateListeners();
            // Wait for the animation to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void newTurn() {
        PanelDisplayerPlayer1.setTurn(testingTurn);
        PanelDisplayPlayer2.setTurn(!testingTurn);
        testingTurn = !testingTurn;
        changeTurn.play();
    }

    public void endGame() {
        this.PanelButtonSlotMachine.setEnabled(false);
        this.PanelButtonSlotMachine.deactivateListeners();
        mainTheme.stop();
        // Add a victory window
        
    }

    public void takeDamage(String name) {
        displayPanels.get(name).setState(PlayerState.DAMAGE);
        ScreenShake shakeEffect = new ScreenShake();
        if (lifePanels.containsKey(name) && displayPanels.containsKey(name)) {
            lifePanels.get(name).perdreVie();
            shakeEffect.shakePanel(displayPanels.get(name), 200, 50);
            takeDamage.play();
        }
    }

    public void gamblingDuelResult(String name, boolean win) {
        if(win) {
           gamblingDuelVictory.play();
           displayPanels.get(name).setState(PlayerState.VICTORY);
        } else {
           takeDamage(name);
        }
        casePopupManager.closePopup();
    }
    
    public void caseBombe(){
       casePopupManager.popupCaseBomb();
    }
    
    public void caseRejouer(String name){
       casePopupManager.popupCaseRejouer();
       displayPanels.get(name).setState(PlayerState.VICTORY);
    }
    
    public void caseReculer(){
       casePopupManager.popupCaseReculer();
    }
    
    public void caseGambling(int value){
       casePopupManager.popupCaseGambi();
       casePopupManager.showPopup(this,"RELANCE POUR TENTER DE FAIRE AU DESSUS OU EGALE A " + value);
    }
    
    private void setPanelClickListener(Runnable listener) {
        PanelButtonSlotMachine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.run();
                PanelButtonSlotMachine.deactivateListeners();
                PanelDisplayerPlayer1.setState(PlayerState.IDLE);
                PanelDisplayPlayer2.setState(PlayerState.IDLE);
            }
        });
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
        PanelLifePlayer1 = new com.mycompany.pirate.Boundary.UserInterface.LifePanel();
        LayeredPanePlayer2 = new javax.swing.JLayeredPane();
        PanelDisplayPlayer2 = new com.mycompany.pirate.Boundary.UserInterface.PanelPlayer2();
        PanelLifePlayer2 = new com.mycompany.pirate.Boundary.UserInterface.LifePanel();
        jPanel1 = new javax.swing.JPanel();

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
        LayeredPaneMain.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 10, 0, new java.awt.Color(0, 0, 0)));

        PanelPion1.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout PanelPion1Layout = new javax.swing.GroupLayout(PanelPion1);
        PanelPion1.setLayout(PanelPion1Layout);
        PanelPion1Layout.setHorizontalGroup(
            PanelPion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        PanelPion1Layout.setVerticalGroup(
            PanelPion1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
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
                .addContainerGap()
                .addGroup(LayeredPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LayeredPaneMainLayout.createSequentialGroup()
                        .addComponent(PanelPion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PanelButtonSlotMachine, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(211, 211, 211)
                        .addComponent(PanelPion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LayeredPaneMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelGameboard, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        LayeredPaneMainLayout.setVerticalGroup(
            LayeredPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredPaneMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelGameboard, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LayeredPaneMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LayeredPaneMainLayout.createSequentialGroup()
                        .addComponent(PanelPion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(PanelPion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PanelButtonSlotMachine, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        LayeredPanePlayer1.setBackground(new java.awt.Color(204, 255, 204));
        LayeredPanePlayer1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout PanelLifePlayer1Layout = new javax.swing.GroupLayout(PanelLifePlayer1);
        PanelLifePlayer1.setLayout(PanelLifePlayer1Layout);
        PanelLifePlayer1Layout.setHorizontalGroup(
            PanelLifePlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        PanelLifePlayer1Layout.setVerticalGroup(
            PanelLifePlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelDisplayerPlayer1Layout = new javax.swing.GroupLayout(PanelDisplayerPlayer1);
        PanelDisplayerPlayer1.setLayout(PanelDisplayerPlayer1Layout);
        PanelDisplayerPlayer1Layout.setHorizontalGroup(
            PanelDisplayerPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDisplayerPlayer1Layout.createSequentialGroup()
                .addComponent(PanelLifePlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelDisplayerPlayer1Layout.setVerticalGroup(
            PanelDisplayerPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(PanelDisplayerPlayer1Layout.createSequentialGroup()
                .addGap(0, 450, Short.MAX_VALUE)
                .addComponent(PanelLifePlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        LayeredPanePlayer1.add(PanelDisplayerPlayer1, java.awt.BorderLayout.PAGE_END);

        LayeredPanePlayer2.setBackground(new java.awt.Color(0, 105, 91));
        LayeredPanePlayer2.setForeground(new java.awt.Color(0, 105, 91));
        LayeredPanePlayer2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout PanelLifePlayer2Layout = new javax.swing.GroupLayout(PanelLifePlayer2);
        PanelLifePlayer2.setLayout(PanelLifePlayer2Layout);
        PanelLifePlayer2Layout.setHorizontalGroup(
            PanelLifePlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        PanelLifePlayer2Layout.setVerticalGroup(
            PanelLifePlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelDisplayPlayer2Layout = new javax.swing.GroupLayout(PanelDisplayPlayer2);
        PanelDisplayPlayer2.setLayout(PanelDisplayPlayer2Layout);
        PanelDisplayPlayer2Layout.setHorizontalGroup(
            PanelDisplayPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDisplayPlayer2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelLifePlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        );
        PanelDisplayPlayer2Layout.setVerticalGroup(
            PanelDisplayPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDisplayPlayer2Layout.createSequentialGroup()
                .addComponent(PanelLifePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 456, Short.MAX_VALUE))
        );

        LayeredPanePlayer2.add(PanelDisplayPlayer2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LayeredPanePlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(LayeredPaneMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(LayeredPanePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(LayeredPanePlayer1)
            .addComponent(LayeredPaneMain, javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(LayeredPanePlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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
    private com.mycompany.pirate.Boundary.UserInterface.LifePanel PanelLifePlayer1;
    private com.mycompany.pirate.Boundary.UserInterface.LifePanel PanelLifePlayer2;
    private com.mycompany.pirate.Boundary.UserInterface.PionPanel PanelPion1;
    private com.mycompany.pirate.Boundary.UserInterface.PionPanel PanelPion2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
