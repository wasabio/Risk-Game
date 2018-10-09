package model.map;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.utilities.FileHandler;
import model.utilities.StringAnalyzer;

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



public class Map {
	
	private ArrayList<Continent> continents = new ArrayList<Continent>();
<<<<<<< HEAD
	public ArrayList<Country> Countries = new ArrayList<Country>();
	private String name;
	private String mapFilePath;
	private String imageFilePath;
	
	
=======
	
	private String mapFilePath;
	private String imageFilePath;
	private boolean wrap;
	private String scroll;
	private String author;
	private boolean warn;
>>>>>>> master

	public void load(String mapFilePath) throws IOException 

	{	
		this.mapFilePath = mapFilePath;
		LineNumberReader in = new LineNumberReader(new FileReader(mapFilePath));
		loadMapSection(in);
		loadContinents(in);
		loadCountries(in);
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
	
	private Country findCountry(String name) throws IOException {
		for(Continent ct : continents) {
			for (Country c : ct.countries) {
				if(c.name.equals(name)) {
					return c;
				}
			}
		}
		throw new IOException("Incorrect map file (" + name + "," + ")");
	}

	private Country parseCountryLine(String line) throws IOException {
		try {
			StringTokenizer st = new StringTokenizer(line, ",");
			Country ctry = new Country();
			ctry.name = st.nextToken().trim();
			ctry.setCenter(Integer.parseInt(st.nextToken().trim()), Integer.parseInt(st.nextToken().trim()));

			if (st.hasMoreTokens()) {
				String name = st.nextToken().trim();
				ctry.setContinent(findContinent(name));
				if ((ctry.name == null) || (ctry.name.length() < 0)) {
					throw new Exception("country name not found");
				}
				if ((ctry.getX() == -1) || (ctry.getY() == -1)) {
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

<<<<<<< HEAD
	/**
	 * check whether a map contains two different format files including .map
	 * text file and .bmp image file.
	 * 
	 * @return Return true if two different files are matched exactly, other
	 *         wise return false.
	 */
	public boolean isDisparateImageFileDirectory() {
		if (this.imageFilePath == null || this.mapFilePath == null) {
			return false;
		}
		File mapDir = new File(this.mapFilePath).getParentFile();
		File imgDir = new File(this.imageFilePath).getParentFile();
		return (mapDir != null) && (imgDir != null) && (mapDir.compareTo(imgDir) != 0);
	}
	
}
=======
	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}
		
}
>>>>>>> master
