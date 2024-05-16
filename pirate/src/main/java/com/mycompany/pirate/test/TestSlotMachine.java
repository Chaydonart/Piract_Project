package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControleSlotMachine;
import com.mycompany.pirate.Interfaces.IServiceSlotMachine;
import com.mycompany.pirate.Services.SlotMachineService;

public class TestSlotMachine extends Tester {
    private SlotMachineService issm = new SlotMachineService();
    private ControleSlotMachine csm = new ControleSlotMachine(issm);
    public TestSlotMachine() {
        super();
        this.getFuncRes().put("TestFormatCorrect",TestFormatCorrect());
    }

    public boolean TestFormatCorrect(){
        return csm.spin().length == 3;
    }

}

