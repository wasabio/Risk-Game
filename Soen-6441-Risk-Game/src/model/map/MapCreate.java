package model.map;

import java.util.ArrayList;

import MapEditor.Model.Continent;
import MapEditor.Model.Territory;
import MapEditor.Model.ConquestMap.ScrollOptions;

/**
 * for functions such as add, delete continents and countries.
 * @author skyba
 *
 */
public class MapCreate {

	/**
	 * Create a new and empty map. If need to load existing map, please see
	 * function load().
	 */
	public ConquestMap() {
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
		if (findContinent(cont.getName()) == null) {
			this.continents.add(cont);
			changeState();
		}
	}

	/**
	 * Add a qualified territory to the local variables, then build the
	 * neighbour links.
	 * 
	 * @param ter
	 *            The input territory.
	 * 
	 */
	public void addTerritory(Territory ter) {
		if (findTerritory(ter.name) == null) {
			this.territories.add(ter);
			ArrayList<String> linkNames = ter.getLinkNames();
			if (linkNames.size() > 0) {
				for (String name : linkNames) {
					Territory neighbour = findTerritory(name);
					if (neighbour != null) {
						neighbour.getLinkNames().add(ter.getName());
						buildTerritoryLinks(neighbour);
					}
				}
			}
			changeState();
		}
	}
	
	/**
	 * Initial function of the class, normalize the local variables.
	 */
	public void clear() {
		this.mapFilePath = null;
		this.imageFilePath = null;
		this.author = null;
		this.scroll = ScrollOptions.HORIZONTAL;
		this.wrap = false;
		this.warn = true;
		this.continents.clear();
		this.territories.clear();
		changeState();
	}
	
	/**
	 * Delete the input continent, and remove the link between continent and its
	 * territories,set null continent to these territories.
	 * 
	 * @param cont
	 *            The target continent needed to remove.
	 */
	public void deleteContinent(Continent cont) {
		if (this.continents.contains(cont)) {
			this.continents.remove(cont);
			ArrayList<Territory> temp = new ArrayList<>();
			for (Territory ter : this.territories) {
				if (ter.getContinent() == cont) {
					temp.add(ter);
				}
			}
			for (Territory ter : temp) {
				ter.setContinent(null);
			}
			changeState();

		}
	}

	/**
	 * Delete the target territory, also remove all links in its neighbours.
	 * 
	 * @param ter
	 *            The target territory needed to be removed.
	 */
	public void deleteTerritory(Territory ter) {
		if (this.territories.contains(ter)) {
			this.territories.remove(ter);
			ArrayList<String> linkNames = ter.getLinkNames();
			if (linkNames.size() > 0) {
				for (String name : linkNames) {
					Territory neighbour = findTerritory(name);
					if (neighbour != null) {
						neighbour.getLinkNames().remove(ter.getName());
						buildTerritoryLinks(neighbour);
					}
				}
			}
		}
		changeState();
	}
	
}
