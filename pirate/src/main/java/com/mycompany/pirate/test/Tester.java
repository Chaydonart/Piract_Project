package com.mycompany.pirate.test;

import java.util.HashMap;
import java.util.Map;

public abstract class Tester {
    private Map<String, Boolean> funcRes = new HashMap<>();

    public void results(){
        System.out.println( " executing tests for " + this.getClass().toString() );
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
    
}
