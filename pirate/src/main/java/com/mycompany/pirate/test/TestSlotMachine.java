package com.mycompany.pirate.test;

import java.util.Arrays;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.Interfaces.IServiceSlotMachine;
import com.mycompany.pirate.Services.SlotMachineService;

public class TestSlotMachine extends Tester {
    private SlotMachineService issm = new SlotMachineService();
    private ControlSlotMachine csm = new ControlSlotMachine(issm);
    public TestSlotMachine() {
        super();
        this.getFuncRes().put("TestFormatCorrect",() -> TestFormatCorrect());
        this.getFuncRes().put("TestDansIntervalle",() -> TestDansIntervalle());

    }

    public boolean TestFormatCorrect(){
        int values[] = csm.spin();
        StringBuilder sb = new StringBuilder();
        sb.append("machine donne ->{");
        Arrays.stream(values).forEach( value -> sb.append(value+","));
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        System.out.println(sb.toString());
        return values.length == 3;
    }

    public boolean TestDansIntervalle(){
        int values[] = csm.spin();
        int lim_min = 0;
        int lim_max = 3;
        boolean res = true;
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            if(i >= 1){
                System.out.println("intervalle -> [" + (lim_min + 1)+","+(lim_max + 1) + "], valeur -> " + value);
                res = value >= (lim_min + 1) && value <= (lim_max + 1);
            }else{
                System.out.println("intervalle -> [" + (lim_min)+","+(lim_max) + "[, valeur -> " + value);
                res = value >= (lim_min) && value < (lim_max);
            }
        }
        return res;

    }

}

