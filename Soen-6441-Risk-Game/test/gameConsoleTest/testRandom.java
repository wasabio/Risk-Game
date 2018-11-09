package gameConsoleTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


import model.utilities.Random;
/**
 * 
 * 
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 */
public class testRandom 
{
	@Test
	public void testRandomFunc() 
	{
		int i = 5;
		int m = 10;
		Random.getRandomInt(i, m);
		
		
		assertTrue(Random.getRandomInt(i, m)<=10);
		assertTrue(5<=Random.getRandomInt(i, m));
	}
	
}
