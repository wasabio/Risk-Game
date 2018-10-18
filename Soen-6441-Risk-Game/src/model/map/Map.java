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

public class Map extends Observable {
	
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
	 * check if the map is valid by : check if the map is empty, 
	 * check if the continent has any country,
	 * check if a country has any neighbor
	 * @author Yueshuai
	 * @return true if the map is valid, otherwise false
	 */
	public boolean check() {		
		return (checkPlayableMap() && checkConnectedGraph() && checkNoEmptyContinent()) ;
	}
	
	public boolean checkPlayableMap() {
		/* You also have to check if the number of players is lower or equals than the number of countries on the map */
		if(this.countries == null || this.countries.size() == 0) 
		{
			System.out.println("There is no country in the map");
			return false;
		}
		
		return true;
	}
	
	public boolean checkConnectedGraph() {
		for(Continent ct : continents) {
			for (Country c : ct.countries) {
				for(Country n : c.neighbors) {
					if(c.neighbors == null || c.neighbors.size() == 0 ){
						System.out.println("A country doesnt have neighbor");
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean checkNoEmptyContinent() {
		for(Continent ct : continents) {
			if(ct.countries == null || ct.countries.size() == 0)
			{
				System.out.print("There is no country in the continent");
				return false;
			}
		}
		return true; 
	}
	
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
	
	private void loadCountries(LineNumberReader in) throws IOException {
		while(true) {
			String line = in.readLine();
			if (line == null) {
				break;
			}
			if (!line.trim().equals("")) {
				parseCountryLine(line);
			}
		}
		
		for (Country c : countries) {
			for(String n : c.neighborsNames) {
				c.linkTo(findCountry(n));
			}
		}
	}
	
	private Country findCountry(String name) throws IOException {
		for (Country c : countries) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		throw new IOException("Incorrect map file (" + name + "," + ")");
	}

	private void parseCountryLine(String line) throws IOException {
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
					ctry.neighborsNames.add(st.nextToken().trim());
				}
			}
			
		} catch (Exception e) {
			throw new IOException(" :Invalid country line (" + e + "): " + line);
		}
	}
		
	private Continent findContinent(String name) {
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
					p.setArmies(p.getArmies()-1);
				}
			}
		}while(freeCountries.size() > 0);
		
		setChanged();
		notifyObservers(this);
	}

	public boolean isOwned() {
		Player owner = countries.get(0).getPlayer();
		for(Country c : countries) {
			if(c.getPlayer() != owner) {
				return false;
			}
		}
		
		return true;
	}

	public void addArmiesToCountry(int ctryId, int armieNumber) {
		Player p = countries.get(ctryId-1).getPlayer();
		System.out.println(p.getArmies());
		p.setArmies(p.getArmies() - armieNumber);
		int oldArmies = countries.get(ctryId-1).getArmyNumber();
		countries.get(ctryId-1).setArmyNumber(oldArmies + armieNumber);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * calculate number of countries the player owns, and calculate how many armies he should have left to deploy
	 * @param p the current player
	 * @return number of armies the player should have left to deploy
	 */
	public int calculateArmyNum(Player p) {
		int numOfCty = 0;
		/* Search how many countries are owned by current player */
		for(Country c : countries) {
			if(p.getNumber() == c.getPlayer().getNumber()) {
				numOfCty++;
			}
		}
		/* calculate how many armies the player should have */
		int numOfArmy = numOfCty/3;
		int bonusArmies = wholeContinentBonus(p);
		if((numOfArmy + bonusArmies) < 3) {
			return 3;
		}
		return (numOfCty/3+bonusArmies);
	}
	
	/**
	 * to check if the player take over the whole continent
	 * @param current player
	 * @return the number of bonus armies player get for taking whole continent
	 */
	public int wholeContinentBonus(Player p)
	{
		int bonusArmies = 0;
		/* search continents */
		for(Continent conti: continents) {
			/* if the player owns current continent, get bonus armies */
			 if(conti.ownedBy(p)) {
				 bonusArmies += conti.getExtraArmies();
			 }
		}
		return bonusArmies;
	}

	public void clear() {
		Country.Counter = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
