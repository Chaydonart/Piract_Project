package pirate;

import static org.junit.Assert.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlReculer;
import com.mycompany.pirate.FonctionnalKernel.Controller.ControlSlotMachine;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;

public class TestReculer {
	private PionRepository pr =  new PionRepository(IntStream.iterate(1,nb -> nb++ ).limit(2).mapToObj(nb -> new Pion(String.valueOf(nb))).collect(Collectors.toList()));
	private Plateau  plateau = new Plateau(36, null);
	private ControlDeplacerPion cdp = new ControlDeplacerPion(plateau, null, pr);
	private ControlSlotMachine csm =  new ControlSlotMachine(null) {
		@Override
		public int[] spin() {
			// TODO Auto-generated method stub
			return IntStream.generate(()->1).limit(3).toArray();
		}
	}; 
	private ControlReculer cr =  new ControlReculer(cdp, csm, null);
	@Test
	public void testReculer() {
		plateau.initialiser(cdp, csm);
		Pion pc = pr.getPionActuel();
		cdp.deplacerPion(pc, 21);
		int oldPos = pc.getPosition();
		cr.reculer(pc);
		assertTrue("oldPos => "+oldPos + " currentPos => "+pc.getPosition(), oldPos +cr.getDistanceRecule() == pc.getPosition());
		
		
	}

}
