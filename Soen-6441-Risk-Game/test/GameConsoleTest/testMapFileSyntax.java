package GameConsoleTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.map.Map;

public class testMapFileSyntax {
	String mapFilePath;
	String fileName;
	LineNumberReader in;
	File file;
	Map map;
	
	@Before
	public void before() {
		map = new Map();
	}
	
	@Rule
	  public final ExpectedException exception = ExpectedException.none();

	@Test(expected = FileNotFoundException.class)
	public void testNotFoundMap() throws FileNotFoundException {
		mapFilePath = "ok.map";

		in = new LineNumberReader(new FileReader(mapFilePath));
	}
	
	@Test(expected = java.io.EOFException.class)
	public void testWrongMapSection() throws IOException {
		fileName = "noMapSection.map";
		file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "maps" + System.getProperty("file.separator") + "testmaps" + System.getProperty("file.separator") + fileName);
		mapFilePath = file.getAbsolutePath();

		map.load(mapFilePath);
	}
	
	
	@Test(expected = IOException.class)
	public void testMissingContinentSection() throws IOException {
		fileName = "noContinentSection.map";
		file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "maps" + System.getProperty("file.separator") + "testmaps" + System.getProperty("file.separator") + fileName);
		mapFilePath = file.getAbsolutePath();

		map.load(mapFilePath);
	}
	
	
	@Test(expected = IOException.class)
	public void testMissingTerritoriesSection() throws IOException {
		fileName = "noTerritoriesSection.map";
		file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "maps" + System.getProperty("file.separator") + "testmaps" + System.getProperty("file.separator") + fileName);
		mapFilePath = file.getAbsolutePath();

		map.load(mapFilePath);
	}
	
	
	@Test(expected = IOException.class)
	public void testMissingContinentBonus() throws IOException {
		fileName = "noContinentBonus.map";
		file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "maps" + System.getProperty("file.separator") + "testmaps" + System.getProperty("file.separator") + fileName);
		mapFilePath = file.getAbsolutePath();

		map.load(mapFilePath);
	}
	
	@Test
	public void testGoodMap() throws IOException {
		fileName = "world.map";
		file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "maps" + System.getProperty("file.separator") +  fileName);
		mapFilePath = file.getAbsolutePath();
		map.load(mapFilePath);
	}
	
	
	@Test(expected = IOException.class)
	public void testMissingContinentName() throws IOException {
		fileName = "missingContinentName.map";
		file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "maps" + System.getProperty("file.separator") +  fileName);
		mapFilePath = file.getAbsolutePath();
		map.load(mapFilePath);
	}
	
	@Test(expected = IOException.class)
	public void testMissingCountryName() throws IOException {
		fileName = "missingCountryName.map";
		file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "maps" + System.getProperty("file.separator") +  fileName);
		mapFilePath = file.getAbsolutePath();
		map.load(mapFilePath);
	}
}
