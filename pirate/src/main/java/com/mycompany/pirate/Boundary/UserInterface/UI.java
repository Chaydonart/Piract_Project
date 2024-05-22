/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pirate.Boundary.UserInterface;

import com.mycompany.pirate.Interfaces.IPirates;
import static com.mycompany.pirate.data.FileRef.FX_CHANGE_TURN;
import static com.mycompany.pirate.data.FileRef.OST_MAINTHEME;
import com.mycompany.pirate.data.SoundPlayer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 *
 * @author BEN JAAFAR
 */
public class UI extends JFrame implements IPirates {
    private boolean testingTurn = true;
    private int testDeplacer = 0;
    /*
    * Mettre tout les sons et les changer dans un SoundGestionnaire
    */
    private SoundPlayer mainTheme = new SoundPlayer(OST_MAINTHEME);
    private SoundPlayer changeTurn = new SoundPlayer(FX_CHANGE_TURN);

    public UI() {
        initComponents();        
        setLocationRelativeTo(null);
	setResizable(false);
        lifePanel2.setPlayer2();
        mainTheme.loop();
        mainTheme.play();

    }
    
    public void startGUI(){
        setVisible(true);
    }
    
    public void deplacerPion(int destinationCellNumber){
        gameBoardPanel1.deplacerPion(pionPanel1,destinationCellNumber);
    }
    
    public void setPanelClickListener(Runnable listener) {
        panelSlotButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.run();
            }
        });
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        pionPanel1 = new com.mycompany.pirate.Boundary.UserInterface.PionPanel();
        gameBoardPanel1 = new com.mycompany.pirate.Boundary.UserInterface.GameBoardPanel();
        panelSlotButton1 = new com.mycompany.pirate.Boundary.UserInterface.PanelSlotButton();
        jButton1 = new javax.swing.JButton();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        panelPlayer2 = new com.mycompany.pirate.Boundary.UserInterface.PanelPlayer();
        lifePanel1 = new com.mycompany.pirate.Boundary.UserInterface.LifePanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        panelPlayer21 = new com.mycompany.pirate.Boundary.UserInterface.PanelPlayer2();
        lifePanel2 = new com.mycompany.pirate.Boundary.UserInterface.LifePanel();

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

        jLayeredPane3.setBackground(new java.awt.Color(51, 204, 0));

        javax.swing.GroupLayout pionPanel1Layout = new javax.swing.GroupLayout(pionPanel1);
        pionPanel1.setLayout(pionPanel1Layout);
        pionPanel1Layout.setHorizontalGroup(
            pionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );
        pionPanel1Layout.setVerticalGroup(
            pionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelSlotButton1Layout = new javax.swing.GroupLayout(panelSlotButton1);
        panelSlotButton1.setLayout(panelSlotButton1Layout);
        panelSlotButton1Layout.setHorizontalGroup(
            panelSlotButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
        );
        panelSlotButton1Layout.setVerticalGroup(
            panelSlotButton1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
        );

        jButton1.setText("PLAYER TURN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLayeredPane3.setLayer(pionPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(gameBoardPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(panelSlotButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameBoardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146)
                .addComponent(panelSlotButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(272, 272, 272))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameBoardPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelSlotButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLayeredPane2.setBackground(new java.awt.Color(204, 255, 204));
        jLayeredPane2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout lifePanel1Layout = new javax.swing.GroupLayout(lifePanel1);
        lifePanel1.setLayout(lifePanel1Layout);
        lifePanel1Layout.setHorizontalGroup(
            lifePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );
        lifePanel1Layout.setVerticalGroup(
            lifePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelPlayer2Layout = new javax.swing.GroupLayout(panelPlayer2);
        panelPlayer2.setLayout(panelPlayer2Layout);
        panelPlayer2Layout.setHorizontalGroup(
            panelPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayer2Layout.createSequentialGroup()
                .addComponent(lifePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPlayer2Layout.setVerticalGroup(
            panelPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelPlayer2Layout.createSequentialGroup()
                .addGap(0, 450, Short.MAX_VALUE)
                .addComponent(lifePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLayeredPane2.add(panelPlayer2, java.awt.BorderLayout.PAGE_END);

        jLayeredPane1.setBackground(new java.awt.Color(0, 105, 91));
        jLayeredPane1.setForeground(new java.awt.Color(0, 105, 91));
        jLayeredPane1.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout lifePanel2Layout = new javax.swing.GroupLayout(lifePanel2);
        lifePanel2.setLayout(lifePanel2Layout);
        lifePanel2Layout.setHorizontalGroup(
            lifePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        lifePanel2Layout.setVerticalGroup(
            lifePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelPlayer21Layout = new javax.swing.GroupLayout(panelPlayer21);
        panelPlayer21.setLayout(panelPlayer21Layout);
        panelPlayer21Layout.setHorizontalGroup(
            panelPlayer21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lifePanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        panelPlayer21Layout.setVerticalGroup(
            panelPlayer21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlayer21Layout.createSequentialGroup()
                .addComponent(lifePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 450, Short.MAX_VALUE))
        );

        jLayeredPane1.add(panelPlayer21, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
            .addComponent(jLayeredPane2)
            .addComponent(jLayeredPane3, javax.swing.GroupLayout.Alignment.LEADING)
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        panelPlayer2.setTurn(testingTurn);
        panelPlayer21.setTurn(!testingTurn);
        testingTurn = !testingTurn;
        changeTurn.play();
        
        gameBoardPanel1.deplacerPion(pionPanel1, testDeplacer);
        testDeplacer++;

    }                                        

    // Variables declaration - do not modify                     
    private com.mycompany.pirate.Boundary.UserInterface.GameBoardPanel gameBoardPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private com.mycompany.pirate.Boundary.UserInterface.LifePanel lifePanel1;
    private com.mycompany.pirate.Boundary.UserInterface.LifePanel lifePanel2;
    private com.mycompany.pirate.Boundary.UserInterface.PanelPlayer panelPlayer2;
    private com.mycompany.pirate.Boundary.UserInterface.PanelPlayer2 panelPlayer21;
    public com.mycompany.pirate.Boundary.UserInterface.PanelSlotButton panelSlotButton1;
    private com.mycompany.pirate.Boundary.UserInterface.PionPanel pionPanel1;
    // End of variables declaration                   
}
