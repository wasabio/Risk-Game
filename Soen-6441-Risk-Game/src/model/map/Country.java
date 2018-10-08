package model.map;

import java.util.ArrayList;

public class Country {
	
	public String name;
	private Continent continent;
	private int x = -1;
	private int y = -1;
	public ArrayList<Country> neighbours = new ArrayList<Country>();
	public ArrayList<String> neighboursNames = new ArrayList<String>();
	
	public void setCenter(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void linkTo(Country neighbour) {
		this.neighbours.add(neighbour);
	}

}
