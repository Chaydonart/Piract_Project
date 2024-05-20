package pirate;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlGamblingDuel;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;

public class TestGamblingDuel {
	private ControlSlotMachine csm =  new ControlSlotMachine(null);
	private ControlGamblingDuel cgd =  new ControlGamblingDuel(csm);
	

	@Test
	public void testGamblingDuel() {
		Pion pt = new Pion("Test");
		int oldHP = pt.getVie();
		int resultGamble = cgd.duelDeDes(pt, null);
		assertTrue(pt.getVie() == oldHP + resultGamble);
	}

}
