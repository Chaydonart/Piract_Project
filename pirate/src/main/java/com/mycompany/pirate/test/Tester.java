package com.mycompany.pirate.test;

import java.util.HashMap;
import java.util.Map;

public abstract class Tester {
    /*private Map<String, Boolean> funcRes = new HashMap<>();
    public Tester() {
        TestRes.gameLoopController.setGameUI(TestRes.gameUI);
        TestRes.gameUI.setGameLoopController(TestRes.gameLoopController);
        TestRes.controlDeplacerPion.setDeplacerPionService(TestRes.deplacerPionService);
        TestRes.plateau.initialiser(TestRes.controlDeplacerPion, TestRes.controlSlotMachine);
        TestRes.gameLoopController.setGameUI(TestRes.gameUI);
    }

    public void results(){
        System.out.println( " executing tests for " + this.getClass() );
        for (Map.Entry<String,Boolean> entry : getFuncRes().entrySet()) {
            if(entry.getValue()){
                System.out.println(entry.getKey() + " passed");
            }
            else{
                System.out.println(entry.getKey() + " failed");
            }
            
        }
    }

    public Map<String, Boolean> getFuncRes() {
        return funcRes;
        
    }

    public void setFuncRes(Map<String, Boolean> funcRes) {
        this.funcRes = funcRes;
        
    }
    public void test(){
        System.out.println("execution des tests pour "+ this.getClass().getSimpleName()+":");
        System.out.println();
        this.funcRes.entrySet().stream().forEach( e -> System.out.println(
            e.getValue() ? e.getKey() + " passed" : e.getKey() + " failed"
        ));
        System.out.println("---------------------------------------------------------------------");
    }
    */
}
