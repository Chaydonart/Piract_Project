package com.mycompany.pirate.test;

import java.util.ArrayList;
import java.util.List;

public class TestAll {
    private List<Tester> tests = new ArrayList<>();
    public TestAll() {
        this.tests.add(new TestDeplacerPion());
        this.tests.add(new TestGamblingDuel());
        this.tests.add(new TestRejouer());
        this.tests.add(new TestSlotMachine());
        
        this.tests.stream().forEach( t -> t.test());
    }
    public static void main(String[] args) {
        TestAll testAll = new TestAll();
        
    }
    
}
