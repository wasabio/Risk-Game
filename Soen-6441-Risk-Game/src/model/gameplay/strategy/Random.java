package model.gameplay.strategy;

import model.map.Country;
import model.map.Map;
import model.utilities.Rng;

public class Random extends ConcreteStrategy implements Strategy {

	@Override
	public void reinforce() {
		map.getPhase().setPhase("Reinforcement phase", player);
		int randomIndex = Rng.getRandomInt(0, player.ownedCountries.size()-1);

		
	}

	@Override
	public void attack() {
		
	}

	@Override
	public void fortify() {
		
	}

}
