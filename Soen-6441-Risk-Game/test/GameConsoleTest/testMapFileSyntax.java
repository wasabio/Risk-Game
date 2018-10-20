package GameConsoleTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class testMapFileSyntax {
	String mapFilePath;
	LineNumberReader in;
	
	@Rule
	  public final ExpectedException exception = ExpectedException.none();

	@Test(expected = FileNotFoundException.class)
	public void testNotFoundMap() throws FileNotFoundException {
		mapFilePath = "ok.map";

		in = new LineNumberReader(new FileReader(mapFilePath));
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testMissingMapSection() throws FileNotFoundException {
		new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "maps");
		File file = new File("world.map");
		mapFilePath = file.getAbsolutePath();

		in = new LineNumberReader(new FileReader(mapFilePath));
	}
	
	
}
