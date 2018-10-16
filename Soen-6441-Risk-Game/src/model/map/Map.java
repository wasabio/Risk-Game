package model.map;

import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.StringTokenizer;



import java.util.Comparator;
import java.util.HashSet;

import model.gameplay.Player;
import model.utilities.Random;
import model.utilities.StringAnalyzer;


/**
 *  The class for dealing with map functions such as loading maps, map logics
 * @author skyba, Yann
 *
 */

public class Map extends Observable implements Comparator<Object> 
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
	 * The method of observer pattern for updating
	 */
	public void changeState() 
	{
		setChanged();
		notifyObservers();
	}
	
	/**
	 * The method that load the map file path and check the sytax of continents, countries suit or not.
	 * @param mapFilePath
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

	private void check() 
	{
		// Yueshuai implementation : 3 map correctness checking + check if map playable regarding playerNumber
		
	}

	/**
	 * Method for checking the loaded map is suit for the program or not
	 * @param in
	 * @throws IOException
	 */
	private void loadMapSection(LineNumberReader in) throws IOException {
		reachSection(in, "Map");
		while(true) {
			String line = in.readLine();
			if (line == null) {
				throw new IOException("[Continents] Section expected; found EOF");
			}
			if (!line.trim().equals("")) {
				if (line.startsWith("[")) {
					if (line.equalsIgnoreCase("[Continents]")) {
						return;
					}
					throw new IOException("[Continents] Section expected; found " + line);
				}
				String[] pair = line.split("=", 2);
				if ((pair != null) && (pair.length == 2)) {
					String prop = pair[0].toLowerCase();
					String val = pair[1];
					if ("image".equals(prop)) {
						if ((val != null) && (val.length() > 0)) {
							this.imageFilePath = (new File(this.mapFilePath).getParent() + "\\" + val);
						}
					} else if ("wrap".equals(prop)) {
						this.wrap = val.equalsIgnoreCase("yes");
					} else if ("scroll".equals(prop)) {
						this.scroll = val;
					} else if ("author".equals(prop)) {
						this.author = val;
					} else if ("warn".equals(prop)) {
						this.warn = val.equalsIgnoreCase("yes");
					}
				}
			}
		}
	}

	/**
	 * The method checking continents suit or not to the program
	 * @param in
	 * @throws IOException
	 */
	private void loadContinents(LineNumberReader in) throws IOException {
		while(true) {
			String line = in.readLine();
			if (line == null) {
				throw new IOException("[Territories] Section expected; found EOF");
			}
			if (!line.trim().equals("")) {
				if (line.startsWith("[")) {
					if (line.equalsIgnoreCase("[Territories]")) {
						return;
					}
					throw new IOException("[Territories] Section expected; found " + line);
				}
				int eqloc = line.indexOf("=");
				if (eqloc == -1) {
					throw new IOException("Invalid continent line: " + line);
				}
				String cname = line.substring(0, eqloc).trim();
				int cbonus = StringAnalyzer.parseInt(line.substring(eqloc + 1).trim(), -1);
				if ((cname.length() < 1) || (cbonus == -1)) {
					throw new IOException("Invalid continent line: " + line);
				}
				this.continents.add(new Continent(cname, cbonus));
			}
		}
	}
	
	
	
	/**
	 * The method of checking countries is suit to the program or not
	 * @param in
	 * @throws IOException
	 */
	private void loadCountries(LineNumberReader in) throws IOException {
		Country ctry;
		while(true) {
			String line = in.readLine();
			if (line == null) {
				break;
			}
			if (!line.trim().equals("")) {
				ctry = parseCountryLine(line);
			}
		}
		
		for (Country c : countries) {
			for(String n : c.neighboursNames) {
				c.linkTo(findCountry(n));
			}
		}
	}
	
	/**
	 * The method of finding countries in the map file
	 * @param name country's name
	 * @return return to the country
	 * @throws IOException
	 */
	public Country findCountry(String name) throws IOException {
		for (Country c : countries) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		throw new IOException("Incorrect map file (" + name + "," + ")");
	}

	/**
	 * Count the number of current Countries in target continent.
	 * 
	 * @param cont
	 *            The target continent.
	 * @return The number of current territories.
	 */
		public int countCountries(Continent cont) {
			int total = 0;
			for (Country ctry : this.countries) {
				if (ctry.getContinent() == cont) {
					total++;
				}
			}
			return total;
		}
	
		/**
		 * The override function for Comparator, designed to compare the order of
		 * two countries.
		 */
		public int compare(Object o1, Object o2) {
			if ((o1 == null) && (o2 == null)) {
				return 0;
			}
			if (o1 == null) {
				return -1;
			}
			if (o2 == null) {
				return 1;
			}
			if (((o1 instanceof Country)) && ((o2 instanceof Country))) {
				Country a = (Country) o1;
				Country b = (Country) o2;
				if (a.getContinent() != b.getContinent()) {
					return compare(a.getContinent(), b.getContinent());
				}
				return a.getName().compareTo(b.getName());
			}
			return o1.toString().compareTo(o2.toString());
		}	

