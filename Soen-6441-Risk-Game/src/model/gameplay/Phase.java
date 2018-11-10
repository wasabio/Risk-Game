package model.gameplay;


import java.util.Observable;

/**
 * The class for update different phase state
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen 
 *
 */
public class Phase extends Observable{
	String phase;
	Player p;
	String action="";

	/**
	 * set the phase state
	 * @param newPhase next state of phase
	 * @param newPlayer the current player
	 */
	public void setPhase(String newPhase,Player newPlayer) {
		this.phase = newPhase;
		this.p = newPlayer;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * get the current phase state
	 * @return return the current phase
	 */
	public String getPhase() {
		return phase;
	}
	
	/**
	 * get the current player
	 * @return return the current player
	 */
	public Player getPlayer() {
		return p;
	}
	
	/**
	 * get the current action of the current player
	 * @return return the chosen action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * set the chosen action
	 * @param str the action name with str type
	 */
	public void setAction(String str) {
		action = str;
	}
	
}
