package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import view.common.MapSelectionView;
import view.gameplay.FortificationView;
import view.gameplay.MapView;
import view.gameplay.PhaseView;
import view.gameplay.ReinforcementView;
import view.gameplay.StartUpView;
import model.gameplay.Phase;
import model.gameplay.Player;
import model.map.Country;
import model.map.Map;
	
/**
 * This class is a controller for game play part
 * It also includes controls for different phases in the game
 */
public class GameController 
{
	
	private Map map;
	private Phase phase;
	private MapView mapView;
	private PhaseView phaseView;
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
			phase = new Phase();
			mapView = new MapView();
			phaseView = new PhaseView();
			reinforcementView = new ReinforcementView();
			fortificationView = new FortificationView();
			phase.addObserver(phaseView);
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
		}while(!map.isOwned()); /* When map is owned, end of the game */
		
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
					phase.setPhase("Start up phase",p,1);
					int ctryId = startUpView.askCountry(p);
					phase.setAction("p"+p.getNumber()+" added 1 army in "+map.countries.get(ctryId-1).getName()+"\n");
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
		phase.setPhase("Reinforcement phase", p,1);
		do 
		{
			
			int countryNumber = reinforcementView.askCountry(p);
			int selectedArmies = reinforcementView.askArmiesNumber(p);
			int index = map.countries.get(countryNumber-1).getArmyNumber();
			p.reinforcement(map, countryNumber, selectedArmies);
			phase.setAction("P"+p.getNumber()+" reinfoced "+ selectedArmies+" army in "+map.countries.get(countryNumber-1).getName()+"\n");
			phase.setPhase("Reinforcement phase", p,map.countries.get(countryNumber-1).getArmyNumber());
			
			
		}while(p.getArmies() > 0);
	}
	
	/**
	 * This method deals with the logic of the fortification phase. The player can choose origin and destination, and armies number.
	 * Origin and destination countries must be connected.
	 * @param p The current player that is in the fortificationPhase
	 */
	private void fortificationPhase(Player p) 
	{
		phase.setPhase("Fortification phase", p,1);
		/* Getting origin country */
		boolean canSendTroops;
		int	originCountryId;
		Country origin;
		do 
		{
			originCountryId = fortificationView.chooseOriginCountry(p); /* Select a valid country owned by the current player */
			if(originCountryId == 0) return;	/* 0 to skip */
			
			origin = map.countries.get(originCountryId-1);
			canSendTroops = origin.canSendTroopsToAlly(); /* Check if the selected country can send troops */
			if(!canSendTroops)	fortificationView.errorSendingTroops();
		}while(!canSendTroops);
		
		
		/* Getting destination country */
		boolean connected;
		int destinationCountryId;
		Country destination;
		do 
		{
			destinationCountryId = fortificationView.chooseDestinationCountry(p);
			
			destination = map.countries.get(destinationCountryId-1);
			 connected = destination.isConnectedTo(origin);
			if(!connected) 
			{
				fortificationView.errorNotConnectedCountries();
			}
		}while(!connected);
			
		Country c = map.countries.get(originCountryId-1);
		
		/* Getting number of armies to send */
		int selectedArmies = fortificationView.askArmiesNumber(p, c.getArmyNumber()-1);	/* User has to let at least 1 army on the origin country */
		
		/* Updating armies */
		p.fortification(map, originCountryId, destinationCountryId, selectedArmies);
		phase.setAction("p"+p.getNumber()+" fortified "+ selectedArmies+" army from "+
				map.countries.get(originCountryId-1).getName()+" to "+map.countries.get(destinationCountryId-1).getName()+"\n");
	}	
}