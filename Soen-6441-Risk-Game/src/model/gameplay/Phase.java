package model.gameplay;


import java.util.Observable;

public class Phase extends Observable{
	String phase;
	Player p;
	String action="";
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
	public String getAction() {
		return action;
	}
	public void setAction(String str) {
		action = str;
	}
	
}
