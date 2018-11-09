package GamePlayTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.gameplay.Dices;
import model.gameplay.Player;
import model.map.Continent;
import model.map.Country;
import model.map.Map;

/**
 * This class tests the attack phase
 * @author Yann
 *
 */
public class TestAttack {
	
	static Player p1, p2;
	static Country cty1, cty2, cty3, cty4;
	static Continent con1;
	static Map map;

	@BeforeClass
	public static void beforeClass() {
		p1 = new Player(1,5);
		p2 = new Player(2,5);
		
		cty1= new Country("London");
		cty2 = new Country("Beijing");
		cty3 = new Country("Paris");
		cty4 = new Country("A");
		
		con1 = new Continent("",7);
		map = new Map();
	}
	
	
	/*/**
	 * initiating 3 continents, 5 countries, country 1,4,5 is in continent 1, country 2 in continent 2
	 * country 3 in continent 3
	*/
	/**
	 * The test method for adding countries and continents
	 */
	@Before 
	public void before() 
	{
		con1.addCountry(cty1);
		con1.addCountry(cty2);
		con1.addCountry(cty3);
		con1.addCountry(cty4);
		map.continents.add(con1);
		
		map.countries.add(cty1);
		map.countries.add(cty2);
		map.countries.add(cty3);
		map.countries.add(cty4);
	}
	
	@Test
	public void testValidAttackAllOutMode() {
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		
		cty1.setArmyNumber(2);
		cty2.setArmyNumber(2);
		
		cty1.setPlayer(p1);
		cty2.setPlayer(p2);
		
		p1.attack(map, cty1, cty2);
		// Attacker XOR Defender wins
		assertTrue(cty1.getArmyNumber() == 1 || cty2.getArmyNumber() == 0);
		assertFalse(cty1.getArmyNumber() == 1 && cty2.getArmyNumber() == 0);
		
		if(cty1.getArmyNumber() == 1) {	//Defender wins
			assertEquals(cty1.getPlayer(), p1);
			assertEquals(cty2.getPlayer(), p2);
		} else {						//Attacker wins : he conquers the country
			assertEquals(cty1.getPlayer(), p1);
			assertEquals(cty2.getPlayer(), p1);
		}
	}
	
	@Test
	public void testAttackNotConnected() {
		cty3.setArmyNumber(12);
		cty4.setArmyNumber(10);
		
		cty3.setPlayer(p1);
		cty4.setPlayer(p2);
		
		p1.attack(map, cty3, cty4);
		assertEquals(cty3.canAttack(), false);
		assertEquals(cty4.canBeAttackedBy(cty3), false);
	}
	
	@Test
	public void testValidAttackClassicMode() {
		cty1.setPlayer(p1);
		cty2.setPlayer(p2);
		cty1.linkTo(cty2);
		cty2.linkTo(cty3);
		
		cty1.setArmyNumber(12);
		cty2.setArmyNumber(10);
		Dices dices = new Dices(cty1.getArmyNumber(), cty2.getArmyNumber());
		dices.setDicesNumber(3, 2);//Set attacker dices to 3, defender dices to 2
		p1.attack(map, cty1, cty2, dices);
		
		assertEquals(dices.getAttackerLoss() + dices.getDefenderLoss(), 2);
	}
}
