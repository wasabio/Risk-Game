package model.map;

import java.util.ArrayList;

import model.utilities.FileHandler;

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
	public ArrayList<Country> Countries = new ArrayList<Country>();
	private String name;
	private String mapFilePath;
	private String imageFilePath;
	
	

	public void load() 
	{
		FileHandler fileHandler = new FileHandler();
		//this.name = fileHandler.select();
		fileHandler.read(this.name);
		
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
