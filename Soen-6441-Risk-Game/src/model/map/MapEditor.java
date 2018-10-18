package model.map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This class is for functions such as add, delete continents and countries in the map file.
 * 
 *
 */
public class MapEditor extends Observable{
	public Map map;

	/**
	 * Create a new and empty map. If need to load existing map, please see
	 * function load().
	 */
	public MapEditor() {
		map = new Map();
		map.clear();
		map.setPlayerNumber(0);
	}

	/**
	 * If the data has been changed, call this function to notify observers.
	 */
	public void changeState() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Add a qualified continent to the local variables.
	 * 
	 * @param cont
	 *            The input continent.
	 */
	public void addContinent(Continent cont) {
		if (findContinent(cont.getName()) == null) {
			map.continents.add(cont);
			changeState();
		}
	}

	/**
	 * Delete the input continent, and remove the countries inside and their links
	 * 
	 * @param contNumber
	 *            The target continent needed to remove.
	 */
	public boolean deleteContinent(int contNumber) {
		
		Continent c = getContinent(contNumber);
		/* if the continent exists */
		if(c != null) {
			map.continents.remove(c);
			changeState();
			return true;
		}
		return false;
	}
	
	/**
	 * Add a qualified Country to the local variables, then build the
	 * neighbor links.
	 * 
	 * @param ter
	 *            The input Country.
	 * 
	 */
	public void addCountry(Country ctry) {
		/* If country name not already exists */
		if (findCountry(ctry.getName()) == null) {
			this.map.countries.add(ctry);
			/* Adding neighbors */
			ArrayList<String> neighborNames = ctry.neighborsNames;
			if (neighborNames.size() > 0) {
				for (String name : neighborNames) {
					Country neighbor = findCountry(name);
					if (neighbor != null) {
						neighbor.neighborsNames.add(ctry.getName());
						connect(ctry, neighbor);
					}
				}
			}
			changeState();
		} else {
			Country.Counter--;
		}
	}

	/**
	 * Delete the target Country, also remove all links in its neighbors.
	 * 
	 * @param ter
	 *            The target Country needed to be removed.
	 * @return 
	 */
	public boolean deleteCountry(int ctryNumber) {
		Country c = getCountry(ctryNumber);
		/* if the country exists */
		if(c != null) {
			deleteCountry(c);
			return true;
		}
		return false;
	}
	
	/**
	 * The method for deleting country in the map file
	 * @param c the selected country
	 */
	public void deleteCountry(Country c) {
		if (map.countries.contains(c)) {
			disconnect(c);
			c.getContinent().countries.remove(c);
			map.countries.remove(c);
		}
		changeState();
	}
	
