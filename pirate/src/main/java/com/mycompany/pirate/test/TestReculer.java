//package com.mycompany.pirate.test;
//
//import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
//import com.mycompany.pirate.FonctionnalKernel.Controller.ControlReculer;
//import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
//import com.mycompany.pirate.Services.ServiceReculer;
//
//public class TestReculer extends Tester {
//    //initialisation
//    private ServiceReculer sr = new ServiceReculer(TestRes.controlDeplacerPion, TestRes.controlSlotMachine, TestRes.gameUI);
//    private ControlDeplacerPion cdp = TestRes.controlDeplacerPion;
//    private ControlSlotMachine csm = TestRes.controlSlotMachine;
//    private ControlReculer cr = new ControlReculer(TestRes.controlDeplacerPion, TestRes.controlSlotMachine, TestRes.gameUI);
//
//    public TestReculer() {
//        super();
//        this.getFuncRes().put("TestPosition", () -> TestPosition());
//
//
//    }
//
//    public boolean TestPosition(){
//        int oldPosition = cdp.getPionRepository().getPionActuel().getPosition();
//        sr.reculer();
//        int finalPosition = cdp.getPionRepository().getPionActuel().getPosition();
//        System.out.println("position initial etait -> "+ oldPosition + ",position final -> "+finalPosition);
//        return oldPosition >= oldPosition+sr.getDistanceRecule() || sr.getDistanceRecule() < 0;
//
//
//    }
//
//}
