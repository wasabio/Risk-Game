package model.gameplay.strategy;

import model.map.Country;
import model.map.Map;
import model.utilities.Rng;

public class Random extends ConcreteStrategy implements Strategy {

	@Override
	public void reinforce() {
		map.getPhase().setPhase("Reinforcement phase", player);
		
		int randomArmy = Rng.getRandomInt(0, player.getArmies());
		int randomCtyIndex = Rng.getRandomInt(0, player.ownedCountries.size()-1);
		
		player.reinforcementMove(map.getCountry(randomCtyIndex), randomArmy);
	}

	@Override
	public void attack() {
		map.getPhase().setPhase("attack phase", player);
		
		int attackCty = Rng.getRandomInt(0, player.ownedCountries.size()-1);
		Country attacker = player.ownedCountries.get(attackCty);
		
		int defendCty = Rng.getRandomInt(0, map.countries.size()-1);
		
	}

	@Override
	public void fortify() {
		
	}

}
