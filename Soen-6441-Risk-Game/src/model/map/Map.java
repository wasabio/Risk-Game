package model.map;

import java.util.ArrayList;

import model.utilities.FileHandler;

public class Map {
	
	private ArrayList<Continent> continents = new ArrayList<Continent>();
	
	private String name;

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

}
