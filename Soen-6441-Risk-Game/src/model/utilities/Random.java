
package model.utilities;

import java.util.concurrent.ThreadLocalRandom;

/**
 * To generate all the random aspects of the game : dice, cards, countries assignment...
 */
public class Random {

	/**
	 * The method is to get the random index from local thread.
	 * @param min The minimum number of the index with int type.
	 * @param max The maximum number of the index with int type.
	 * @return Returning the current random index of thread with minimum and maximum numbers.
	 */
	public static int getRandomIndex(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

}
