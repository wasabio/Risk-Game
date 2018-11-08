package model.gameplay;


import java.util.Observable;

public class Phase extends Observable{
	String phase;
	Player p;
	public void setPhase(String newPhase,Player newPlayer) {
		this.phase = newPhase;
		this.p = newPlayer;
		setChanged();
		notifyObservers(this);
	}
	public String getPhase() {
		return phase;
	}
	public Player getPlayer() {
		return p;
	}
	
	
}
