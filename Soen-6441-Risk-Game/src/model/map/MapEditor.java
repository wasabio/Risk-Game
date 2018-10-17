package model.map;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.StringTokenizer;

import model.map.Map;
import model.map.Country;
import model.map.Continent;
/**
 * The class for map editing, such changing the inputs of a country, continent, or other type of value
 * It also include saving map files
 * @author skyba
 *
 */
public class MapEditor extends Observable{
	
	private Map map = new Map();
	
	/**
	 * Create a new and empty map. editing maps use load function
	 *
	 */
	public MapEditor() {
		clear();
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
		if (map.findContinent(cont.getName()) == null) {
			map.continents.add(cont);
			changeState();
		}
	}

	/**
	 * Add a qualified territory to the local variables, then build the
	 * neighbour links.
	 * 
	 * @param ter
	 *            The input territory.
	 * @throws IOException 
	 * 
	 */
	public void addCountry(Country Ctry) throws IOException {
		if (map.findCountry(Ctry.getName()) == null) {
			map.countries.add(Ctry);
			ArrayList<String> neighboursNames = Ctry.neighboursNames;
			if (neighboursNames.size() > 0) {
				for (String name : neighboursNames) {
					Country neighbour = map.findCountry(name);
					if (neighbour != null) {
						neighbour.linkTo(neighbour);
						buildCountryNeighbour(neighbour);
					}
				}
			}
			changeState();
		}
	}
	
	public void deleteContinent(Continent cont) 
	{
		
		
	}
	
	public void deleteCountry(Country Crty)
	{
		
		
	}
	/**
	 * method building connections among territories, each territory has a
	 * parameters of its linked territories
	 * 
	 * @param c c is meaning country
	 * @throws IOException 
	 */
	public void buildCountryNeighbour(Country c) throws IOException {
		if (map.findCountry(c.getName())!= null) {
			Set<String> set = new HashSet<>();
			for (String neighbourName : c.getNeighboursNames()) {
				if (neighbourName.length() > 0) {
					set.add(neighbourName);
				}
			}
			c.setNeighboursNames(new ArrayList<String>(set));

			c.neighbours = new ArrayList<Country>();
			if (c.getNeighboursNames().size() > 0) {
				for (String NeighboursName : c.getNeighboursNames()) {
					Country neighbour = map.findCountry(NeighboursName);
					c.getNeighboursNames().add(neighbour.getName());
				}

			}
		}
	}
	
	
	/**
	 * clear the variables in the file for new map to create, if need to edit map files, please use the load function
	 */
	public void clear() {
		map.setMapFilePath(null);
		map.setAuthor(null);
		map.setScroll(null);
		map.setWrap(false);
		map.setWarn(true);
		map.continents.clear();
		map.countries.clear();
		changeState();
	}
	
	/**
	 * This method is for updating the continent information. changing old name to new one, and also the extra armies player will get
	 * 
	 * @param oldName
	 * @param newName
	 * @param extraArmies
	 */
	public void updateContinent(String oldName, String newName, int extraArmies) 
	{
		Continent continent = map.findContinent(oldName);
		continent.setName(newName);
		continent.setExtraArmies(extraArmies);
		changeState();
	}

	/**
	 * The method for updating the country data when changed
	 * 
	 * @param oldName
	 * @param newName
	 * @param newXLocation
	 * @param newYLocation
	 * @param continent
	 * @throws IOException
	 */
	public void updateCountry(String oldName, String newName, int newXLocation, int newYLocation, Continent continent) throws IOException 
	{
		Country country = map.findCountry(oldName);
		country.setName(newName);
		country.setXLocation(newXLocation);
		country.setYLocation(newYLocation);
		country.setContinent(continent);	
		country.setNeighboursNames(new ArrayList<String>());
		changeState();
	}
	

}


