package view.gameplay;

import model.gameplay.Player;

/**
 * This class is in charge of displaying the winner who won the game
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 *
 */
public class WinnerView {
	
	/**
	 * show the winner view of the game
	 * @param new_winner the winner player
	 */
	public WinnerView(Player new_winner) {
		print(new_winner);
	}

	/**
	 * To print the player that won the game
	 * @param p the winner
	 */
	public void print(Player p) {
		System.out.println("Congrats " + p.getName() + ", you won !");
	}
}
