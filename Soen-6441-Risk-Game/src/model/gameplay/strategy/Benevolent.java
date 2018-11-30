package model.gameplay.strategy;

import model.map.Country;

public class Benevolent extends ConcreteStrategy implements Strategy {

	@Override
	public void reinforce() {
		do
		{
			map.getPhase().setPhase("Reinforcement phase", player);
			Country weakestCountry = player.getWeakestCountry();
			player.reinforcementMove(weakestCountry, 1);
		}while(player.getArmies() > 0);
	}

	@Override
	public void attack() 
	{
		map.getPhase().setPhase("Attack phase", player);
	}

	@Override
	public void fortify() {
		map.getPhase().setPhase("Fortification phase", player);
		
		Country weakestCountry = player.getWeakestCountry();
		Country origin = null;
		
		for (Country c : player.ownedCountries) {
			//Check if a connected country can reinforce the weakest
			if (c.isConnectedTo(weakestCountry) && c.getArmyNumber() > 1) {
				origin = c;
				break;
			}
		}

		if (origin == null)
			return;

		int armies = origin.getArmyNumber() / 2;
		player.fortificationMove(origin, weakestCountry, armies);
	}
}
