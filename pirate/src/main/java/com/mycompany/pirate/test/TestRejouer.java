package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlRejouer;
import com.mycompany.pirate.Interfaces.NotificationService;
import com.mycompany.pirate.Services.ServiceRejouer;

public class TestRejouer extends Tester {
    private ControlRejouer cr = new ControlRejouer(TestRes.controlDeplacerPion, TestRes.controlSlotMachine, TestRes.gameUI);

    private ServiceRejouer sr = new ServiceRejouer(TestRes.controlDeplacerPion, TestRes.controlSlotMachine, TestRes.gameUI);
    public TestRejouer() {
        super();
        this.getFuncRes().put("TestRejouerPion",() -> TestRejouerPion());
    }


    public boolean TestRejouerPion(){
        sr.rejouer();
        // ça veut deire qu ç'a augmenté
        System.out.println(TestRes.controlSlotMachine.getCompteurSpin());
        return  sr.getDistanceRejoue() != 0;
    }

    
}
