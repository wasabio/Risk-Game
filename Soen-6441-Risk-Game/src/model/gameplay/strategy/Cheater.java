package model.gameplay.strategy;

import model.map.Country;

public class Cheater extends ConcreteStrategy implements Strategy {

	@Override
	public void reinforce() {
		map.getPhase().setPhase("Reinforcement phase", player);
		
		if(player.ownedCountries != null && player.ownedCountries.size() != 0) {
			for(Country c : player.ownedCountries) 
			{
				int army = c.getArmyNumber() * 2;
				c.setArmyNumber(army);
			}
		}		
	}

	@Override
	public void attack() {
		map.getPhase().setPhase("Attack phase", player);
		
		for(Country c : player.ownedCountries) {
			for(Country neighbor : c.neighbors)
			{
				if (neighbor.getPlayer() != player)
				{
					neighbor.setPlayer(player);
					player.ownedCountries.add(neighbor);
				}
			}
		}
	}

	@Override
	public void fortify() {
		map.getPhase().setPhase("Fortification phase", player);
		
		for(Country c : player.ownedCountries)
			for( Country neighbor : c.neighbors)
			{
				if( neighbor.getPlayer() != player)
				{
					int army = c.getArmyNumber() * 2;
					c.setArmyNumber(army);
					break;
				}
			}
	}

}
