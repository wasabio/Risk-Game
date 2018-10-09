package model.map;

import java.util.ArrayList;


import model.map.*;



/**
 * This class is handling the information of country, army in the country and relationships with the country's neighbors
 * 
 */

public class Country 
{
	private Continent continent;
	//private Player player;
	private int armyNumber;
	private String name;
	private int xLocation;
	private int yLocation;
	//private CountryButton button;
	public boolean hasReached;
	private ArrayList<String> neighborsNames = new ArrayList<>();
	private ArrayList<Country> neighbors = new ArrayList<Country>();
	
	/**
	 * Constructor method
	 */
	public Country() 
	{
		
	}
	
	/**
	 * Constructor method to initial the attributes
	 * 
	 * @param name : country name with String type
	 * @param continent : 
	 * @param xLocation :
	 * @param yLocation :
	 * @param
	 * @param           
	 */
	public Country(String name) 
	{
		this.continent = null;
		//this.player = null;
		this.name = name;
		this.xLocation = 0;
		this.yLocation = 0;
		//this.button = null;
	}
	
	/**
	 * To set the continent that will contain the country
	 * 
	 * @param cont : the desired continent want to be set with Continent type
	 *            
	 */
	public void setContinent(Continent cont) 
	{
		continent = cont;
	}
	
	/**
	 * Override method to get country information in string layout
	 * 
	 * @return country information with String type
	 */
	@Override
	public String toString() 
	{
		ArrayList<String> nameList = new ArrayList<>();
		if (neighbors.size() > 0) 
		{
			for (Country cont : neighbors) 
			{
				nameList.add(cont.getName());
			}
		}
		return "Country= " + this.getName() + " player=" ;
		// player.getName()
		//return "Territory [name=" + name + ", continent=" + cont + ", centerY=" + centerY + ", centerX=" + centerX
		//+ ", linkes=" + linkNames + "]";
	}
	
	/**
	 * To get the continent
	 * 
	 * @return continent with Continent type
	 */
	public Continent getContinent() {
		return continent;
	}

	/**
	 * To set the player to the continent
	 * 
	 * @param p
	 *            the player the want to set to the contient with Player type
	 */
	//public void setPlayer(Player p) {
	//	player = p;
	//}
	
	/**
	 * To get the player
	 * 
	 * @return the player with Player type
	 */
	//public Player getPlayer() {
	//	return player;
	//}
	
	/**
	 * To add the troop to the country
	 * 
	 * @param addArmies : the number of the Armies that need to be added with int type
	 *            
	 */
	public void addArmies(int newArmies) 
	{
		this.armyNumber += newArmies;
	}

	/**
	 * might need to do some changes, the Armies cannot be removed by players, only can be removed in move turn or defeated
	 * To remove the Armies from the country
	 * 
	 * @param RemovedArmies : the number of the Armies that want to be removed from the country
	 *            
	 */
	public void removeArmies(int RemovedArmies) 
	{
		this.armyNumber -= RemovedArmies;
	}
	
	/**
	 * To set a name to the country
	 * 
	 * @param name : the desired name that want to set to the country with String type
	 *            
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * To get the name of the country
	 * 
	 * @return the name of the country with String type
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * To set the x coordinate postion of the country
	 * 
	 * @param x : the desired x coordinate postion of the country
	 *            
	 */
	public void setXLocation(int x) 
	{
		xLocation = x;
	}

	/**
	 * To get the x coordinate location of the country
	 * 
	 * @return the x coordinate location of the country with int type
	 */
	public int getXLocation() 
	{
		return xLocation;
	}
	
	/**
	 * To set the y coordinate location of the country
	 * 
	 * @param y : the desired y coordinate location of the country
	 */
	public void setYLocation(int y) 
	{
		yLocation = y;
	}

	/**
	 * To get the y coordinate location of the country
	 * 
	 * @return the y coordinate location of the country with int type
	 */
	public int getYLocation() 
	{
		return yLocation;
	}
	
}
