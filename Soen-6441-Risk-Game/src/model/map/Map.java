package model.map;

import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.StringTokenizer;

import model.gameplay.Player;
import model.utilities.Random;
import model.utilities.StringAnalyzer;

/**
 * This is the class that includes most logic functions for map
 * It includes the functions like :
 * 1. The functions of data changes for the map that is dealing with Continents, countries, and players data
 * 2. The saving, and loading functions of the map file
 * 3. The checking functions for checking the string properties of the map file is suitable to the program or not
 */
public class Map extends Observable 
{
	
	public ArrayList<Continent> continents = new ArrayList<Continent>();
	public ArrayList<Country> countries = new ArrayList<Country>();
	public ArrayList<Player> players = new ArrayList<Player>();
	private String name;
	private String mapFilePath;
	private String imageFilePath;
	private boolean wrap;
	private String scroll;
	private String author;
	private boolean warn;
	private int playerNumber;
	
	/**
	 * The method for loading the map file and checking the syntax of the map file is suit to the program or not
	 * @param mapFilePath The file path of the map file in string type
	 * @throws IOException
	 */
	public void load(String mapFilePath) throws IOException 
	{	
		this.mapFilePath = mapFilePath;
		LineNumberReader in = new LineNumberReader(new FileReader(mapFilePath));
		loadMapSection(in);
		loadContinents(in);
		loadCountries(in);
		check();
	}

	/**
	 * check if the map is valid by: 
	 * 1. Checking if the map is empty, 
	 * 2. Checking if a country without any neighbor
	 * 3. Checking if a continent without any country
	 * @return Returning true when 3 checking functions all passed, and other situations return false
	 */
	public boolean check() 
	{		
		return (checkPlayableMap() && checkConnectedGraph() && checkNoEmptyContinent()) ;
	}
	
	/**
	 * check if there is any country and continent in the map 
	 * check if the total country number is more than total player number
	 * @return true if the map is valid, otherwise false
	 */
	public boolean checkPlayableMap() 
	{
		/* check does map have any country and continent */
		if (this.countries == null || this.countries.size() == 0 || this.continents == null
				||this.continents.size() == 0) 
		{
			System.out.println("There is no country or continent in the map");
			return false;
		}
		/*check if the total number of country is more than total number of player*/
		else if (this.players.size()>this.countries.size()) 
		{
			System.out.println("There are not enough country for all players");
			return false;
		}
		else return true;
	}
	

