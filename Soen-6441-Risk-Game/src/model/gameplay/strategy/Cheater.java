package model.gameplay.strategy;

import model.map.Country;

public class Cheater extends ConcreteStrategy implements Strategy {

	@Override
	public void reinforce() {
		// TODO Auto-generated method stub
		map.getPhase().setPhase("Reinforcement phase", player);
		
			if(player.ownedCountries == null || player.ownedCountries.size() == 0);
			else {
				for(Country c : player.ownedCountries) 
				{
					int army = c.getArmyNumber() * 2;
					c.setArmyNumber(army);
				}
			}
			
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
