package controller;
<<<<<<< HEAD
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.utilities.StringAnalyzer;

import javax.swing.JFileChooser;
=======
>>>>>>> master

import java.io.IOException;
import view.MapSelectionView;
import view.MapView;
import model.map.Map;

public class GameController {
	
	private Map map;
	private MapView mapView;

	public GameController()
	{
		try {
			map = new Map();
			mapView = new MapView();
			map.addObserver(mapView);
			execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void execute() throws IOException 
	{
		MapSelectionView mapSelectionView = new MapSelectionView();		
		int playerNumber = mapSelectionView.print();
		
		map.setPlayers(playerNumber); 
		
		String mapFilePath = mapSelectionView.selectMap();		
		map.load(mapFilePath);	//3 Checking functions should go inside this method. We also need to check if map is playable regarding the number of players.
		
		startUpPhase();
		
		do {
			
		}while();
	}
	
	private void startUpPhase() {
		map.distributeCountries();
	}
}
	

