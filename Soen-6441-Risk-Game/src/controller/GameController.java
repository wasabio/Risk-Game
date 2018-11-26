package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import view.common.MapSelectionView;
import view.gameplay.FortificationView;
import view.gameplay.AttackView;
import view.gameplay.CardExchangeView;
import view.gameplay.MapView;
import view.gameplay.PhaseView;
import view.gameplay.StartUpView;
import view.gameplay.WinnerView;
import view.gameplay.WorldDominationView;
import model.gameplay.Phase;
import model.gameplay.Player;
import model.map.Country;
import model.map.Map;
	
/**
 * This class is a controller for game play part
 * It also includes controls for different phases in the game
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 */
public class GameController 
{
	private Map map;
	private Phase phase;
	private MapView mapView;
	private PhaseView phaseView;
	private WorldDominationView worldDomiView;
	private Player winner;
	private CardExchangeView cardExchangeView;

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
			worldDomiView = new WorldDominationView();
			cardExchangeView = new CardExchangeView();
			phase.addObserver(phaseView);
			phase.addObserver(cardExchangeView);
			map.addObserver(worldDomiView);
			map.addObserver(mapView);
			
			setUp();
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
					attackPhase(p);
					
					if(map.isOwned()) {
						winner = p;
						break;
					}
					
					fortificationPhase(p);
				}
			}
		}while(!map.isOwned()); /* When map is owned, end of the game */
		
		new WinnerView(winner);
	}
	
	/**
	 * Set up the parameters of the game : number of players, types of players, map selection.
	 */
	public void setUp() throws IOException  
	{
		MapSelectionView mapSelectionView = new MapSelectionView();
		int playerNumber = mapSelectionView.print();
		map.setPlayers(playerNumber); 
		
		String mapFilePath = mapSelectionView.selectMap();		
		map.load(mapFilePath);
	}

	/**
	 * This method deals with deploying players and armies on the map when the game just start.
	 * The deployment of each player with countries is randomly deployed.
	 * The deployment will end until every country have an owner.
	 * @throws IOException
	 */
	private void startUpPhase()
	{
		StartUpView startUpView = new StartUpView();
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
					phase.setPhase("Start up phase",p);
					int ctryId = startUpView.askCountry(p);
					phase.setAction(p.getName() + " added 1 army in " + map.countries.get(ctryId-1).getName() + "\n");
					map.addArmiesFromHand(ctryId, 1);
				}
			}
		}while(remainingPlayers.size() != 0);
	}
	
	/**
	 * This method collects all the inputs in the reinforcement phase.
	 * @param p The current player that is in the reinforcementPhase
	 */
	private void reinforcementPhase(Player p) 
	{
		int armyNum = map.calculateArmyNum(p);
		p.setArmies(armyNum);
		p.reinforce();
	}
	
	/**
	 * This method collects all the inputs in the attack phase by calling views. The player can choose origin and destination, and armies number.
	 * Origin and destination countries must be connected.
	 * @param p The current player that is in the fortificationPhase
	 */
	private void attackPhase(Player p) 
	{
		p.attack();
	}

	/**
	 * This method deals with the logic of the fortification phase. The player can choose origin and destination, and armies number.
	 * Origin and destination countries must be connected.
	 * @param p The current player that is in the fortificationPhase
	 */
	private void fortificationPhase(Player p) 
	{
		p.fortify();
	}	
}