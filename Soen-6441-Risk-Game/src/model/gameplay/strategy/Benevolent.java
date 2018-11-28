package model.gameplay.strategy;

import java.util.ArrayList;
import model.map.Country;

public class Benevolent extends ConcreteStrategy implements Strategy {

	@Override
	public void reinforce() {
		// TODO Auto-generated method stub
		map.getPhase().setPhase("Reinforcement phase", player);
		while(player.getArmies() != 0)
		{
			Country weakestCountry = player.getWeakestCountry();
			int armies = player.getArmies();
			player.reinforcementMove(weakestCountry, 1);
		}
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		map.getPhase().setPhase("Attack phase", player);
		System.out.println("Skipped");
		
	}

	@Override
	public void fortify() {
		// TODO Auto-generated method stub
		map.getPhase().setPhase("fortification phase", player);
		
		
	}

}
