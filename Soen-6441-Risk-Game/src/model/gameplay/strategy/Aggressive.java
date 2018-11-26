package model.gameplay.strategy;

import model.map.Country;

public class Aggressive extends ConcreteStrategy implements Strategy {

	@Override
	public void reinforce() {
		map.getPhase().setPhase("Reinforcement phase", player);

		Country strongestCountry = player.getStrongestCountry();
		int maxArmies = player.getArmies();
		
		player.reinforceMove(strongestCountry, maxArmies);
	}

	@Override
	public void attack() {
		while(player.canContinueAttacking()) {
			map.getPhase().setPhase("Attack phase", player);

			Country attacker = getACountryThatCanAttack();
			Country defender = attacker.getEnnemyNeighbor();
			
			boolean conquered = player.allOutAttack(attacker, defender);
			
			if(conquered) {
				int movingArmies = (attacker.getArmyNumber() - 1);
				map.addArmiesToCountry(attacker.getNumber(), -movingArmies);
				map.addArmiesToCountry(defender.getNumber(), movingArmies);
				map.getPhase().setAction(map.getPhase().getAction() + attacker.getName() + "(" + player.getName() + ") conquered " + defender.getName() + " and moved " + movingArmies + " armies\n");
			} else {
				map.getPhase().setAction(map.getPhase().getAction() + attacker.getName() + "(" + player.getName() + ") failed to conquer " + defender.getName() + " (" + defender.getPlayer().getName() + ")\n");
			}
		}
	}

	@Override
	public void fortify() {
		
	}
	
	private Country getACountryThatCanAttack() {
		for(Country c : player.ownedCountries) {
			if(c.canAttack())	return c;
		}
		return null;
	}
}
