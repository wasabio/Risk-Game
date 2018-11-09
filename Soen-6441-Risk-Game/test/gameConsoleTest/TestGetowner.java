package gameConsoleTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import model.gameplay.Player;
import model.map.Continent;
import model.map.Country;
import model.map.Map;
/**
 * 
 * 
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 */
public class TestGetowner 
{
	Map map = new Map();	
	Continent cont1 = new Continent("",1);
	Continent cont2 = new Continent("",2);
	
	Country cty1 = new Country();
	Country cty2 = new Country();
	Country cty3 = new Country();
	
	Player p1 = new Player(0, 1);
	Player p2 = new Player(0, 1);
	
	
	@Test
	public void testGetOwner() 
	{
		map.players.add(p1);
		map.players.add(p2);
		
		cont1.countries.add(cty1);
		cont1.countries.add(cty3);
		cont2.countries.add(cty2);
		
		
		p1.ownedCountries.add(cty1);
		p1.ownedCountries.add(cty3);
		cty1.setContinent(cont1);
		cty3.setContinent(cont1);
		
		p2.ownedCountries.add(cty2);
		cty2.setContinent(cont2);
		
		cty1.setPlayer(p1);
		cty2.setPlayer(p2);
		cty3.setPlayer(p1);
		
		cont1.addCountry(cty1);
		cont1.addCountry(cty3);
		cont2.addCountry(cty2);
		
		assertEquals(p1, cont1.getOwner());
	}
	
	@Test
	public void testNotGetOwner() 
	{
		
		map.players.add(p1);
		map.players.add(p2);
		cont1.countries.add(cty1);
		cont1.countries.add(cty3);
		cont2.countries.add(cty2);
		
		cty1.setContinent(cont1);
		cty3.setContinent(cont1);
		cty2.setContinent(cont2);
		
		cont1.addCountry(cty1);
		cont1.addCountry(cty3);
		cont2.addCountry(cty2);
		
		 cty1.setPlayer(p2);
		 cty2.setPlayer(p2);
		 cty3.setPlayer(p2);
		
		 assertNotEquals(p1, cont1.getOwner());
	}
}
