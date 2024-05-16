package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlRejouer;
import com.mycompany.pirate.Services.ServiceRejouer;
import com.mycompany.pirate.Interfaces.INotificationService;

public class TestRejouer extends Tester {
    private ControlRejouer cr = new ControlRejouer(TestRes.controlDeplacerPion, TestRes.controlSlotMachine, TestRes.gameUI);

    private ServiceRejouer sr = new ServiceRejouer(TestRes.controlDeplacerPion, TestRes.controlSlotMachine, TestRes.gameUI);
    public TestRejouer() {
        super();
        this.getFuncRes().put("TestRejouerPion",() -> TestRejouerPion());
    }


    public boolean TestRejouerPion(){
        cr.rejouer();
        // ça veut deire qu ç'a augmenté
        return TestRes.controlSlotMachine.getCompteurSpin() >= 1;
    }

    
}
