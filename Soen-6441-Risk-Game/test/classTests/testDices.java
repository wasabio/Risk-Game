package classTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.gameplay.Dices;

public class testDices {
	Dices d;
	
	@Test
	public void testMaxDices() {
		d = new Dices(10, 5);
		
		d.roll();
		
		assertEquals(d.getDefenderMaxDices(), 2);
		assertEquals(d.getAttackerMaxDices(), 3);
	}
	
	@Test
	public void testMaxDices2() {
		d = new Dices(2, 1);
		
		d.roll();
		
		assertEquals(d.getDefenderMaxDices(), 1);
		assertEquals(d.getAttackerMaxDices(), 1);
	}
}
