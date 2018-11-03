package gameConsoleTest;

import static org.junit.Assert.*;

import org.junit.Test;

import model.gameplay.Player;
import model.map.Continent;
import model.map.Country;
import model.map.Map;

/**
 * test the method of checking if the map is valid when there is no country or continent in a map or
 * when player number is more than total country number
 * @author Yueshuai
 *
 */
public class testMapValidation 
{
	Map map = new Map();
	
	/** 
	 * test when there are 2 continents but no country
	 **/ 
	@Test
	public void testNoCountry() 
	{
		Continent con1 = new Continent("America",7);
		Continent con2 = new Continent("Asia",4);
		map.continents.add(con1);
		map.continents.add(con2);
		assertFalse(map.checkPlayableMap());
	}
	
	/** 
	 * test when there are 2 countries but no continent 
	 **/
	@Test
	public void testNoContinent() 
	{
		Country cty1= new Country("USA");
		Country cty2 = new Country("Canada");
		assertFalse(map.checkPlayableMap());
	}
	
	/**
	 *  test when there are only 2 countries but there are 3 players 
	 **/
	@Test
	public void testNoEnoughCountry() 
	{
		Continent con1 = new Continent("America",7);
		Continent con2 = new Continent("Asia",4);
		map.continents.add(con1);
		map.continents.add(con2);
		Country cty1= new Country("USA");
		Country cty2 = new Country("Canada");
		con1.addCountry(cty1);
		con2.addCountry(cty2);
		map.countries.add(cty1);
		map.countries.add(cty2);
		Player p1 = new Player(1,2);
		Player p2 = new Player(2,3);
		Player p3 = new Player(3,3);
		map.players.add(p1);
		map.players.add(p2);
		map.players.add(p3);
		assertFalse(map.checkPlayableMap());	
	}
	
	/**
	 *  test when there are 2 continents and 3 countries in the map and 
	 *  player number less than country number 
	 * */
	@Test
	public void testNoError() 
	{
		Continent con1 = new Continent("America",7);
		Continent con2 = new Continent("Asia",4);
		map.continents.add(con1);
		map.continents.add(con2);
		Country cty1= new Country("USA");
		Country cty2 = new Country("Canada");
		con1.addCountry(cty1);
		con2.addCountry(cty2);
		map.countries.add(cty1);
		map.countries.add(cty2);
		Player p1 = new Player(1,2);
		Player p2 = new Player(2,3);
		Player p3 = new Player(3,3);
		map.players.add(p1);
		map.players.add(p2);
		assertTrue(map.checkPlayableMap());	
	}
	
	

}
