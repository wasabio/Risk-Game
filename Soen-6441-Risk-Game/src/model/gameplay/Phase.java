package model.gameplay;


import java.util.Observable;
/**
 * 
 * The class for observer phases
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 */
public class Phase extends Observable{
	String phase;
	Player p;
	String action="";
	int army=-1;
	
	/**
	 * Change phase states in the game
	 * @param newPhase the nest state phase
	 * @param newPlayer next player
	 * @param armyNum the army num
	 */
	public void setPhase(String newPhase,Player newPlayer,int armyNum) {
		this.phase = newPhase;
		this.p = newPlayer;
		army = armyNum;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * get the current phase
	 * @return return the current phase
	 */
	public String getPhase() {
		return phase;
	}
	
	/**
	 * get the current player
	 * @return the current player
	 */
	public Player getPlayer() {
		return p;
	}
	
	/**
	 * get the current player's action
	 * @return the current player's action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * set the action
	 * @param str the action name
	 */
	public void setAction(String str) {
		action = str;
	}
	
}
