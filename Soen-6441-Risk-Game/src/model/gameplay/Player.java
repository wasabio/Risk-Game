package model.gameplay;

import java.util.ArrayList;

import model.map.Country;
import model.map.Map;

/**
 * This class is dealing with each player's data changes like owned countries, armies, and cards
 * 
 */
public class Player 
{
	private int number;
	private int armies;
	public ArrayList<Country> ownedCountries = new ArrayList<Country>();
	
	/**
	 * The Constructor method of player
	 * @param new_number new number of countries
	 * @param new_armies new number of armies
	 */
	public Player(int new_number, int new_armies) 
	{
		setNumber(new_number);
		setArmies(new_armies);
	}

	/**
	 * get number method for owned countries
	 * @return return to current number of owned countries of the player
	 */
	public int getNumber() 
	{
		return number;
	}
	
	/**
	 * set number method for owned countries
	 * @param number the number of countries
	 */
	public void setNumber(int number) 
	{
		this.number = number;
	}
	
	/**
	 * get method for the armies
	 * @return return to current armies number of the player
	 */
	public int getArmies() 
	{
		return armies;
	}
	
	/**
	 * set method of the armies
	 * @param armies The number of armies of the current players
	 */
	public void setArmies(int armies) 
	{
		this.armies = armies;
	}
	
	/**
	 * The method is for checking correctness that a player with how many countries he or she owned
	 * @param countryNumber The total owned country number of a player
	 * @return return Return true means correct, and false means incorrect with the owned country number of a player
	 */
	public boolean owns(int countryNumber) 
	{
		for(Country c : ownedCountries) 
		{
			if(c.getNumber() == countryNumber) 
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method will process to reinforce a player's country
	 * @param map the map
	 * @param countryNumber the selected country
	 * @param selectedArmies the number of armies to add
	 */
	public void reinforcement(Map map, int countryNumber, int selectedArmies) 
	{
		map.addArmiesFromHand(countryNumber, selectedArmies);
	}
	
	/**
	 * This method will process the all-out attack mode.
	 * @param attackerCtry Country attacking
	 * @param defenderCtry Country defending
	 */
	public void attack(Map map, Country attackerCtry, Country defenderCtry) 
	{
		do{
			Dices dices = new Dices(attackerCtry.getArmyNumber(), defenderCtry.getArmyNumber());
			battle(map, dices, attackerCtry, defenderCtry);
		}while(attackerCtry.getArmyNumber() > 1 && defenderCtry.getArmyNumber() > 0);	// Continue until no attack is possible
		
		/* Resolving battle result : conquering a country */
		if(defenderCtry.getArmyNumber() == 0) {
			conquer(defenderCtry);			
		}
	}

	/**
	 * This method will process the classic attack mode. The players provide their dices.
	 * @param attackerCtry Country attacking
	 * @param defenderCtry Country defending
	 * @param dices Dices selected by the players
	 */
	public void attack(Map map, Country attackerCtry, Country defenderCtry, Dices dices) {
		battle(map, dices, attackerCtry, defenderCtry);
		
		/* Resolving battle result : conquering a country */
		if(defenderCtry.getArmyNumber() == 0) {
			conquer(defenderCtry);			
		}
	}
	
	/**
	 * This class will compute a battle. It rolls the dice, computing the result, and resolve the loss of both sides.
	 * @param map
	 * @param dices
	 * @param attackerCtry
	 * @param defenderCtry
	 */
	private void battle(Map map, Dices dices, Country attackerCtry, Country defenderCtry) {
		dices.roll();
		/* Resolving loss in both armies */
		map.addArmiesToCountry(attackerCtry.getNumber(), -dices.getAttackerLoss());
		map.addArmiesToCountry(defenderCtry.getNumber(), -dices.getDefenderLoss());
	}
	
	public void fortification(Map map, int originCountryId, int destinationCountryId, int selectedArmies)
	{
		map.addArmiesToCountry(originCountryId, -selectedArmies);
		map.addArmiesToCountry(destinationCountryId, selectedArmies);
	}
	
	/**
	 * To conquer a country
	 * @param c the country to conquer
	 */
	private void conquer(Country c)
	{
		Player defender = c.getPlayer();
		defender.ownedCountries.remove(c);
		this.ownedCountries.add(c);
		c.setPlayer(this);
	}

	
}
