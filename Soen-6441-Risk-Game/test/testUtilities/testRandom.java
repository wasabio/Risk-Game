package testUtilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


import model.utilities.Random;

public class testRandom 
{
	@Test
	public void testRandomFunc() 
	{
		int i = 5;
		int m = 10;
		Random.getRandomIndex(i, m);
		
		
		assertTrue(Random.getRandomIndex(i, m)<=10);
		assertTrue(5<=Random.getRandomIndex(i, m));
	}
	
}
