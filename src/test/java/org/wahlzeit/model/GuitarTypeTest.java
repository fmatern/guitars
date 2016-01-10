package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * test cases for GuitarType classes
 */
public class GuitarTypeTest {

	GuitarManager manager = GuitarManager.getInstance();
	
	/* tests basic object and hierarchy creation via manager */
	@Test
	public void testCreation(){
		
		GuitarType elG = manager.getGuitarType("electric");
		elG.addSubType(manager.getGuitarType("ST", "Strat", "Fender"));
		
		Guitar strat = manager.createGuitar("ST","Strat", "Fender");
		strat.setOwner("Me");
		strat.setPrice(100);
		
		assertEquals(elG, strat.getGuitarType().getSuperType());
		assertEquals(manager.getGuitarType("ST", "Strat", "Fender"), strat.getGuitarType());
		
		GuitarType elGsub = elG.getSubTypeIterator().next();
		assertEquals(elGsub, strat.getGuitarType());
		
	}

}
