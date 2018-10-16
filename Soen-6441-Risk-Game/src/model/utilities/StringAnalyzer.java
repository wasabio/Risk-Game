package model.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class StringAnalyzer {
	
	/**
	 * method to read the content of .map files
	 * 
	 * @param str
	 *            content of a specific line
	 * @param def
	 *            line number
	 * @return return the specific line number
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
	
	public static boolean checkMapType(File file)
	{
		if(file.getName().contains(".map"))
			return true;
		else
		{
			JOptionPane.showMessageDialog(null, "the file name has to end with .map");
			return false;
		}
	}
	
	
}
