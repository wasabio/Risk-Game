package model.gameplay.strategy;

import model.map.Map;
import view.gameplay.ReinforcementView;

/**
 * A concrete Strategy that implements human strategy operation
 */
public class Human extends ConcreteStrategy implements Strategy {
	
	private ReinforcementView reinforcementView;
	
	/**
	 * Constructor for HumanStrategy
	 */
	@Override
	public void reinforce() {
		reinforcementView = new ReinforcementView();

		int countryNumber = reinforcementView.askCountry(player);
		if(countryNumber == 0) {
			int combination = reinforcementView.askCardsToTrade(player);
			if(player.trade(combination)) {
				player.setArmies(player.getArmies() + Map.getCardBonus());
				map.getPhase().setAction(player.getName() + " traded cards and got " + Map.getCardBonus() + " new armies\n");
			} else {
				reinforcementView.errorTraiding();
			}
		} else {
			int selectedArmies = reinforcementView.askArmiesNumber(player);
			map.addArmiesFromHand(countryNumber, selectedArmies);
			player.reinforcement(map, countryNumber, selectedArmies);
			map.getPhase().setAction(player.getName() + " reinforced " + selectedArmies + " army in " + map.countries.get(countryNumber-1).getName() + "\n");
		}
	}

	@Override
	public void attack() {
		
	}

	@Override
	public void fortify() {
		
	}

}
