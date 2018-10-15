package controller;

import java.io.IOException;

import view.MapSelectionView;
import model.map.Map;

public class GameController {
	
	private Map map;

	public GameController()
	{
		try {
			execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private void execute() throws IOException 
	{
		MapSelectionView mapSelectionView = new MapSelectionView();
		map = new Map();
		
		int playerNumber = mapSelectionView.print();
		map.setPlayerNumber(playerNumber); 
		
		String mapFilePath = mapSelectionView.selectMap();		
		map.load(mapFilePath);	//3 Checking functions should go inside this method. We also need to check if map is playable regarding the number of players.
		
		startUpPhase();
		
	}
	
	private void startUpPhase() {
		map.distributeCountries();
	}
}
	

