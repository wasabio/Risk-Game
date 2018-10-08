package model.map;

import java.util.ArrayList;

public class Continent {

	private String name;
	private int bonus;
	
	private ArrayList<Country> countries = new ArrayList<Country>();
	
	public Continent(String new_name, int new_bonus) {
		this.name = new_name;
		this.bonus = new_bonus;
	}
}
