package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlRejouer;
import com.mycompany.pirate.Interfaces.NotificationService;
import com.mycompany.pirate.Services.ServiceRejouer;

public class TestRejouer extends Tester {
    private ControlRejouer cr = new ControlRejouer(TestRes.controlDeplacerPion, TestRes.controlSlotMachine, new NotificationService() {
        public void notify(String message) {};
    });

    private ServiceRejouer sr = new ServiceRejouer(TestRes.controlDeplacerPion, TestRes.controlSlotMachine, new NotificationService() {
        public void notify(String message) {};
    });
    public TestRejouer() {
        super();
        this.getFuncRes().put("TestRejouerPion",TestRejouerPion());
    }


    public boolean TestRejouerPion(){
        cr.rejouer();
        // ça veut deire qu ç'a augmenté
        return TestRes.controlSlotMachine.getCompteurSpin() == 1;
    }

    
}
