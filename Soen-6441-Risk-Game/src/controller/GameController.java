package controller;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.utilities.StringAnalyzer;

import javax.swing.JFileChooser;

import view.SetUpView;
import view.View;
import model.map.Map;

public class GameController {

	public GameController()
	{
		try {
			execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void execute() throws IOException 
	{
		SetUpView setUpView = new SetUpView();
		setUpView.print();
		String mapFilePath = setUpView.selectMap();
		
		Map map = new Map();
		map.load(mapFilePath);
		
	}	
}
	

