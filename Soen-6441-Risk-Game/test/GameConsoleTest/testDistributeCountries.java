package GameConsoleTest;

import static org.junit.Assert.*;
import org.junit.Test;
import model.gameplay.Player;
import model.map.Continent;
import model.map.Country;
import model.map.Map;
import org.junit.*;

/**
 * test if all countries will be distributed to players
 * @author Yueshuai
 *
 */
public class testDistributeCountries {
	Continent con1 = new Continent("",2);
	Continent con2 = new Continent("",3);
	Continent con3 = new Continent("",4);
	Country cty1 = new Country("");
	Country cty2 = new Country("");
	Country cty3 = new Country("");
	Country cty4 = new Country("");
	Country cty5 = new Country("");
	Player p1 = new Player(1,3);
	Player p2 = new Player(2,3);
	Map map = new Map();
	
	
	/**
	 * put countries into different continents
	 */
	@Before
	public void Before() {
		con1.addCountry(cty1);
		con1.addCountry(cty2);
		con2.addCountry(cty3);
		con2.addCountry(cty4);
		con3.addCountry(cty5);
		
		map.continents.add(con1);
		map.continents.add(con2);
		map.continents.add(con3);
		map.countries.add(cty1);
		map.countries.add(cty2);
		map.countries.add(cty3);
		map.countries.add(cty4);
		map.countries.add(cty5);
		map.players.add(p1);
		map.players.add(p2);
	}
	/**
	 * distribute countries, and test if all countries are owned by players
	 */
	@Test
	public void test() {
		map.distributeCountries();
		boolean isDistributed = true;
		for(Country c : map.countries) {
			if(c.getPlayer().getNumber() == 0) {
				isDistributed = false;
				break;
			}
		}
		assertTrue(isDistributed);	
	}
	/**
	 * do not distribute countries, and test if countries are owned by players
	 */
	@Test
	public void test2() {
		boolean isDistributed = true;
		for(Country c : map.countries) {
			if(map.getPlayerNumber() == 0) {
				isDistributed = false;
				break;
			}
		}
		assertFalse(isDistributed);
			
	}

}
