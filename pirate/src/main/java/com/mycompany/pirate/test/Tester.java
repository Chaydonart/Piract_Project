package com.mycompany.pirate.test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class Tester {
    private Map<String, Supplier<Boolean>> funcRes = new HashMap<>();
    public Tester() {
        TestRes.gameLoopController.setGameUI(TestRes.gameUI);
        TestRes.gameUI.setGameLoopController(TestRes.gameLoopController);
        TestRes.controlDeplacerPion.setDeplacerPionService(TestRes.deplacerPionService);
        TestRes.plateau.initialiser(TestRes.controlDeplacerPion, TestRes.controlSlotMachine);
        TestRes.gameLoopController.setGameUI(TestRes.gameUI);
    }

    

    public Map<String, Supplier<Boolean>> getFuncRes() {
        return funcRes;
        
    }

    public void setFuncRes(Map<String, Supplier<Boolean>> funcRes) {
        this.funcRes = funcRes;
        
    }
    public void test(){
        System.out.println("execution des tests pour "+ this.getClass().getSimpleName()+":");
        System.out.println();
        this.funcRes.entrySet().stream().forEach( e -> System.out.println(
            e.getValue().get() ? e.getKey() + " passed" : e.getKey() + " failed"
        ));
        System.out.println("---------------------------------------------------------------------");
    }
    
}
