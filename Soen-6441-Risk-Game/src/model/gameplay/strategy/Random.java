package model.gameplay.strategy;

import java.util.ArrayList;

import model.gameplay.Dices;
import model.map.Country;
import model.map.Map;
import model.utilities.Rng;

public class Random extends ConcreteStrategy implements Strategy {

	@Override
	public void reinforce() {
		map.getPhase().setPhase("Reinforcement phase", player);
		
		int randomArmy = Rng.getRandomInt(1, player.getArmies());
		int randomCtyIndex = Rng.getRandomInt(0, player.ownedCountries.size()-1);
		player.reinforcementMove(player.ownedCountries.get(randomCtyIndex), randomArmy);
	}

	@Override
	public void attack() {
		/* Choosing random attacker */
		ArrayList<Country> potentialAttackers = new ArrayList<Country>();	//Looking for potential attackers
		for(Country c : player.ownedCountries) {
			if(c.canAttack())	potentialAttackers.add(c);
		}
		if(potentialAttackers.size() == 0) {
			map.getPhase().setPhase("attack phase", player);
			return;
		}
		
		int attackerIndex = Rng.getRandomInt(0, potentialAttackers.size()-1);
		Country attacker = potentialAttackers.get(attackerIndex);
		
		do {
			map.getPhase().setPhase("attack phase", player);

			int attack = Rng.getRandomInt(0, 3);	//Chance to attack 75%
			if(attack == 0 || attacker.canAttack() == false)	 return;
			
			Country defender = attacker.getEnnemyNeighbor();
			
			player.classicAttack(attacker, defender, new Dices(attacker.getArmyNumber(), defender.getArmyNumber()));
			boolean conquered = player.conquestMove(attacker, defender, attacker.getArmyNumber() - 1);
			if(conquered)	attacker = defender;
		}while(true);		
	}

	@Override
	public void fortify() {
		/* Choosing random country that can be origin */
		ArrayList<Country> potentialOrigins = new ArrayList<Country>();	//Looking for potential attackers
		for(Country c : player.ownedCountries) {
			//if(c.canAttack())	potentialAttackers.add(c);
		}
	}
}
