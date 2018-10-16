package model.map;

import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.utilities.StringAnalyzer;

/**
 *  The class for dealing with map functions such as loading maps, map logics
 * @author skyba, Yann
 *
 */
public class Map {
	
	public ArrayList<Continent> continents = new ArrayList<Continent>();
	public ArrayList<Country> countries = new ArrayList<Country>();
	private String name;
	private String mapFilePath;
	private String imageFilePath;
	private boolean wrap;
	private String scroll;
	private String author;
	private boolean warn;

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
		for(Continent ct : continents) {
			for (Country c : ct.countries) {
				for(String n : c.neighboursNames) {
					c.linkTo(findCountry(n));
				}
			}
		}
	}
	
	/**
	 * The method of finding countries in the map file
	 * @param name country's name
	 * @return return to the country
	 * @throws IOException
	 */
	private Country findCountry(String name) throws IOException {
		for(Continent ct : continents) {
			for (Country c : ct.countries) {
				if(c.getName().equals(name)) {
					return c;
				}
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
	
}