	/**
	 * The method is to check if there is any disconnected countries in the map which does not have any neighbor
	 * @return Returning false if such countries exists, otherwise true
	 */
	public boolean checkConnectedGraph() 
	{
		for(Continent ct : continents) 
		{
			for (Country c : ct.countries) 
			{
				for(Country n : c.neighbors) 
				{
					if(n.neighbors == null || n.neighbors.size() == 0 )
					{
						System.out.println("A country doesnt have neighbor");
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * The method is to check if there are empty continents which does not have a country signed under those continents
	 * @return Returning false if such continents exist, otherwise true
	 * check if there is empty continent which does not have any country
	 * @return false if such continent exists, otherwise false
	 */
	public boolean checkNoEmptyContinent() 
	{
		for(Continent ct : continents) 
		{
			if(ct.countries == null || ct.countries.size() == 0)
			{
				System.out.print("There is no country in the continent");
				return false;
			}
		}
		return true; 
	}
	
	/**
	 * The method is to load and check the basic properties of the map file has what the program needed
	 * @param in The string type in the map file that need to be read
	 * @throws IOException
	 */
	private void loadMapSection(LineNumberReader in) throws IOException 
	{
		reachSection(in, "Map");
		while(true) 
		{
			String line = in.readLine();
			if (line == null) 
			{
				throw new IOException("[Continents] Section expected; found EOF");
			}
			if (!line.trim().equals("")) 
			{
				if (line.startsWith("[")) 
				{
					if (line.equalsIgnoreCase("[Continents]")) 
					{
						return;
					}
					throw new IOException("[Continents] Section expected; found " + line);
				}
				String[] pair = line.split("=", 2);
				if ((pair != null) && (pair.length == 2)) 
				{
					String prop = pair[0].toLowerCase();
					String val = pair[1];
					if ("image".equals(prop)) 
					{
						if ((val != null) && (val.length() > 0)) 
						{
							this.imageFilePath = (new File(this.mapFilePath).getParent() + "\\" + val);
						}
					} 
					else if ("wrap".equals(prop)) 
					{
						this.wrap = val.equalsIgnoreCase("yes");
					} 
					else if ("scroll".equals(prop)) 
					{
						this.scroll = val;
					} 
					else if ("author".equals(prop)) 
					{
						this.author = val;
					} 
					else if ("warn".equals(prop)) 
					{
						this.warn = val.equalsIgnoreCase("yes");
					}
				}
			}
		}
	}

	/**
	 * The method is to load and check the loaded continent strings in the map file are suitable to the program or not
	 * @param in The continent string type in the map file that need to be read
	 * @throws IOException
	 */
	private void loadContinents(LineNumberReader in) throws IOException 
	{
		while(true) 
		{
			String line = in.readLine();
			if (line == null) 
			{
				throw new IOException("[Territories] Section expected; found EOF");
			}
			if (!line.trim().equals("")) 
			{
				if (line.startsWith("[")) 
				{
					if (line.equalsIgnoreCase("[Territories]")) 
					{
						return;
					}
					throw new IOException("[Territories] Section expected; found " + line);
				}
				int eqloc = line.indexOf("=");
				if (eqloc == -1) 
				{
					throw new IOException("Invalid continent line: " + line);
				}
				String cname = line.substring(0, eqloc).trim();
				int cbonus = StringAnalyzer.parseInt(line.substring(eqloc + 1).trim(), -1);
				if ((cname.length() < 1) || (cbonus == -1)) 
				{
					throw new IOException("Invalid continent line: " + line);
				}
				this.continents.add(new Continent(cname, cbonus));
			}
		}
	}
	
	/**
	 * The method is to load and check the loaded country strings in the map file are suitable to the program or not
	 * @param in The country string type in the map file that need to be read
	 * @throws IOException
	 */
	private void loadCountries(LineNumberReader in) throws IOException 
	{
		while(true) 
		{
			String line = in.readLine();
			if (line == null) 
			{
				break;
			}
			if (!line.trim().equals("")) 
			{
				parseCountryLine(line);
			}
		}
		
		for (Country c : countries) 
		{
			for(String n : c.neighborsNames) 
			{
				c.linkTo(findCountry(n));
			}
		}
	}
	
	/**
	 * The method is to find the specific country and its information
	 * @param name The specific country name in string type
	 * @return Returning to the current country in country type if the condition is correct, otherwise print incorrect map file with the current country name
	 * @throws IOException
	 */
	private Country findCountry(String name) throws IOException 
	{
		for (Country c : countries) 
		{
			if(c.getName().equals(name)) 
			{
				return c;
			}
		}
		throw new IOException("Incorrect map file (" + name + "," + ")");
	}
	
	/**
	 * The method is to parse the neighbors of the current country with separated by ",".
	 * It also will check some conditions like the current country is exist or not and the correctness of the coordinates (x,y)
	 * @param line This is the string line of neighbors of the current country
	 * @throws IOException
	 */
	private void parseCountryLine(String line) throws IOException 
	{
		try 
		{
			StringTokenizer st = new StringTokenizer(line, ",");
			Country ctry = new Country();
			ctry.setName(st.nextToken().trim());
			ctry.setCenter(Integer.parseInt(st.nextToken().trim()), Integer.parseInt(st.nextToken().trim()));

			if (st.hasMoreTokens()) 
			{
				String name = st.nextToken().trim();
				ctry.setContinent(findContinent(name));
				if ((ctry.getName() == null) || (ctry.getName().length() < 0)) 
				{
					throw new Exception("country name not found");
				}
				if ((ctry.getXLocation() == -1) || (ctry.getYLocation() == -1)) 
				{
					throw new Exception("invalid coordinates");
				}
				
				if (ctry.getContinent() != null && !name.equals("")) 
				{
					ctry.getContinent().countries.add(ctry);
					countries.add(ctry);
				}
				while (st.hasMoreTokens()) 
				{
					ctry.neighborsNames.add(st.nextToken().trim());
				}
			}
			
		} catch (Exception e) 
		{
			throw new IOException(" :Invalid country line (" + e + "): " + line);
		}
	}
	
	/**
	 * The method is to find the specific continent and its information
	 * @param name The specific country name in string type
	 * @return Returning to the current continent in continent type if the condition is correct, otherwise returning null
	 */
	private Continent findContinent(String name) 
	{
		for (Continent cont : this.continents) 
		{
			if (name.equalsIgnoreCase(cont.getName())) 
			{
				return cont;
			}
		}
		return null;
	}

	//QA
	/**
	 * The method is to make a pointer for the LineNumberReader to point the target section in the map file
	 * @param in The string line number that need to be read 
	 * @param section The part of the string in the map file that is selected as the target
	 * @return Returning the LineNumberReader type "in" while the read file string is not equal to the head
	 * @throws IOException
	 */
	private int reachSection(LineNumberReader in, String section) throws IOException 
	{
		String head = "[" + section + "]";
		String line;
		do 
		{
			line = in.readLine();
			if (line == null) 
			{
				throw new EOFException("EOF encountered before section " + head + " found");
			}
		} while (!line.equalsIgnoreCase(head));
		return in.getLineNumber();
	}

	/**
	 * The method is to get the current player number in the game
	 * @return Returning the current player number in the game
	 */
	public int getPlayerNumber() 
	{
		return playerNumber;
	}

	/**
	 * The method is to set the current player number in the game
	 * @param playerNumber The current total player number of the game with int type
	 */
	public void setPlayerNumber(int playerNumber) 
	{
		this.playerNumber = playerNumber;
	}
	
	/**
	 * The method is to set the current player number that selected by the user to the game
	 * It also contains the function to assign the initial armies for each player
	 * @param playerNumber The number of players that are selected with int type
	 */
	public void setPlayers(int playerNumber) 
	{
		setPlayerNumber(playerNumber);
		int armiesNumber = getInitialArmiesNumber();
		
		for(int i = 1; i <= playerNumber; i++) 
		{
			players.add(new Player(i, armiesNumber));
		}
	}

	/**
	 * To generate an initial armies number depending on the number of players
	 * 
	 * @return the initial number of armies
	 */
	/**
	 * The method is to generate initial number of armies for each player when the game start
	 * It also includes the condition for different player number with different initial armies number for each player
	 * @return Returning different army number with different cases:
	 * 	case 2 with 12 army each player, 
	 * 	case 3 with 35 army each player, 
	 *  case 4 with 30 army each player, 
	 *  case 5 with 25 army each player, 
	 *  case 6 with 20 army each player, 
	 *  others Return 0; 
	 */	
	private int getInitialArmiesNumber() 
	{
		switch(this.playerNumber) 
		{
		case 2:
			return 12;
		case 3:
			return 35;
		case 4:
			return 30;
		case 5:
			return 25;
		case 6:
			return 20;
		}
		return 0;
	}
	
	/**
	 * The method of this function is to distribute the countries on the map between the players.
	 * It will deploy 1 army of each player as take turns until all the countries are owned by players
	 */
	public void distributeCountries() 
	{
		ArrayList<Country> freeCountries = new ArrayList<Country>(countries);

		do 
		{
			for(Player p : players) 
			{
				if(freeCountries.size() > 0) 
				{
					int i = Random.getRandomIndex(0, freeCountries.size()-1); //Random assignment
					Country c = freeCountries.remove(i);
					c.setPlayer(p);
					c.setArmyNumber(1);
					p.ownedCountries.add(c);
					p.setArmies(p.getArmies()-1);
				}
			}
		}while(freeCountries.size() > 0);
		
		setChanged();
		notifyObservers(this);
	}

	/**
	 * The method is to check whether the country owned by the correct player. 
	 * Returning false if the match is incorrect, and returning true if the match is correct.
	 * @return Returning false if the country is not owned by the correct owned player, otherwise returning true.
	 */
	public boolean isOwned() 
	{
		Player owner = countries.get(0).getPlayer();
		for(Country c : countries) 
		{
			if(c.getPlayer() != owner) 
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 * This method is to add armies to selected country that is owned by current player.
	 * It includes functions of showing the remaining armies that can be deployed
	 *  and change the army number of the selected country. 
	 * @param ctryId The selected country ID with int type
	 * @param armieNumber The army number that the current player wants to deploy to the selected country
	 */
	public void addArmiesToCountry(int ctryId, int armieNumber) 
	{
		Player p = countries.get(ctryId-1).getPlayer();
		System.out.println(p.getArmies());
		p.setArmies(p.getArmies() - armieNumber);
		int oldArmies = countries.get(ctryId-1).getArmyNumber();
		countries.get(ctryId-1).setArmyNumber(oldArmies + armieNumber);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * The method is to calculate the country number that the current player owned.
	 * It also calculate the number of deployable armies that the current player can get
	 * @param p The current player
	 * @return Returning the number of deployable armies that the current player left
	 */
	public int calculateArmyNum(Player p) 
	{
		int numOfCty = 0;
		/* Search how many countries are owned by current player */
		for(Country c : countries) {
			if(p.getNumber() == c.getPlayer().getNumber()) 
			{
				numOfCty++;
			}
		}
		/* calculate how many armies the player should have */
		int numOfArmy = numOfCty/3;
		int bonusArmies = wholeContinentBonus(p);
		if((numOfArmy + bonusArmies) < 3) 
		{
			return 3;
		}
		return (numOfCty/3+bonusArmies);
	}
	
	/**
	 * The method is to check whether the player can take over the whole continent
	 * @param p The current player
	 * @return Returning the number of extra armies that the player will get for owned the current continent
	 */
	public int wholeContinentBonus(Player p)
	{
		int bonusArmies = 0;
		/* search continents */
		for(Continent conti: continents) 
		{
			/* if the player owns current continent, get bonus armies */
			 if(conti.ownedBy(p)) 
			 {
				 bonusArmies += conti.getExtraArmies();
			 }
		}
		return bonusArmies;
	}
	
	//QA
	/**
	 * The method is to clear the data information of the country
	 */
	public void clear() 
	{
		Country.Counter = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
