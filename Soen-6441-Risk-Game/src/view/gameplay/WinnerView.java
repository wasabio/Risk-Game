package view.gameplay;

import model.gameplay.Player;

/**
 * This class is in charge of displaying the winner who won the game
 * 
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 */
public class WinnerView {
	/**
	 * 
	 * @param new_winner
	 */
	public WinnerView(Player new_winner) {
		print(new_winner);
	}

	/**
	 * To print the player that won the game
	 * @param p the winner
	 */
	public void print(Player p) {
		System.out.println("Congrats P" + p.getNumber() + ", you won !");
	}
}
