package model.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class StringAnalyzer {
	
	/**
	 * method to reading contents in the .map file
	 * 
	 * @param str
	 *            contents in the specific line
	 * @param def
	 *            line number
	 * @return return the specific line number.
	 */
	public static int parseInt(String str, int lineNb) {
		if (str == null) {
			return lineNb;
		}
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
		}
		return lineNb;
	}
	
	
		
	
}
