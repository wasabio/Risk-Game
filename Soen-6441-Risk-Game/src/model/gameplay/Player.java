package model.gameplay;

import java.util.ArrayList;

import model.map.Country;

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

	
	
}
