package model.gameplay;


import java.util.Observable;

public class Phase extends Observable{
	String phase;
	Player p;
	String action="";
	int army=-1;
	public void setPhase(String newPhase,Player newPlayer,int armyNum) {
		this.phase = newPhase;
		this.p = newPlayer;
		army = armyNum;
		setChanged();
		notifyObservers();
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
