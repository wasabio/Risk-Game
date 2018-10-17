package GameConsoleTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.gameplay.Player;
import model.map.Continent;
import model.map.Country;
import model.map.Map;

/**
 * This class tests the calculation of army number test should be given to player in Map class
 * @author Yueshuai
 *
 */
public class testCalculateArmyNum {

	Player p1 = new Player(1,5);
	Player p2 = new Player(2,5);
	Country cty1= new Country("London");
	Country cty2 = new Country("Beijing");
	Country cty3 = new Country("Paris");
	Country cty4 = new Country("Montreal");
	Country cty5 = new Country("Toronto");
	Continent con1 = new Continent("",7);
	Continent con2 = new Continent("",4);
	Continent con3 = new Continent("",6);
	
	Map map = new Map();
	/**
	 * test calculation of armies when player owns 1 whole continent and another country in a continent
	 */
	@Before 
	public void before1() {
	con1.addCountry(cty1);
	con2.addCountry(cty2);
	con3.addCountry(cty3);
	con1.addCountry(cty4);
	con1.addCountry(cty5);
	p1.ownedCountries.add(cty1);
	p1.ownedCountries.add(cty2);
	p2.ownedCountries.add(cty3);
	p2.ownedCountries.add(cty4);
	p1.ownedCountries.add(cty1);
	cty2.setPlayer(p1);
	cty1.setPlayer(p1);
	cty3.setPlayer(p2);
	cty4.setPlayer(p2);
	map.continents.add(con1);
	map.continents.add(con2);
	map.continents.add(con3);
	}
	@Test
	public void test1() {
		assertEquals(4,map.calculateArmyNum(p1));
		assertEquals(6,map.calculateArmyNum(p2));
	}
	
	/**
	 * test when a player owns 4 countries in one continent
	*/
	@Before 
	public void before2() {
	con1.addCountry(cty1);
	con2.addCountry(cty2);
	con3.addCountry(cty3);
	con1.addCountry(cty4);
	con1.addCountry(cty5);
	cty1.setNumber(1);    //didn't input number
	cty2.setNumber(2);
	cty3.setNumber(3);
	cty4.setNumber(4);
	cty5.setNumber(5);
	p1.ownedCountries.add(cty1);
	p1.ownedCountries.add(cty2);
	p1.ownedCountries.add(cty3);
	p1.ownedCountries.add(cty4);
	p1.ownedCountries.add(cty5);
	cty2.setPlayer(p1);
	cty1.setPlayer(p1);
	cty3.setPlayer(p1);
	cty4.setPlayer(p1);
	cty5.setPlayer(p1);
	map.continents.add(con1);
	map.continents.add(con2);
	map.continents.add(con3);
	
	}
	@Test
	public void test2() {
		
		assertEquals(2,map.calculateArmyNum(p1));
		
	} 
	
	

}
