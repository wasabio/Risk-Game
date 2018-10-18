package model.map;

import java.util.ArrayList;

import model.gameplay.Player;

/**
* This class holds informations and behaviors of a continent. 
* It holds a list of its associated countries.
*/
public class Continent 
{
	private String name;
	private int extraArmies;
	public ArrayList<Country> countries = new ArrayList<Country>();
	
	/**
	 * constructor method.
	 */
	public Continent() 
	{
		
	}
	
	/**
	 * Construction method with the following parameters.
	 * @param new_name :  continent name with String type
	 * @param new_extraArmies : extraArmies the player can get after conquest the continent
	 */
	public Continent(String new_name, int new_extraArmies)
	{
		this.name = new_name;
		this.extraArmies = new_extraArmies;
	}
	
	/**
	 * constructor method with incoming parameters.
	 * @param extraArmies A continent's extraArmies after a player conquest it 
	 * @param name Continent name with String type
	 *
	 */
	public Continent(int bonus, String name, int extraArmies) //QA
	{
		super();
		this.extraArmies = extraArmies;
		this.name = name;
	}
	
	/**
	 * This method is to get the country list in String layout
	 * @return Return the list of countries' name with String type
	 */
	public String toString() 
	{
		String retString = "";
		for(Country c: this.countries) 
		{
			retString += c.getName() + " ";
		}
		return retString;
	}

	/**
	 * Adding a country to the Continent
	 * @param c The country that was added
	 */
	public void addCountry(Country c)
	{
		c.setContinent(this);
		countries.add(c);
	}

	/**
	 * Removing a country from the Continent
	 * @param c The country that was removed
	 */
	public void removeCountry(Country c)
	{
		for(Country A : countries)
		{
			if(A.equals(c))
			{
				countries.remove(A);
				break;
			}
		}
	}
	
	/**
	 * This method is to get the country list of the continent
	 * @return Return the list of countries with ArrayList type
	 */
	public ArrayList<Country> getCountries()
	{
		return countries;
	}

	/**
	 * This method is to set the number of the extra armies of the continent for each turn when the continent is owned
	 * @param extraArmies: the number of the extra armies players will get each turn
	 */
	public void setExtraArmies(int extraArmies)
	{
		this.extraArmies = extraArmies;
	}

	/**
	 * This method is to get the number of the extra armies that a player should receive if he owned the whole continent in each turn.
	 * @return return Returning the number of the extra armies with int type
	 */
	public int getExtraArmies()
	{
		return extraArmies;
	}

	/**
	 * This method is to set a name to the continent
	 * @param name The desired name that user wants to set to the continent with String type
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * This method is to get the name of the continent
	 * @return Returning the name of the continent with String type
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * This method is to get the owner of the countries(in the continent) 
	 * and a checking logic for correctness of owned country number(in the continent) of a player
	 * @return Checking the number of owned countries is correct or not. If it is correct, than returning the player as player type
	 */
	public Player getOwner() 
	{
		Player p = countries.get(0).getPlayer();
		for(Country c : countries) 
		{
			if(c.getPlayer().getNumber() != p.getNumber()) 
			{
				return null;
			}
		}
		
		return p;
	}
	
	/**
	 * This method is to check that the country is owned by which player
	 * @param p the player of the player type
	 * @return Returning false means the not owned by the player, and true means the
	 */
	public boolean ownedBy(Player p) 
	{
		for(Country c : countries) 
		{
			if(c.getPlayer().getNumber() != p.getNumber()) 
			{
				return false;
			}
		}
		
		return true;
	}
}
