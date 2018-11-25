package model.gameplay.strategy;

import model.gameplay.Player;
import model.map.Map;

public abstract class ConcreteStrategy implements Strategy {

	protected Player player;
	protected Map map;
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
}
