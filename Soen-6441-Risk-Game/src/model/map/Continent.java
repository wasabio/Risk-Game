package model.map;

import java.util.ArrayList;
import model.map.*;
/**
*
* This class create methods to add/remove countries to Continents, get/set Continents names, extraArmies and color.
* the toString method lists the countries on the continent.
*
*/

<<<<<<< HEAD
public class Continent 
{
	String name;
	private int extraArmies;
	private String color;
	
	private ArrayList<Country> countries = new ArrayList<Country>();
	
	/**
	 * constructor method.
	 */
	public Continent() 
	{
		
	}
	
	/**
	 * Construction method with incoming parameters.
	 * @param color : the continent color with Color type
	 * @param name :  continent name with String type
	 * @param extraArmies : extraArmies the player can get after conquest the continent
	 * @param world : the world the players are in(not done yet)
	 */
	public Continent(String color, String name, int extraArmies)
	{
		this.extraArmies = extraArmies;
		this.color = color;
		this.name = name;
		//this.world = world;
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
	 * To get the number of the troop
	 * @return the number of the troop with int type
	 */
	public int getExtraArmies()
	{
		return extraArmies;
	}

	/**
	 * To set the color to the continent
	 * @param col the color that want to be set to the continent with String type
	 */
	public void setColor(String col)
	{
		color = col;
	}

	/**
	 * To get the color of the continent
	 * @return the color of the continent with String type
	 */
	public String getColor()
	{
		return color;
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

	
	
=======
public class Continent {

	private String name;
	private int bonus;
	
	public ArrayList<Country> countries = new ArrayList<Country>();
	
	public Continent(String new_name, int new_bonus) {
		this.name = new_name;
		this.bonus = new_bonus;
	}

	public String getName() {
		return this.name;
	}
>>>>>>> master
}
