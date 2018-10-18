package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import view.common.MapView;
import view.gameplay.FortificationView;
import view.gameplay.MapSelectionView;
import view.gameplay.ReinforcementView;
import view.gameplay.StartUpView;
import model.gameplay.Player;
import model.map.Country;
import model.map.Map;
	
	/**
	 * This class is a controller for game play part
	 * It includes controls for different phases in the game
	 */
public class GameController 
{
	
	private Map map;
	private MapView mapView;
	private ReinforcementView reinforcementView;
	private FortificationView fortificationView;

	/**
	 * This is a constructor method for GameController
	 */
	public GameController()
	{
		try 
		{
			map = new Map();
			map.clear();
			mapView = new MapView();
			reinforcementView = new ReinforcementView();
			fortificationView = new FortificationView();
			
			map.addObserver(mapView);
			
			execute();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method deals with phase steps and checks whether the game has a winner or not
	 * @throws IOException
	 */
	private void execute() throws IOException
	{		
		startUpPhase();
		
		do 
		{
			for(Player p : map.players) 
			{
				if(p.ownedCountries.size() > 0) 
				{
					reinforcementPhase(p);
					fortificationPhase(p);
				}
			}
		}while(!map.isOwned()); /* End of the game */
		
	}

	/**
	 * This method deals with deploying players and armies on the map when the game just start.
	 * The deployment of each player with countries is randomly deployed.
	 * The deployment will end until every country have an owner.
	 * @throws IOException
	 */
	private void startUpPhase() throws IOException 
	{
		MapSelectionView mapSelectionView = new MapSelectionView();
		StartUpView startUpView = new StartUpView();

		int playerNumber = mapSelectionView.print();
		map.setPlayers(playerNumber); 
		
		String mapFilePath = mapSelectionView.selectMap();		
		map.load(mapFilePath);
		map.distributeCountries(); /* Randomly split the countries between the players */
		
		ArrayList<Player> remainingPlayers = new ArrayList<Player>(map.players);
			
		do 
		{
			Iterator<Player> i = remainingPlayers.iterator();

			while (i.hasNext()) 
			{
				Player p = i.next();
				if(p.getArmies() == 0) 
				{
					i.remove();
				} 
				else 
				{
					int ctryId = startUpView.askCountry(p);
					Country c = map.countries.get(ctryId-1);					
					map.addArmiesToCountry(ctryId, 1);
				}
			}
		}while(remainingPlayers.size() != 0);
	}
	/**
	 * The method's main function is adding standby armies to the selected country when in the reinforcementPhase.
	 * @param p The current player that is in the reinforcementPhase
	 */
	private void reinforcementPhase(Player p) 
	{
		int armyNum = map.calculateArmyNum(p);
		p.setArmies(armyNum);
		do 
		{
			int countryNumber = reinforcementView.askCountry(p);
			int selectedArmies = reinforcementView.askArmiesNumber(p);
			map.addArmiesToCountry(countryNumber, selectedArmies);
		}while(p.getArmies() > 0);
	}
	
	/**
	 * This method deals with the logic when one group of army in a country tries to attack another country.
	 * It also deals with the computing of fight damage when two group of armies are fighting. 
	 * @param p The current player that is in the fortificationPhase
	 */
	private void fortificationPhase( Player p) 
	{
		/* Getting Origin countries */
		int	originCountryId = fortificationView.chooseOriginCountry(p);
		/* 0 to skip */
		if(originCountryId == 0) return;
		
		int maxArmies = map.countries.get(originCountryId-1).getArmyNumber();
		/* We can't move armies from a country that has only 1 army */		
		while(maxArmies <= 1) 
		{
			originCountryId = fortificationView.originError(p);
			if(originCountryId == 0) return;
			maxArmies = map.countries.get(originCountryId-1).getArmyNumber();
		}
		/* Getting destination and armies number */
		Country c = map.countries.get(originCountryId-1);
		int destinationCountryId = fortificationView.chooseDestinationCountry(p);
		int selectedArmies = fortificationView.askArmiesNumber(p, c.getArmyNumber());
		/* Updating armies */
		map.addArmiesToCountry(originCountryId, -selectedArmies);
		map.addArmiesToCountry(destinationCountryId, selectedArmies);
	}	
}