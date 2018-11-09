package model.gameplay;

import java.util.ArrayList;
import java.util.Observable;

import model.map.Country;
import model.map.Map;

/**
 * This class is dealing with each player's data changes like owned countries, armies, and cards
 * 
 */
public class Player extends Observable
{
	private int number;
	private int armies;
	private Phase phase = new Phase();
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

	public void reinforcement(Map map, int countryNumber, int selectedArmies) 
	{
		map.addArmiesToCountry(countryNumber, selectedArmies);
		phase.setAction("P "+" reinfoced "+ selectedArmies+" army in "+map.countries.get(countryNumber-1).getName()+"\n");
		
		
	}
	
	public void attack() 
	{
		
	}
	
	public void fortification(Map map, int originCountryId, int destinationCountryId, int selectedArmies)
	{
		map.addArmiesToCountry(originCountryId, -selectedArmies);
		map.addArmiesToCountry(destinationCountryId, selectedArmies);
		phase.setAction("p"+getNumber()+" fortified "+ selectedArmies+" army from "+
				map.countries.get(originCountryId).getName()+"to"+map.countries.get(destinationCountryId).getName()+"\n");
	}
	
}
