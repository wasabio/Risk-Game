package model.gameplay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import model.map.Country;
import model.map.Map;
import model.utilities.Random;

/**
 * This class is dealing with each player's data changes like owned countries, armies, and cards
 * 
 */
public class Player extends Observable
{
	private int number;
	private int armies;
	public boolean gotCard = false;
	public ArrayList<Country> ownedCountries = new ArrayList<Country>();
	public ArrayList<Card> cards = new ArrayList<Card>();
	
	/**
	 * The Constructor method of player
	 * @param new_number new number of countries
	 * @param new_armies new number of armies
	 */
	public Player(int new_number, int new_armies) 
	{
		setNumber(new_number);
		setArmies(new_armies);
	}

	/**
	 * get player id
	 * @return return player id
	 */
	public int getNumber() 
	{
		return number;
	}
	
	/**
	 * set player id
	 * @param number the ID of the player
	 */
	public void setNumber(int number) 
	{
		this.number = number;
	}
	
	/**
	 * get method for the armies
	 * @return current armies number of the player
	 */
	public int getArmies() 
	{
		return armies;
	}
	
	/**
	 * set number of the armies
	 * @param armies The number of armies of the current players
	 */
	public void setArmies(int armies) 
	{
		this.armies = armies;
	}
	
	/**
	 * The method is for checking correctness that a player with how many countries he or she owned
	 * @param countryNumber The total owned country number of a player
	 * @return return Return true means correct, and false means incorrect with the owned country number of a player
	 */
	public boolean owns(int countryNumber) 
	{
		for(Country c : ownedCountries) 
		{
			if(c.getNumber() == countryNumber) 
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method will process to reinforce a player's country
	 * @param map the map
	 * @param countryNumber the selected country
	 * @param selectedArmies the number of armies to add
	 */
	public void reinforcement(Map map, int countryNumber, int selectedArmies) 
	{
		map.addArmiesFromHand(countryNumber, selectedArmies);
	}
	
	/**
	 * This method will process the all-out attack mode.
	 * @param attackerCtry Country attacking
	 * @param defenderCtry Country defending
	 */
	public void attack(Map map, Country attackerCtry, Country defenderCtry) 
	{
		do{
			Dices dices = new Dices(attackerCtry.getArmyNumber(), defenderCtry.getArmyNumber());
			battle(map, dices, attackerCtry, defenderCtry);
		}while(attackerCtry.getArmyNumber() > 1 && defenderCtry.getArmyNumber() > 0);	// Continue until no attack is possible
		
		/* Resolving battle result : conquering a country */
		if(defenderCtry.getArmyNumber() == 0) {
			conquer(defenderCtry);			
		}
	}

	/**
	 * This method will process the classic attack mode. The players provide their dices.
	 * @param attackerCtry Country attacking
	 * @param defenderCtry Country defending
	 * @param dices Dices selected by the players
	 */
	public void attack(Map map, Country attackerCtry, Country defenderCtry, Dices dices) {
		battle(map, dices, attackerCtry, defenderCtry);
		
		/* Resolving battle result : conquering a country */
		if(defenderCtry.getArmyNumber() == 0) {
			conquer(defenderCtry);			
		}
	}
	
	/**
	 * This class will compute a battle. It rolls the dice, computing the result, and resolve the loss of both sides.
	 * @param map
	 * @param dices
	 * @param attackerCtry
	 * @param defenderCtry
	 */
	private void battle(Map map, Dices dices, Country attackerCtry, Country defenderCtry) {
		dices.roll();
		/* Resolving loss in both armies */
		map.addArmiesToCountry(attackerCtry.getNumber(), -dices.getAttackerLoss());
		map.addArmiesToCountry(defenderCtry.getNumber(), -dices.getDefenderLoss());
	}
	
	/**
	 * fortification phase function
	 * @param map map data
	 * @param originCountryId first chosen Country
	 * @param destinationCountryId second chosen Country
	 * @param selectedArmies army number selected to attack
	 */
	public void fortification(Map map, int originCountryId, int destinationCountryId, int selectedArmies)
	{
		map.addArmiesToCountry(originCountryId, -selectedArmies);
		map.addArmiesToCountry(destinationCountryId, selectedArmies);
	}
	
	/**
	 * To conquer a country
	 * @param c the country to conquer
	 */
	private void conquer(Country c)
	{
		Player defender = c.getPlayer();
		defender.ownedCountries.remove(c);
		this.ownedCountries.add(c);
		c.setPlayer(this);
	}

	/**
	 * get the percentage of each player owned countries in a map
	 * @param p each player
	 * @param map map file
	 * @return each player's percentage
	 */
	public float getPercentage(Player p, Map map)
    {
		float count = p.ownedCountries.size();
		float total = map.countries.size();
		float percentage = count/total;
        BigDecimal bd = new BigDecimal(percentage);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return (float)bd.doubleValue();
    }
	
	/**
	 * get the total army of each player
	 * @return total army number of eaach player
	 */
	public int getTotalArmy() {
		int totalArmy = 0;
		
		for(Country c : ownedCountries) {
			totalArmy += c.getArmyNumber();
		}
		return totalArmy;
	}

	/**
	 * get the card
	 * @return the card that get
	 */
	public String getCards() {
		String result = "";
		for(Card c : cards) {
			result += c.name() + " ";
		}
		return result;
	}

	/**
	 * get one type of the card
	 */
	public void getOneCard() {		
		switch(Random.getRandomInt(1, 3)) {
		case 1:
			cards.add(Card.artillery);
			break;
		case 2:
			cards.add(Card.cavalry);
			break;
		case 3:
			cards.add(Card.infantry);
			break;			
		}
	}

	/**
	 * trade the card
	 * @param combination 3 same type to trade
	 * @return true if can trade, otherwise false
	 */
	public boolean trade(int combination) {
		switch(combination)
		{
		case 1: 
			if(checkSameCards(Card.artillery)) {
				removeSameCards(Card.artillery);
				return true;
			}		
			break;
		case 2:
			if(checkSameCards(Card.cavalry)) {
				removeSameCards(Card.cavalry);
				return true;
			}
			break;
		case 3:
			if(checkSameCards(Card.infantry)) {
				removeSameCards(Card.infantry);
				return true;
			}
			break;
		case 4:
			if(checkDifferentCards()) {
				removeDifferentCards();
				return true;
			}
			break;
		}
		
		return false;
	}

	/**
	 * check weather cards are different
	 * @return return the types string of cards names
	 */
	private boolean checkDifferentCards() {
		boolean artillery = false, infantry = false, cavalry = false;
		
		for(Card card : cards) {
			if(card.name().equals("artillery"))	artillery = true;
			if(card.name().equals("infantry"))	artillery = true;
			if(card.name().equals("cavalry"))	artillery = true;
		}
		
		return (artillery & infantry & cavalry);
	}

	/**
	 * remove 3 different types of cards to add extra army
	 */
	private void removeDifferentCards() {
		Iterator<Card> i = cards.iterator();
		boolean artillery = false, infantry = false, cavalry = false;
		
		while(i.hasNext() && (artillery  == false || infantry == false || cavalry  == false)) {
			Card c = i.next();
			if(c.name().equals("artillery") && artillery == false) {
				i.remove();
				artillery = true;
			}
			else if(c.name().equals("infantry") && infantry == false) {
				i.remove();
				infantry = true;
			}
			else if(c.name().equals("cavalry") && cavalry == false) {
				i.remove();
				cavalry = true;
			}
		}
	}

	/**
	 * check do the player get the same 3 cards
	 * @param c the card that hold
	 * @return true if have three same cards, otherwise false
	 */
	public boolean checkSameCards(Card c) {
		int sameCard = 0;
		
		for(Card card : cards) {
			if(card.name().equals(c.name()))	sameCard++;
		}
		
		if(sameCard >= 3) return true;
		
		return false;
	}
	
	/**
	 * remove same 3 cards
	 * @param t same 3 cards
	 */
	public void removeSameCards(Card t) {
		Iterator<Card> i = cards.iterator();
		int removed = 0;
		
		while(i.hasNext() && removed < 3) {
			Card c = i.next();
			if(c.name().equals(t.name()))	{
				i.remove();
				removed++;
			}
		}
	}
}
