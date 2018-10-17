package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
				if(p.ownedCountries.size() > 0) {
					reinforcementPhase(p);
					fortificationPhase(p);
				}
			}
		}while(map.isOwned()); /* End of the game */
		
	}

	private void startUpPhase() throws IOException {
		MapSelectionView mapSelectionView = new MapSelectionView();
		StartUpView startUpView = new StartUpView();

		int playerNumber = mapSelectionView.print();
		map.setPlayers(playerNumber); 
		
		String mapFilePath = mapSelectionView.selectMap();		
		map.load(mapFilePath);
		map.distributeCountries(); /* Randomly split the countries between the players */
		
		ArrayList<Player> remainingPlayers = new ArrayList<Player>(map.players);
			
		do {
			Iterator<Player> i = remainingPlayers.iterator();

			while (i.hasNext()) {
				Player p = i.next();
				if(p.getArmies() == 0) {
					i.remove();
				} else {
					int ctryId = startUpView.askCountry(p);
					Country c = map.countries.get(ctryId-1);
					map.setCountryArmies(ctryId, c.getArmyNumber() + 1);
				}
			}
		}while(remainingPlayers.size() != 0);
	}
	
	private void reinforcementPhase(Player p) {
		do {
			int armyNum = map.calculateArmyNum(p);
			p.setArmies(armyNum);
			
			int countryNumber = reinforcementView.askCountry(p);
			int selectedArmies = reinforcementView.askArmiesNumber(p);
			int oldArmiesNumber = map.countries.get(countryNumber).getArmyNumber();
			
			map.setCountryArmies(countryNumber, oldArmiesNumber + selectedArmies);
		}while(p.getArmies() > 0);
	}
	
	
	private void fortificationPhase( Player p) {
		int originCountryId = fortificationView.chooseOriginCountry(p);
		int destinationCountryId = fortificationView.chooseDestinationCountry(p)
	}
}