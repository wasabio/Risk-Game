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
	 * @param extraArmies : a continent's extraArmies after conquest it 
	 * @param name : continent name with String type
	 *
	 */
	public Continent(int bonus, String name, int extraArmies) 
	{
		super();
		this.extraArmies = extraArmies;
		this.name = name;
	}
	
	/**
	 * To get the country list in String layout
	 * @return the list of countries' name with String type
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
	 * method to override the Continent toString() method.
	 */
	//public String toString() 
	//{
	//	return this.name;
	//}
	
	/**
	 * add a country to Continent
	 * @param c the country was added
	 */
	public void addCountry(Country c)
	{
		c.setContinent(this);
		countries.add(c);
	}

	/**
	 * remove a country from Continent
	 * @param c the country was removed
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
	 * To get the country list
	 * @return the list of countries with ArrayList type
	 */
	public ArrayList<Country> getCountries()
	{
		return countries;
	}

	/**
	 * To set the number of the troops
	 * @param extraArmies: the number of the troops
	 */
	public void setExtraArmies(int extraArmies)
	{
		this.extraArmies = extraArmies;
	}

	/**
	 * To get the number of the bonus armies that a player should reveive if he holds the whole continent.
	 * @return the number of the bonus armies with int type
	 */
	public int getExtraArmies()
	{
		return extraArmies;
	}

	/**
	 * To set a name to  the continent
	 * @param name the desired name that user wants to set to the continent with String type
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * To get the name of the continent
	 * @return the name of the continent with String type
	 */
	public String getName()
	{
		return name;
	}
	
	public Player getOwner() {
		Player p = countries.get(0).getPlayer();
		for(Country c : countries) {
			if(c.getPlayer().getNumber() != p.getNumber()) {
				return null;
			}
		}
		
		return p;
	}
	
	public boolean ownedBy(Player p) {
		for(Country c : countries) {
			if(c.getPlayer().getNumber() != p.getNumber()) {
				return false;
			}
		}
		
		return true;
	}
}
