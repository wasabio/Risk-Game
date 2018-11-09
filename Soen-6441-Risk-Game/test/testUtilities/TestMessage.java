package testUtilities;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import model.utilities.Message;

public class TestMessage {

	@Test
	public void testError() 
	{
		String a = Message.error("error message");
		String b = "Error : error message";
		
		assertEquals(b, a);
	}
	
}
