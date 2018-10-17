package model.map;

import java.util.ArrayList;

import model.gameplay.Player;

/**
 * This class is handling the information and the behavior of a country.
 * It holds data about the army in the country and has relationships with the country's neighbours
 */
public class Country {
	
	private String name;
	private Continent continent;
	private int xLocation;
	private int yLocation;
	private Player player;
	private int armyNumber;
	public boolean hasReached;
	public ArrayList<Country> neighbours = new ArrayList<Country>();
	public ArrayList<String> neighboursNames = new ArrayList<String>();
	
	/**
	 * Constructor method
	 */
	public Country() 
	{
		
	}
	
	/**
	 * Constructor method to initial the attributes
	 * 
	 * @param new_name : country name with String type
	 *    
	 */
	public Country(String new_name) 
	{
		this.continent = null;
		this.setPlayer(null);
		this.name = new_name;
		this.xLocation = -1;
		this.yLocation = -1;
		this.armyNumber = 0;
	}

	/**
	 * To set the position of the country center
	 * 
	 * @return continent with Continent type
	 */
	public void setCenter(int x, int y) {
		this.xLocation = x;
		this.yLocation = y;
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
	 * To set the continent associated to the country
	 * 
	 * @param continent : the continent that will be set
	 *            
	 */
	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	/**
	 * To set the player to the country
	 * 
	 * @param p : the player that owns the country
	 *            
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
		this.setArmyNumber(this.getArmyNumber() + newArmies);
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
		this.setArmyNumber(this.getArmyNumber() - RemovedArmies);
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
	 * To set the x coordinate position of the country
	 * 
	 * @param x : the desired x coordinate position of the country
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

	
	public int getYLocation() 
	{
		return yLocation;
	}

	public void linkTo(Country neighbour) {
		this.neighbours.add(neighbour);
	}
	
	public ArrayList<String> getNeighboursNames(){
		return neighboursNames;
	}
	
	public void setNeighboursNames(ArrayList<String> neighboursNames) {
		this.neighboursNames = neighboursNames;
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
		if (neighbours.size() > 0) 
		{
			for (Country c : neighbours) 
			{
				nameList.add(c.getName());
			}
		}
		return "Country= " + this.getName() + " player=" ;
		// player.getName()
		//return "Territory [name=" + name + ", continent=" + cont + ", centerY=" + centerY + ", centerX=" + centerX
		//+ ", linkes=" + linkNames + "]";
	}

	public int getArmyNumber() {
		return armyNumber;
	}

	public void setArmyNumber(int armyNumber) {
		this.armyNumber = armyNumber;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
