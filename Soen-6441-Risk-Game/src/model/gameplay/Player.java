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
	 * This method will process a single attack move.
	 * @param attackerCtry Country attacking
	 * @param defenderCtry Country defending
	 * @param attackMode Attack mode (all-out or classic)
	 */
	public void attack(Map map, Country attackerCtry, Country defenderCtry, int attackMode) 
	{
		do{
			Dices dices = new Dices(attackerCtry.getArmyNumber(), defenderCtry.getArmyNumber());
			dices.roll();
			
			/* Resolving loss in both armies */
			map.addArmiesToCountry(attackerCtry.getNumber(), -dices.getAttackerLoss());
			map.addArmiesToCountry(defenderCtry.getNumber(), -dices.getDefenderLoss());
			
			// Continue if attack mode is all-out, until no attack is possible
		}while(attackMode == 1 && attackerCtry.getArmyNumber() > 1 && defenderCtry.getArmyNumber() > 0);
		
		/* Resolving battle result : conquering a country */
		if(defenderCtry.getArmyNumber() == 0) {
			conquer(defenderCtry);			
		}
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
