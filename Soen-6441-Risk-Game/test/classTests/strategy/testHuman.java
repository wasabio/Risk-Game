package classTests.strategy;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.gameplay.Phase;
import model.gameplay.Player;
import model.gameplay.strategy.Cheater;
import model.gameplay.strategy.Human;
import model.map.Continent;
import model.map.Country;
import model.map.Map;

public class testHuman {

	Continent con1,con2;
	Country cty1,cty2,cty3,cty4;
	Player p1,p2;
	Map map = new Map();
	
	@Before
	public void Before() 
	{
		con1 = new Continent("",3);
		con2 = new Continent("",2);
		cty1 = new Country("");
		cty2 = new Country("");
		cty3 = new Country("");
		cty4 = new Country("");
		p1 = new Player(1, 5, map, new Human());
		p2 = new Player(2, 3, map, new Human());
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
	}
	
	@Test
	public void testReinforce() {
		
		p1.ownedCountries.add(cty1);
		cty1.setPlayer(p1);
		
		p1.ownedCountries.add(cty3);
		cty3.setPlayer(p1);
		
		p1.ownedCountries.add(cty2);
		cty2.setPlayer(p1);
		
		p2.ownedCountries.add(cty4);
		cty4.setPlayer(p2);
		
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		
		cty1.linkTo(cty3);
		cty3.linkTo(cty1);
		
		cty4.linkTo(cty2);
		cty2.linkTo(cty4);
		
		cty1.setArmyNumber(1);
		cty2.setArmyNumber(2);
		cty3.setArmyNumber(3);
		cty4.setArmyNumber(4);
		
		Phase phase  = new Phase();
		map.setPhase(phase);
		//phase.setPhase("startup phase", p1);
		
		//test the number of armies before cheater reinforce
		assertEquals(1,cty1.getArmyNumber());
		assertEquals(2,cty2.getArmyNumber());
		assertEquals(3,cty3.getArmyNumber());
		assertEquals(4,cty4.getArmyNumber());
		
		p1.setArmies(3);
		
		// not sure how to test it... should not use reubforce method but use the function inside?
		//p1.reinforce();
		map.addArmiesFromHand(cty1, 3);
		
		assertEquals(4,cty1.getArmyNumber());
		assertEquals(2,cty2.getArmyNumber());
		assertEquals(3,cty3.getArmyNumber());
		assertEquals(4,cty4.getArmyNumber());
		
		
		// the army of player will only have at least 3 hold army, what if the army only 3? the AI will just add all to the selected one?
		// or the cheater don't care about how many army the ai player get, just multiple the number of army of the country?
	}
	
	@Test
	public void testAttack() {
		
		p1.ownedCountries.add(cty1);
		cty1.setPlayer(p1);
		
		p1.ownedCountries.add(cty3);
		cty3.setPlayer(p1);
		
		p1.ownedCountries.add(cty2);
		cty2.setPlayer(p1);
		
		p2.ownedCountries.add(cty4);
		cty4.setPlayer(p2);
		
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		
		cty1.linkTo(cty3);
		cty3.linkTo(cty1);
		
		cty4.linkTo(cty2);
		cty2.linkTo(cty4);
		
		cty1.setArmyNumber(1);
		cty2.setArmyNumber(2);
		cty3.setArmyNumber(3);
		cty4.setArmyNumber(4);
		
		Phase phase  = new Phase();
		map.setPhase(phase);
		
		assertEquals(1,cty1.getArmyNumber());
		assertEquals(2,cty2.getArmyNumber());
		assertEquals(3,cty3.getArmyNumber());
		assertEquals(4,cty4.getArmyNumber());
		
		
		
		//p1.attack();
		
		
	}
	
}