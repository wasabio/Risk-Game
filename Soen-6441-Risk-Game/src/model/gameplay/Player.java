package model.gameplay;

import java.util.ArrayList;

import model.map.Country;

public class Player {
	private int number;
	private int armies;
	public ArrayList<Country> ownedCountries = new ArrayList<Country>();
	
	public Player(int new_number, int new_armies) {
		setNumber(new_number);
		setArmies(new_armies);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getArmies() {
		return armies;
	}

	public void setArmies(int armies) {
		this.armies = armies;
	}
	
	
}
