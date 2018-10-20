package MapEditorTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.map.Continent;
import model.map.Country;
import model.map.Map;
import model.map.MapEditor;
/**
 * test the method of adding country in the map editor
 */
public class testAddCountry {
	MapEditor mapEditor = new MapEditor();
	Continent con1 = new Continent("",1);
	Continent con2 = new Continent("",2);
	
	/**
	 * 
	 */
	@Before
	public void Before() {
		mapEditor.map.continents.add(con1);
		mapEditor.map.continents.add(con2);
	}
	@Test
	
	/**
	 * add no country
	 */
	public void test() {
		
		
	}

}
