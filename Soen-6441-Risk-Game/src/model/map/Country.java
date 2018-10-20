package model.map;

import java.util.ArrayList;
import java.util.Observable;

import model.gameplay.Player;

/**
 * This class is handling the information and the behavior of countries.
 * It holds data about the army in the country and also the relationships with the country's neighbors
 */
public class Country extends Observable 
{
	
	private String name;
	private int number;
	private Continent continent;
	private int xLocation;
	private int yLocation;
	private Player player;
	private int armyNumber;
	public boolean hasReached;
	public ArrayList<Country> neighbors = new ArrayList<Country>();
	public ArrayList<String> neighborsNames = new ArrayList<String>();
	
	public static int Counter = 0;
	
	/**
	 * The Constructor method of Country
	 */
	public Country() 
	{
		Counter++;
		this.number = Counter;
		this.continent = null;
		this.setPlayer(null);
		this.name = "ctry" + Integer.toString(Counter);
		this.xLocation = -1;
		this.yLocation = -1;
		this.armyNumber = 0;
	}
	
	/**
	 * The constructor method to initial the attributes in the country
	 * 
	 * @param new_name The new country name with String type    
	 */
	public Country(String new_name) 
	{
		Counter++;
		this.number = Counter;
		this.continent = null;
		this.setPlayer(null);
		this.name = new_name;
		this.xLocation = -1;
		this.yLocation = -1;
		this.armyNumber = 0;
	}

	/**
	 * The method to set the country's x and y position in the map file
	 * @param x The x location of the country with int type
	 * @param y The y location of the country with int type
	 */
	public void setCenter(int x, int y) 
	{
		this.xLocation = x;
		this.yLocation = y;
	}

	/**
	 * The method is to get the continent
	 * 
	 * @return Returning the current continent with Continent type
	 */
	public Continent getContinent() 
	{
		return continent;
	}
	
	/**
	 * The method is to set the continent associated to the country
	 * 
	 * @param continent The current continent that will be set            
	 */
	public void setContinent(Continent continent) 
	{
		this.continent = continent;
	}

	/**
	 * The method is to set the player to the country
	 * 
	 * @param p The player that will own the current country
	 *            
	 */
	public void setPlayer(Player p) 
	{
		player = p;
	}
	
	/**
	 * THe method is to get the player who is owned the current country
	 * 
	 * @return Returning the current player(who owned the country) with Player type
	 */
	public Player getPlayer() 
	{
		return player;
	}
	
	/**
	 * The method is to add the armies to the current country
	 * 
	 * @param newArmies The number of new Armies that needed to be added into the country with int type
	 *            
	 */
	public void addArmies(int newArmies) 
	{
		this.setArmyNumber(this.getArmyNumber() + newArmies);
	}

	/**
	 * To remove the Armies from the country
	 * The method is to remove the Armies from the current country
	 * 
	 * @param RemovedArmies The number of the Armies that want to be removed from the country
	 *            
	 */
	public void removeArmies(int RemovedArmies) 
	{
		this.setArmyNumber(this.getArmyNumber() - RemovedArmies);
	}
	
	/**
	 * The method is to set a name to the current country
	 * 
	 * @param name The desired name that want to set to the country with String type
	 *            
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * The method is to get the name of the current country
	 * 
	 * @return Returning the name of the country with String type
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * The method is to set the x coordinate position of the current country
	 * 
	 * @param x The desired x coordinate position of the country
	 *            
	 */
	public void setXLocation(int x) 
	{
		xLocation = x;
	}

	/**
	 * The method is to get the x coordinate location of the current country
	 * 
	 * @return Returning the x coordinate location of the country with int type
	 */
	public int getXLocation() 
	{
		return xLocation;
	}
	
	/**
	 * The method is to set the y coordinate location of the current country
	 * 
	 * @param y The desired y coordinate location of the country
	 */
	public void setYLocation(int y) 
	{
		yLocation = y;
	}

	/**
	 * The method is to get the y coordinate location of the current country
	 * @return Returning the y coordinate location with int type
	 */
	public int getYLocation() 
	{
		return yLocation;
	}
	
	/**
	 * The method is to add a neighbor to the current country
	 * @param neighbor The linked country of the country
	 */
	public void linkTo(Country neighbor) //QA not fully sure is one or multiple, but I think is only one
	{
		this.neighbors.add(neighbor);
	}
	
	/**
	 * This override method is to get country information 
	 * such as neighbors and owned player of the current country with string layout
	 * 
	 * @return Returning country information with neighbors and owned player in String type
	 */
	@Override
	public String toString() 
	{
		ArrayList<String> nameList = new ArrayList<>();
		if (neighbors.size() > 0) 
		{
			for (Country c : neighbors) 
			{
				nameList.add(c.getName());
			}
		}
		return "Country= " + this.getName() + " player=" ;
	}

	/**
	 * The method is to get the army number of the current country
	 * @return Returning the country's armyNumber with int type
	 */
	public int getArmyNumber() 
	{
		return armyNumber;
	}

	/**
	 * The method is to set the army number of the current country
	 * @param armyNumber The number of armies in the country
	 */
	public void setArmyNumber(int armyNumber) 
	{
		this.armyNumber = armyNumber;
	}

	/**
	 * The method is to get the number of countries
	 * @return Returning the number of countries with int type
	 */
	public int getNumber() //QA
	{
		return number;
	}
	
	/**
	 * To ensure a country can send troops during the fortification phase. The country must have more than 1 army and must have 
	 * allied neighbors.
	 * @param originCountryId is the origin country ID of the fortification phase.
	 * @return a boolean saying if the fortification move is possible.
	 */
	public boolean canSendTroopsToAlly() {
		/* We can't move armies from a country that has only 1 army or if the country is not connected */
		if(getArmyNumber() <= 1 || neighbors == null || neighbors.size() == 0) {
			return false;
		}

		/* We look for an allied neighbor */
		for(Country neighbor : neighbors) {
			if(neighbor.getPlayer() == getPlayer()) {
				return true;
			}
		}
		
		/* No ally found in neighbors */
		return false;
	}

	/**
	 * This functions looks for a connected path of allied countries between 2 countries (this & destination)
	 * @param destination is the country to reach
	 * @return if a path has been found between both countries
	 */
	public boolean isConnectedTo(Country destination) {
		ArrayList<Country> open = new ArrayList<Country>();
		ArrayList<Country> closed = new ArrayList<Country>();
		Player player = this.getPlayer();
		Country current;
		
		open.add(this);
		
		while(open.size() > 0)	/* Continue until all connected allies have been inspected */
		{
			current = open.remove(0);
			
			for(Country neighbor : current.neighbors) { /* Check all the neighbors */
				if(neighbor == destination) {
					return true;
				}
				if(player.ownedCountries.contains(neighbor) && !closed.contains(neighbor)) { /* Add not inspected allied country in open list */
					open.add(neighbor);
				}
			}
			closed.add(current);
		}
		
		return false;
	}
}
