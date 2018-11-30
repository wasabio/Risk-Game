package classTests.strategy;

import org.junit.Before;
import org.junit.Test;

import controller.TournamentController;
import model.gameplay.Player;
import model.gameplay.strategy.Random;
import model.map.Continent;
import model.map.Country;
import model.map.Map;

/**
 * This class is for testing functions of the Aggressive AI
 * @author skyba
 *
 */
public class testTournament {

	/**
	 * the initial data of Continents con1,con2
	 */
	Continent con1,con2;
	
	/**
	 * the initial data of countries cty1,cty2,cty3,cty4
	 */
	Country cty1,cty2,cty3,cty4;
	
	/**
	 * the initial data of Players p1,p2
	 */
	Player p1,p2;
	
	/**
	 * the initial data of map file
	 */
	Map map = new Map();
	
	
	
	/**
	 * the method for inputing initial data for testing
	 */
	@Before
	public void Before() 
	{
		
		con1 = new Continent("",3);
		con2 = new Continent("",4);
		cty1 = new Country("");
		cty2 = new Country("");
		cty3 = new Country("");
		cty4 = new Country("");
		
		map.continents.add(con1);
		map.continents.add(con2);
		map.countries.add(cty1);
		map.countries.add(cty2);
		map.countries.add(cty3);
		map.countries.add(cty4);
		con1.addCountry(cty1);
		con2.addCountry(cty2);
		con1.addCountry(cty3);
		con2.addCountry(cty4);
		map.players.add(p1);
		map.players.add(p2);
		
		p1.setArmies(10);
		p2.setArmies(20);
	}
	@Test
	public void testPlayOneGame()
	{
		p1.ownedCountries.add(cty1);
		cty1.setPlayer(p1);
		p1.ownedCountries.add(cty2);
		cty2.setPlayer(p1);
		p1.ownedCountries.add(cty3);
		cty3.setPlayer(p1);
		p1.ownedCountries.add(cty4);
		cty4.setPlayer(p1);

			}
}
