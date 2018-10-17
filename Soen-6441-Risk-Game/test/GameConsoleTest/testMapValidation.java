package GameConsoleTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.map.Continent;
import model.map.Country;
import model.map.Map;
/**
 * test if the map valid when there is no country in a continent
 * @author Yueshuai
 *
 */
public class testMapValidation {

	Map map = new Map();
	Country cty1= new Country("London");
	Country cty2 = new Country("Beijing");
	Country cty3 = new Country("Paris");
	Continent con1 = new Continent("America",7);
	Continent con2 = new Continent("Asia",4);
	Continent con3 = new Continent("Europe",6);
	@Before
	public void Beofre() {
		con1.addCountry(cty1);
		con1.addCountry(cty2);
		con2.addCountry(cty3);
		map.continents.add(con1);
		map.continents.add(con2);
		map.continents.add(con3);
		cty1.linkTo(cty2);
		cty2.linkTo(cty1);
		cty1.linkTo(cty3);
		cty3.linkTo(cty1);
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
