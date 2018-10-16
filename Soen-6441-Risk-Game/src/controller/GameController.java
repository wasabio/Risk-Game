package controller;

import java.io.IOException;
import view.MapSelectionView;
import view.MapView;
import view.StartUpView;
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
		startUpPhase();
		
		/*do {
			reinforcementPhase();
			fortificationPhase();
		}while(map.isOwned());*/
		
	}
	
	private void startUpPhase() throws IOException {
		MapSelectionView mapSelectionView = new MapSelectionView();		
		int playerNumber = mapSelectionView.print();
		
		map.setPlayers(playerNumber); 
		
		String mapFilePath = mapSelectionView.selectMap();		
		map.load(mapFilePath);
		
		map.distributeCountries();
		
		StartUpView startUpView = new StartUpView();
		startUpView.print();
	}
}