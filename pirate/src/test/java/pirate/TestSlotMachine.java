package pirate;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;

public class TestSlotMachine {
	private ControlSlotMachine csm = new ControlSlotMachine(null);
	@Test
	public void testFormat() {
		assertTrue(csm.spin().length == 3);
	}
	
	@Test
	public void testIntervalle() {
		int [] values = csm.spin();
		int lim_min = 0;
		int lim_max = 3;
		boolean res = true;
		for (int i = 0; i < values.length && res; i++) {
			int value = values[i];
			if(i >= 1){
				res = value >= (lim_min + 1) && value <= (lim_max + 1);
			}else{
				res = value >= (lim_min) && value < (lim_max);
			}
		}
		
		assertTrue(res);
	}

}
