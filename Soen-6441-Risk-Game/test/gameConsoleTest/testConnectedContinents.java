package gameConsoleTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.map.Continent;
import model.map.Country;
import model.map.Map;
import model.map.MapChecker;

/**
 * check if all the continents are accessible from any other continent
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 */
public class testConnectedContinents 
{
	Map map = new Map();
	Country cty1,cty2,cty3,cty4;
	Continent con1,con2, con3, con4;
	MapChecker checker;
	
	/**
	 * The value setting for connected graph test
	 */
	@Before
	public void before() 
	{
		con1 = new Continent("",3);
		con2 = new Continent("",2);
		con3 = new Continent("",2);
		con4 = new Continent("",2);
		cty1 = new Country("");
		cty2 = new Country("");
		cty3 = new Country("");
		cty4 = new Country("");
		
		map.continents.add(con1);
		map.continents.add(con2);
		map.continents.add(con3);
		map.continents.add(con4);
		map.countries.add(cty1);
		map.countries.add(cty2);
		map.countries.add(cty3);
		map.countries.add(cty4);
		
		con1.addCountry(cty1);
		con2.addCountry(cty2);
		con3.addCountry(cty3);		
		con4.addCountry(cty4);
		checker = new MapChecker(map);
	}
	
	/**
	 * Continent 1---Continent 2---Continent 4
	 * continent 3 is not connected to the rest of the world
	 */
	@Test
	public void testNotConnected() 
	{
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		
		cty2.linkTo(cty4);
		cty4.linkTo(cty2);
		
		assertFalse(checker.checkConnectedContinents());
	}
	
	/**
	 * test following country (so continent) connections :  1-2  3-4 
	 * Each continent have a link to an other continent, all continents are not reachable
	 */
	@Test
	public void testConnectedButNotAllReachableContinents() 
	{
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		
		cty3.linkTo(cty4);
		cty4.linkTo(cty3);
		
		assertFalse(checker.checkConnectedContinents());
	}
	
	/**
	 * test when all continents are connected together
	 * 4-3-1-2
	 */
	@Test  
	public void testConnected() 
	{
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		
		cty1.linkTo(cty3);
		cty3.linkTo(cty1);
		
		cty4.linkTo(cty3);
		cty3.linkTo(cty4);
		assertTrue(checker.checkConnectedContinents());
	}
}
