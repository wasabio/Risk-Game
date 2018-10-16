
package model.utilities;

import java.util.concurrent.ThreadLocalRandom;

/**
 * To generate all the random aspects of the game : dice, cards, countries assignment...
 */
public class Random {

	public static int getRandomIndex(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

}
