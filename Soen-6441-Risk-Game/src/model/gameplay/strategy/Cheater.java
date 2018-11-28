package model.gameplay.strategy;

import java.util.Iterator;
import java.util.ListIterator;

import model.gameplay.Player;
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
				map.getPhase().setAction(player.getName() + " reinforced " + c.getArmyNumber() + " army in " + c.getName() + "\n");		
			}
		}
	}

	@Override
	public void attack() {
		map.getPhase().setPhase("Attack phase", player);
		
		ListIterator<Country> i = player.ownedCountries.listIterator();
		
		while (i.hasNext()) 
		{
			Country c = i.next();
			for(Country neighbor : c.neighbors)
			{
				if (neighbor.getPlayer() != player)
				{
					Player defender = neighbor.getPlayer();
					defender.ownedCountries.remove(neighbor);
					i.add(neighbor);
					neighbor.setPlayer(player);
					map.addArmiesToCountry(neighbor, (-neighbor.getArmyNumber() + 1));	//Setting army number to 1
					map.getPhase().setAction(map.getPhase().getAction() + c.getName() + "(" + player.getName() + ") conquered " + neighbor.getName() + " and moved 1 army\n");
				}
			}
		}
	}

	@Override
	public void fortify() {
		map.getPhase().setPhase("Fortification phase", player);
		/*
		for(Country c : player.ownedCountries) {
			for( Country neighbor : c.neighbors)
			{
				if( neighbor.getPlayer() != player)
				{
					int army = c.getArmyNumber() * 2;
					c.setArmyNumber(army);
					break;
				}
			}
		}*/
	}

}