/**
 * 		
 * @param line
 * @return
 * @throws IOException
 */
	private Country parseCountryLine(String line) throws IOException {
		try {
			StringTokenizer st = new StringTokenizer(line, ",");
			Country ctry = new Country();
			ctry.setName(st.nextToken().trim());
			ctry.setCenter(Integer.parseInt(st.nextToken().trim()), Integer.parseInt(st.nextToken().trim()));

			if (st.hasMoreTokens()) {
				String name = st.nextToken().trim();
				ctry.setContinent(findContinent(name));
				if ((ctry.getName() == null) || (ctry.getName().length() < 0)) {
					throw new Exception("country name not found");
				}
				if ((ctry.getXLocation() == -1) || (ctry.getYLocation() == -1)) {
					throw new Exception("invalid coordinates");
				}
				if (ctry.getContinent() != null && !name.equals("")) {
					ctry.getContinent().countries.add(ctry);
					countries.add(ctry);
				}
				while (st.hasMoreTokens()) {
					ctry.neighboursNames.add(st.nextToken().trim());
				}
			}
			return ctry;
		} catch (Exception e) {
			throw new IOException(" :Invalid country line (" + e + "): " + line);
		}
	}
	
	/**
	 * find continents in the map file
	 * @param name continent's name
	 * @return
	 */
	
	public Continent findContinent(String name) {
		for (Continent cont : this.continents) {
			if (name.equalsIgnoreCase(cont.getName())) {
				return cont;
			}
		}
		return null;
	}

	
	
	private int reachSection(LineNumberReader in, String section) throws IOException {
		String head = "[" + section + "]";
		String line;
		do {
			line = in.readLine();
			if (line == null) {
				throw new EOFException("EOF encountered before section " + head + " found");
			}
		} while (!line.equalsIgnoreCase(head));
		return in.getLineNumber();
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	/**
	 * The players are created and they are attributed an initial number of armies
	 */
	public void setPlayers(int playerNumber) {
		setPlayerNumber(playerNumber);
		
		int armiesNumber = getInitialArmiesNumber();
		
		for(int i = 1; i <= playerNumber; i++) {
			players.add(new Player(i, armiesNumber));
		}
	}

	/**
	 * To generate an initial armies number depending on the number of players
	 * 
	 * @return the initial number of armies
	 */
	private int getInitialArmiesNumber() {
		switch(this.playerNumber) {
		case 2:
			return 40;
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
	 * This function will distribute the countries on the map between the players. It will put
	 * 1 army by country until all the countries are occupied.
	 */
	public void distributeCountries() {
		ArrayList<Country> freeCountries = new ArrayList<Country>(countries);

		do {
			for(Player p : players) {
				if(freeCountries.size() > 0) {
					int i = Random.getRandomIndex(0, freeCountries.size()-1); //Random assignment
					Country c = freeCountries.remove(i);
					c.setPlayer(p);
					c.setArmyNumber(1);
					p.ownedCountries.add(c);
				}
			}
		}while(freeCountries.size() > 0);
		
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * input the author of the edited map file, change the map information.
	 * 
	 * @param author
	 */
	public final void setAuthor(String author) 
	{
			this.author = author;
			changeState();
	}

	/**
	 * confirming if it is scroll.
	 * 
	 * @param scroll
	 */
	public final void setScroll(String scroll) 
	{
		if (this.scroll != scroll) 
		{
			this.scroll = scroll;
			changeState();
		}
	}

	/**
	 * confirming if it is warn.
	 * 
	 * @param warn
	 */
	public final void setWarn(boolean warn) 
	{
		if (warn != this.warn) 
		{
			this.warn = warn;
			changeState();
		}
	}

	/**
	 * confirming if it is warp.
	 * 
	 * @param wrap
	 */
	public final void setWrap(boolean wrap) 
	{
		if (wrap != this.wrap) 
		{
			this.wrap = wrap;
			changeState();
		}
	}
	
	/**
	 * input the Continent name, it can not be null, and change the map
	 * information
	 * 
	 * @param cont
	 * @param name
	 */
	public void setContinentName(Continent cont, String name) 
	{
		if (name != null) {
			cont.setName(name);
			changeState();
		}
	}

	/**
	 * input the Country name, it can not be null, and change the map
	 * @param ctry
	 * @param name
	 */
		public void setCountryName(Country ctry, String name) 
		{
			if (name != null) {
				ctry.setName(name);
				changeState();
			}
		}
		
		/**
		 * save the image file in a path the user wants.
		 * 
		 * @param imageFilePath
		 * 
		 */
		//public void setImageFilePath(String imageFilePath) 
		//{
		//		this.imageFilePath = imageFilePath;
		//		changeState();
		//}

		/**
		 * save the map file path in a path the user wants
		 * 
		 * @param mapFilePath
		 */
		public void setMapFilePath(String mapFilePath) 
		{
			this.mapFilePath = mapFilePath;
		}

	
	
	/**
	 * Return the current Author.
	 * 
	 * @return The current Author.
	 */
	public final String getAuthor() {
		return this.author;
	}

	/**
	 * Return a unrepeated territory set of target continent.
	 * 
	 * @param cont
	 *            The target continent.
	 * @return Set of Countries in target continent.
	 */
	public HashSet<Country> getContinentCountries(Continent cont) {
		HashSet<Country> Ctries = new HashSet<>();
		for (Country Ctry : this.countries) {
			if (Ctry.getContinent() == cont) {
				Ctries.add(Ctry);
			}
		}
		return Ctries;
	}

	/**
	 * Return the current ImageFileName.
	 * 
	 * @return The current ImageFileName.
	 */
	//public String getImageFileName() {
	//	if (this.imageFilePath == null) {
	//		return "";
	//	}
	//	return new File(this.imageFilePath).getName();
	//}

	/**
	 * Return the current ImageFilePath.
	 * 
	 * @return The current ImageFilePath.
	 */
	//public String getImageFilePath() {
	//	return this.imageFilePath;
	//}

	/**
	 * Return the current MapDirectory.
	 * 
	 * @return The current MapDirectory.
	 */
	public File getMapPathFile() {
		if (this.mapFilePath == null) {
			return null;
		}
		return new File(this.mapFilePath).getParentFile();
	}

	/**
	 * Return the current MapFilePath.
	 * 
	 * @return The current MapFilePath.
	 */
	public String getMapFilePath() {
		return this.mapFilePath;
	}

	/**
	 * Return the current MapName.
	 * 
	 * @return The current MapName.
	 */
	public String getMapName() {
		if (this.mapFilePath == null) {
			return "Untitled Map";
		}
		return new File(this.mapFilePath).getName();
	}

	/**
	 * Return the current SaveImageFilePath.
	 * 
	 * @return The current SaveImageFilePath.
	 */
	//public String getSaveImageFilePath() {
	//	if (this.imageFilePath == null) {
	//		return "";
	//	}
	//	return getImageFileName();
	//}
	
	/**
	 * sorting continents if continents list is not null or empty
	 */
	void sortContinentsCollection() 
	{
		if ((this.continents != null) && (!this.continents.isEmpty())) 
		{
			Collections.sort(this.continents, this);
		}
	}

	/**
	 * sorting the territories list if it is not null or empty
	 */
	void sortCountriesCollection() 
	{
		if ((this.countries != null) && (!this.countries.isEmpty())) 
		{
			Collections.sort(this.countries, this);
		}
	}
	
}