	/**
	 * Find and return a continent giving a name. In case is does not exist, return null
	 * 
	 * @return the continent found
	 */
	private Continent findContinent(String name) {
		for(Continent c : map.continents) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Find and return a country giving a name. In case is does not exist, return null
	 * 
	 * @return the country found
	 */
	private Country findCountry(String name) {
		for(Country c : map.countries) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Create a connection between 2 countries. It generate a link in both side
	 */
	private void connect(Country ctry, Country neighbor) {
		ctry.linkTo(neighbor);
		neighbor.linkTo(ctry);
	}
	
	/**
	 * To remove all the connections made between a specific country and the other countries
	 * @param c country to remove
	 */
	private void disconnect(Country c) {
		for(Country n : c.neighbors) {
			n.neighbors.remove(c);
			n.neighborsNames.remove(c.getName());
		}
		c.neighbors.clear();
		c.neighborsNames.clear();
	}

	/**
	 * Setting the map name
	 * @param mapName The current map name
	 */
	public void setMapName(String mapName) {
		map.setName(mapName);
	}

	/**
	 * The loading function for map files
	 * @param mapFilePath The selected map file path with string type
	 */
	public void load(String mapFilePath) {
		try {
			map.load(mapFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Translate the number displayed and entered by the user to the selected country
	 * @param inputNumber number entered by the user
	 */
	public Country getCountry(int inputNumber) {
		int counter = 0;
		for(Continent c : map.continents) {
			counter++;
			for(Country ctry : c.countries) {
				counter++;
				if(counter == inputNumber) {
					return ctry;
				}
			}
		}
		return null;
	}
	
	/**
	 * Translate the number displayed and entered by the user to the selected continent
	 * @param inputNumber number entered by the user
	 */
	public Continent getContinent(int inputNumber) {
		int counter = 0;
		for(Continent c : map.continents) {
			counter++;
			if(counter == inputNumber) {
				return c;
			}
			for(Country ctry : c.countries) {
				counter++;
			}
		}
		return null;
	}

	/**
	 * The method for adding countries and the neighbor
	 * @param ctryName The target country with string type
	 * @param contNb The number of continents
	 * @param neighborNumbers Neighbor numbers of a country
	 * @return
	 */
	public boolean addCountry(String ctryName, int contNb, ArrayList<Integer> neighborNumbers) {
		/* Check if there is an other country with the same name */
		if(findCountry(ctryName) != null) {
			return false;
		}
		/* Check if the continent number is correct */
		Continent c = getContinent(contNb);
		if(c == null) {
			return false;
		}
		/* Check for incorrect neighbors */
		ArrayList<Country> neighbors = new ArrayList<Country>();
		for(int i : neighborNumbers) {
			Country n = getCountry(i);
			if(n == null) {
				return false;
			} else {
				neighbors.add(n);
			}
		}
		
		/* add country & create neighbors */
		Country ctry = new Country(ctryName);
		map.countries.add(ctry);
		c.countries.add(ctry);
		ctry.setContinent(c);
		for(Country neighbor : neighbors) {
			connect(ctry, neighbor);
		}
		
		changeState();
		return true;
	}

	/**
	 * Calculate the number of countries and continent that are displayed, so the user can't choose a greater number.
	 * @return the max possible input.
	 */
	public int getMaxInputNumber() {
		int counter = 0;
		for(Continent c : map.continents) {
			counter++;
			for(Country ctry : c.countries) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * 
	 * @param countinentName
	 * @param bonus
	 */
	public boolean addContinent(String continentName, int bonus) {
		/* if continent name not already existing */
		if(findContinent(continentName) != null) {
			return false;
		}
		Continent c = new Continent(continentName, bonus);
		map.continents.add(c);
		changeState();
		
		return true;
	}
		
	/**
	 * save and generate the .map file
	 */
	public void save() {
		map.check();
		String content = extractInfo(map);

		generate(map.getName(),content);
		System.out.println("Map successfully saved");
		System.out.print(content);

		generate(map.getName()+".map",content);

	}

	/**
	 * to extract map info into the string
	 * @param map the map that needs to be saved
	 * @return the string form of the map
	 */
	private String extractInfo(Map map) {
		if(map == null || map.continents.size() ==0) return null;
		String content = "[Map]\n"; 
		content += "image="+map.getName()+".bmp\n";
		content += "warn=yes\n";
		content += "author=Team 14\n";
		content += "scroll=horizontal\n";
		content += "wrap=no\n\n";
		content += "[Continents]\n";
		for(Continent con : map.continents) {
			content += con.getName()+"="+con.getExtraArmies()+"\n";
		}
		content += "\n";
		content +="[Territories]\n";
		int contCounter = 0;
		int counCounter = 0;
		for(Continent con : map.continents) {
			for(Country c : con.countries) {
				content += c.getName()+",0,0,"+c.getContinent().getName();
				for(Country cou1 : c.neighbors) {
					content += ","+cou1.getName();
				}
				if(contCounter != map.continents.size()-1 &&  counCounter != c.neighbors.size() ) {}
				else content += "\n";
				counCounter++;
			}
			if(contCounter < map.continents.size()-1) {
				content += "\n";
			}
			contCounter++;
		}
		return content;
	}

	/**
	 * The function to generate the new file name and new properties that content in the map file
	 * @param fileName The selected file name
	 * @param content The string that content in the file
	 */
	public static void generate(String fileName, String content) { 
		try {
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName)); 
		out.write(content); 

		out.close(); 
		}
		catch(IOException e) { 
		System.out.println(e); 
		 } 
	}
}
