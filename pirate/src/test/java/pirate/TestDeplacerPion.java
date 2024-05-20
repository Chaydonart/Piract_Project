package pirate;

import static org.junit.Assert.*;


import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import com.mycompany.pirate.FonctionnalKernel.Controller.ControlDeplacerPion;
import com.mycompany.pirate.FonctionnalKernel.Entity.Pion;
import com.mycompany.pirate.FonctionnalKernel.Entity.PionRepository;
import com.mycompany.pirate.FonctionnalKernel.Entity.Plateau;

public class TestDeplacerPion {
	private PionRepository pr =  new PionRepository(IntStream.iterate(1,nb -> nb++ ).limit(2).mapToObj(nb -> new Pion(String.valueOf(nb))).collect(Collectors.toList()));
	private Plateau  plateau = new Plateau(36, null);
	private ControlDeplacerPion cdp = new ControlDeplacerPion(plateau, null, pr);
	

	@Test
	public void testDeplacerPion() {
		plateau.initialiser(cdp, null);
		Pion pc = pr.getPionActuel();
		int oldPosition = pc.getPosition();
		Random r = new Random();
		int deplacement = r.nextInt(6);
		cdp.deplacerPion(pc, deplacement);
		assertTrue(pc.getPosition() == oldPosition + deplacement);
		
		
		
	}
	
	@Test
	public void testSurpassePlateau() {
		plateau.initialiser(cdp, null);
		Pion pc = pr.getPionActuel();
		cdp.deplacerPion(pc, plateau.getNbCases());
		assertTrue(pc.getPosition() == plateau.getNbCases());
	}

}
