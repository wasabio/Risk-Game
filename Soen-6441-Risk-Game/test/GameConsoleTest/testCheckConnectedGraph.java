package GameConsoleTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.map.Continent;
import model.map.Country;
import model.map.Map;
/**
 * test the method of checking if there is any isolated country which does not have any neighbor
 * @author Yueshuai
 *
 */
public class testCheckConnectedGraph {
	Map map = new Map();
	Country cty1,cty2,cty3;
	Continent con1,con2;
	@Before
	public void before() {
		con1 = new Continent("",3);
		con2 = new Continent("",2);
		cty1 = new Country("");
		cty2 = new Country("");
		cty3 = new Country("");
		map.continents.add(con1);
		map.continents.add(con2);
		map.countries.add(cty1);
		map.countries.add(cty2);
		map.countries.add(cty3);
		con1.addCountry(cty1);
		con2.addCountry(cty2);
		con1.addCountry(cty3);
		
	}
	/**
	 * test when country 1 and 2 are connected but country 3 does not
	 * have any neighbor
	 */
	@Test
	public void testNotConnected() {
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		assertEquals(0,cty3.neighbors.size());   //error here£¬ should be false
		assertFalse(map.checkConnectedGraph());
	}
	/**
	 * test when all three countries are connected
	 */
	@Test  
	public void testAllConnected() {
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		cty1.linkTo(cty3);
		cty3.linkTo(cty1);
		assertTrue(map.checkConnectedGraph());
	}

}
