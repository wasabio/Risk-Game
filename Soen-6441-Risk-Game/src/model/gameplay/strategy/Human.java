package model.gameplay.strategy;

import model.gameplay.Dices;
import model.map.Country;
import model.map.Map;
import view.gameplay.AttackView;
import view.gameplay.ReinforcementView;

/**
 * A concrete Strategy that implements human strategy operation
 */
public class Human extends ConcreteStrategy implements Strategy {
	
	private ReinforcementView reinforcementView;
	private AttackView attackView;

	/**
	 * Constructor for HumanStrategy
	 */
	@Override
	public void reinforce() {
		reinforcementView = new ReinforcementView();
		
		do 
		{
			map.getPhase().setPhase("Reinforcement phase", player);
			int countryNumber = reinforcementView.askCountry(player);
			if(countryNumber == 0) {	//Trading menu
				int combination = reinforcementView.askCardsToTrade(player);
				if(player.trade(combination)) {
					player.setArmies(player.getArmies() + Map.getCardBonus());
					map.getPhase().setAction(player.getName() + " traded cards and got " + Map.getCardBonus() + " new armies\n");
				} else {
					reinforcementView.errorTraiding();
				}
			} else {	//Reinforcement menu
				int selectedArmies = reinforcementView.askArmiesNumber(player);
				map.addArmiesFromHand(countryNumber, selectedArmies);
				map.getPhase().setAction(player.getName() + " reinforced " + selectedArmies + " army in " + map.countries.get(countryNumber-1).getName() + "\n");
			}
		}while(player.getArmies() > 0);
	}

	@Override
	public void attack() {
		attackView = new AttackView();

		do {
			map.getPhase().setPhase("Attack phase", player);

			Country attackerCtry = getAttackerCountry();
			if(attackerCtry == null)	return;
			
			Country defenderCtry = getDefenderCountry(attackerCtry);
			map.getPhase().setAction(attackerCtry.getName() + "("+ player.getName()+ ") attacked " + map.countries.get(defenderCtry.getNumber()-1).getName() + "(" + defenderCtry.getPlayer().getName() + ")\n");

			/* Getting attack mode : all-out or classic */
			if(attackView.askAttackMode() == 1) //All-out
			{
				player.attack(map, attackerCtry, defenderCtry);
			}
			else {			//Classic
				Dices dices = new Dices(attackerCtry.getArmyNumber(), defenderCtry.getArmyNumber());
				int attackerDices = attackView.askAttackerDices(player, dices.getAttackerMaxDices());
				int defenderDices = attackView.askDefenderDices(defenderCtry.getPlayer(), dices.getDefenderMaxDices());
				dices.setDicesNumber(attackerDices, defenderDices);
				
				player.attack(map, attackerCtry, defenderCtry, dices);
			}
			
			/* Attacker conquered the country */
			if(defenderCtry.getPlayer() == player) {
				int movingArmies = attackView.askMovingArmies(attackerCtry.getArmyNumber());
				map.addArmiesToCountry(attackerCtry.getNumber(), -movingArmies);
				map.addArmiesToCountry(defenderCtry.getNumber(), movingArmies);
				
				if(!player.gotCard == false) {
					player.getOneCard();
					player.gotCard = true;
				}
				map.getPhase().setAction(map.getPhase().getAction() + attackerCtry.getName() + "(" + player.getName() + ") conquered " + defenderCtry.getName() + " and moved " + movingArmies + " armies\n");
			} else {
				map.getPhase().setAction(map.getPhase().getAction() + attackerCtry.getName() + "(" + player.getName() + ") failed to conquer " + defenderCtry.getName() + " (" + defenderCtry.getPlayer().getName() + ")\n");
			}
		}while(player.canContinueAttacking() && attackView.continueAttacking());
	}
	
	@Override
	public void fortify() {
		
	}

	private Country getAttackerCountry() {
		boolean canAttack;
		int	attackerCountryId;
		Country attackerCtry;
		do 
		{
			attackerCountryId = attackView.chooseAttackerCountry(player);
			if(attackerCountryId == 0) return null;	/* 0 to skip */
			
			attackerCtry = map.countries.get(attackerCountryId-1);
			canAttack = attackerCtry.canAttack(); /* Check if the selected country can attack another country */
			if(!canAttack)	attackView.errorCannotAttack();
		}while(!canAttack);
		
		return attackerCtry;
	}
	
	private Country getDefenderCountry(Country attackerCtry) {
		boolean canBeAttacked;
		int defenderCountryId;
		Country defenderCtry;
		do 
		{
			defenderCountryId = attackView.chooseAttackedCountry(player);
			defenderCtry = map.countries.get(defenderCountryId-1);
			canBeAttacked = defenderCtry.canBeAttackedBy(attackerCtry);
			if(!canBeAttacked)	attackView.errorCannotBeAttackedBy(attackerCtry);
		}while(!canBeAttacked);
		
		return defenderCtry;
	}
}
