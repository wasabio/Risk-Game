package controller;

import java.io.IOException;

import view.FortificationView;
import view.MapSelectionView;
import view.MapView;
import view.ReinforcementView;
import view.StartUpView;
import model.gameplay.Player;
import model.map.Country;
import model.map.Map;

public class GameController {
	
	private Map map;
	private MapView mapView;
	private ReinforcementView reinforcementView;
	private FortificationView fortificationView;

	public GameController()
	{
		try {
			map = new Map();
			mapView = new MapView();
			reinforcementView = new ReinforcementView();
			fortificationView = new FortificationView();
			
			map.addObserver(mapView);
			
			execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void execute() throws IOException
	{		
		startUpPhase();
		
		do {
			for(Player p : map.players) {
				reinforcementPhase(p);
				fortificationPhase(p);
			}
		}while(map.isOwned()); //End of the game
		
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
	
	private void reinforcementPhase(Player p) {
		do {
			
			int armyNum = map.calculateArmyNum(p);
			p.setArmies(armyNum);
			
			int countryNumber = reinforcementView.askCountry(p);
			int selectedArmies = reinforcementView.askArmiesNumber(p);
			int oldArmiesNumber = map.countries.get(countryNumber).getArmyNumber();
			
			map.setCountry(countryNumber, oldArmiesNumber + selectedArmies);
			p.setArmies(p.getArmies() - selectedArmies);
		}while(p.getArmies() > 0);
	}
	
	
	private void fortificationPhase( Player p) {
		// TODO Auto-generated method stub
		
	}
}