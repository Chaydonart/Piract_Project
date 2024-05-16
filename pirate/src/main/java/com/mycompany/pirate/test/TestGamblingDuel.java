package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlGamblingDuel;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.CaseGambling;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.Interfaces.IServiceGamblingDuel;
import com.mycompany.pirate.Interfaces.IServiceSlotMachine;
import com.mycompany.pirate.Interfaces.NotificationService;
import com.mycompany.pirate.Services.ServiceGameblingDuel;

public class TestGamblingDuel extends Tester{
    private IServiceGamblingDuel gds = new ServiceGameblingDuel();
    private ControlGamblingDuel cgd = new ControlGamblingDuel(gds);

    public TestGamblingDuel() {
        super();
        this.getFuncRes().put("TestDuelDeDes",() -> this.TestDuelDeDes());
    }

    public boolean TestDuelDeDes(){
        int oldPointDeVie = TestRes.joueur1.getVie();
        cgd.duelDeDes(TestRes.joueur1,TestRes.gameUI);
        return TestRes.joueur1.getVie()<=oldPointDeVie;

        
    }



    
}
