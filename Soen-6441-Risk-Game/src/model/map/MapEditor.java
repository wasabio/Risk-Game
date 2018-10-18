package model.map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * for functions such as add, delete continents and countries.
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
	 * @param cont
	 *            The target continent needed to remove.
	 */
	public void deleteContinent(Continent cont) {
		if(map.continents.contains(cont)) {
			for(Country c : cont.countries) {
				deleteCountry(c);
			}
			map.continents.remove(cont);
			changeState();
		}
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
	 */
	public void deleteCountry(Country ctry) {
		if (map.countries.contains(ctry)) {
			disconnect(ctry);
			ctry.getContinent().countries.remove(ctry);
			map.countries.remove(ctry);
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

	public void setMapName(String mapName) {
		map.setName(mapName);
	}

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

	public void addContinent(String countinentName, int bonus) {
		// TODO Auto-generated method stub
		
	}
		
	/**
	 * save and generate the .map file
	 */
	public void save() {
		map.check();
		String content = extractInfo(map);
		generate(map.getName()+".map",content);
	}
	
	private String extractInfo(Map map) {
		String content = "[Map]\n"; 
		content += "img="+map.getName()+".bmp\n\n";
		content += "[continents]\n\n";
		
		return null;
	}

	public static void generate(String fileName, String content) { 
		try {
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName)); 
		out.write(content); 
		out.newLine(); 
		out.close(); 
		}
		catch(IOException e) { 
		System.out.println(e); 
		 } 
	}
}
