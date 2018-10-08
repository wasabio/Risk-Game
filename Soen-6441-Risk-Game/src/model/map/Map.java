package model.map;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.utilities.FileHandler;

public class Map {
	
	private ArrayList<Continent> continents = new ArrayList<Continent>();
	private String mapFilePath;


	public void load(String mapFilePath) 
	{
		this.mapFilePath = mapFilePath;
		
		/*LineNumberReader in = new LineNumberReader(new FileReader(mapFilePath));
		loadMapSection(in);
		loadContinents(in);
		loadTerritories(in);*/
	}
	
	private void loadMapSection(LineNumberReader in) throws IOException {
		/*findSection(in, "Map");
		while(true) {
			String line = in.readLine();
			if (line == null) {
				throw new IOException("[Continents] Section expected; found EOF");
			}
			if (!line.trim().equals("")) {
				if (line.startsWith("[")) {
					if (line.equalsIgnoreCase("[Continents]")) {
						return;
					}
					throw new IOException("[Continents] Section expected; found " + line);
				}
				String[] pair = line.split("=", 2);
				if ((pair != null) && (pair.length == 2)) {
					String prop = pair[0].toLowerCase();
					String val = pair[1];
					if ("image".equals(prop)) {
						if ((val != null) && (val.length() > 0)) {
							this.imageFilePath = (new File(this.mapFilePath).getParent() + "\\" + val);
						}
					} else if ("wrap".equals(prop)) {
						this.wrap = val.equalsIgnoreCase("yes");
					} else if ("scroll".equals(prop)) {
						this.scroll = convertScrollOptionsString(val, ScrollOptions.NONE);
					} else if ("author".equals(prop)) {
						this.author = val;
					} else if ("warn".equals(prop)) {
						this.warn = val.equalsIgnoreCase("yes");
					}
				}
			}
		}*/
	}

	private void loadContinents(LineNumberReader in) throws IOException {
		/*while(true) {
			String line = in.readLine();
			if (line == null) {
				throw new IOException("[Territories] Section expected; found EOF");
			}
			if (!line.trim().equals("")) {
				if (line.startsWith("[")) {
					if (line.equalsIgnoreCase("[Territories]")) {
						return;
					}
					throw new IOException("[Territories] Section expected; found " + line);
				}
				int eqloc = line.indexOf("=");
				if (eqloc == -1) {
					throw new IOException("Invalid continent line: " + line);
				}
				String cname = line.substring(0, eqloc).trim();
				int cbonus = Integer.parseInt(line.substring(eqloc + 1).trim(), -1);
				if ((cname.length() < 1) || (cbonus == -1)) {
					throw new IOException("Invalid continent line: " + line);
				}
				this.continents.add(new Continent(cname, cbonus));
			}
		}*/
	}
	
	private int findSection(LineNumberReader in, String section) throws IOException {
		String head = "[" + section + "]";
		String line;
		do {
			line = in.readLine();
			if (line == null) {
				throw new EOFException("EOF encountered before section " + head + " found");
			}
		} while (!line.equalsIgnoreCase(head));
		return in.getLineNumber();
	}

	public void open() 
	{
		JFileChooser fileChooser = new JFileChooser();
		StringBuilder sb = new StringBuilder();
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File path = fileChooser.getSelectedFile();
			//File dirf = path.getAbsolutePath();
			
			try {
				Scanner input = new Scanner(path);
			while(input.hasNext())
			{
				sb.append(input.nextLine());
				sb.append("\n");
			}
			input.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} 
			if(checkType(path))
				mapFilePath = path.getAbsolutePath();
			else
				open();
		}
		else {
			sb.append("No file was selected");
		}
		
	}
	private boolean checkType(File file)
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
