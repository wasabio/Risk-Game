package MapEditorTest;

import static org.junit.Assert.*;
import org.junit.Before;

import org.junit.Test;

import model.map.Continent;
import model.map.Map;
import model.map.MapEditor;

public class testAddContinent {
	MapEditor me = new MapEditor();
	Map map = new Map();
	@Before
	public void Before() {
		me.addContinent("Alaska",2);
	}
	@Test
	public void test() {
		assertEquals(1,me.map.continents.size());
		
	}

}
