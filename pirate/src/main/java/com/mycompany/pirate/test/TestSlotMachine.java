package com.mycompany.pirate.test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.Interfaces.IServiceSlotMachine;
import com.mycompany.pirate.Services.SlotMachineService;

public class TestSlotMachine extends Tester {
    private SlotMachineService issm = new SlotMachineService();
    private ControlSlotMachine csm = new ControlSlotMachine(issm);
    public TestSlotMachine() {
        super();
        this.getFuncRes().put("TestFormatCorrect",() -> TestFormatCorrect());
        this.getFuncRes().put("TestDansInterval",() -> TestDansInterval());
    }

    public boolean TestFormatCorrect(){
        int values[] = csm.spin();
        for (int i = 0; i < values.length; i++) {
            System.out.println("value "+i+" => "+values[i]);
        }
        return csm.spin().length == 3;
    }
    public boolean TestDansInterval(){
        int values[] = csm.spin();
        int lim_max = 3;
        int lim_min = 0;
        boolean res = true;
        for (int i = 0; i < values.length; i++) {
            int val = values[i];
            if( i > 0){
                res = val <= (lim_max + 1) && val >= lim_min+1;
                System.out.println("interval -> ["+(lim_min+1)+","+(lim_max+1)+"]"+"| valeur -> "+val); 
            }
            else{
                res = val < lim_max && val >=lim_min;
                System.out.println("interval -> ["+lim_min+","+lim_max+"["+"| valeur -> "+val); 

            }
        }
        return res;
    }

}

