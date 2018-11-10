package classTests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.utilities.Random;

/**
 * Test the random method
 *
 */
public class testRandom {
	
	/**
	 * test the random can give right value
	 */
	@Test
	public void testRnd() {
		
		for(int i = 1; i < 500; i++) {
			int x = Random.getRandomInt(1, 10);
			
			assertTrue(x >= 0);
			assertTrue(x <= 10);
		}
	}
}
