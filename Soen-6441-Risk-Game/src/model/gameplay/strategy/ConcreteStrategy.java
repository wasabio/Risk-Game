package model.gameplay.strategy;

import model.gameplay.Player;
import model.map.Country;
import model.map.Map;
import model.utilities.Rng;

public abstract class ConcreteStrategy implements Strategy {

	protected Player player;
	protected Map map;
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
	 * Method to place a country during the startup phase.
	 */
	@Override
	public void placeOneArmy() {
		map.getPhase().setPhase("Start up phase", player);
		int randomIndex = Rng.getRandomInt(0, player.ownedCountries.size()-1);
		Country randomCountry = player.ownedCountries.get(randomIndex);
		
		map.getPhase().setAction(player.getName() + " added 1 army in " + randomCountry.getName() + "\n");
		map.addArmiesFromHand(randomCountry, 1);		
	}
}
