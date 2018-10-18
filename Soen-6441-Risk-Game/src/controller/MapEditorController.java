package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.gameplay.Player;
import model.map.Map;
import model.map.MapEditor;
import view.common.MapSelectionView;
import view.mapEditor.EditView;
import view.mapEditor.EditorMenuView;
import view.mapEditor.MapEditorView;

/**
 * The class is for the control of map editing part
 * 
 */
public class MapEditorController 
{
	private MapEditor mapEditor;
	private MapEditorView mapEditorView;
	private EditorMenuView editorMenuView;
	private EditView editView;

	/**
	 * This is the constructor method of MapEditorController
	 */
	public MapEditorController()
	{
		try 
		{
			mapEditor = new MapEditor();
			mapEditorView = new MapEditorView();
			editorMenuView = new EditorMenuView();
			editView = new EditView();
			
			mapEditor.addObserver(mapEditorView);
			
			execute();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * The method is to execute the map editor
	 * @throws IOException
	 */
	private void execute() throws IOException
	{
		int choice = editorMenuView.print();
		
		if(choice == 1) 
		{
			String mapName = editorMenuView.askMapName();
			mapEditor.setMapName(mapName);
		} 
		else if(choice == 2) 
		{
			MapSelectionView mapSelectionView = new MapSelectionView();
			String mapFilePath = mapSelectionView.selectMap();
			mapEditor.load(mapFilePath);
			mapEditorView.print(mapEditor.map);
		}
		
		int option;
		do 
		{
			option = editorMenuView.menu();
			
			switch(option) 
			{
			case 0: /* Save & exit */
				mapEditor.save();
				break;
			case 1: /* Add country */
				addCountry();
				break;
			case 2: /* Add continent */
				addContinent();
				break;
			case 3: /* Delete country */
				deleteCountry();
				break;
			case 4: /* Delete continent */
				deleteContinent();
				break;
			case 5: /* Exit */
				return;
			}
		}while(option != 0);
	}
	
	/**
	 * The method for delete country
	 */
	private void deleteCountry() {
		boolean deleted = false;
		int maxInput = mapEditor.getMaxInputNumber();
		int ctryNumber = editView.askCountryNumber(maxInput);
		deleted = mapEditor.deleteCountry(ctryNumber);
		
		if(!deleted) {
			// editView.errorDeletingCountry();
		}
	}

	/**
	 * The method for delete continent
	 */
	private void deleteContinent() {
		boolean deleted = false;
		int maxInput = mapEditor.getMaxInputNumber();
		int contNumber = editView.askContinentNumber(maxInput);
		deleted = mapEditor.deleteContinent(contNumber);
		
		if(!deleted) {
			// editView.errorDeletingContinent();
		}
	}

	/**
	 * The method for add Country
	 */
	public void addCountry() 
	{
		boolean added = false;
		if(mapEditor.map.continents.size() > 0) 
		{
			String ctryName = editView.askCountryName();
			int maxInput = mapEditor.getMaxInputNumber();
			int contNb = editView.askContinentNumber(maxInput);
			ArrayList<Integer> neighborNumbers = new ArrayList<Integer>();
			/* Ask all neighbors until user press 0 */
			int currentInput;
			do {
				currentInput = editView.askNeighbor(maxInput);
				if(currentInput != 0) 
				{
					neighborNumbers.add(currentInput);
				}
			}while(currentInput != 0);
			added = mapEditor.addCountry(ctryName, contNb, neighborNumbers);
		}
		
		 if(!added) 
		 {
			 editView.errorAddingCountry();
		 }
	}
	
	/**
	 * The method for add Continent
	 */
	public void addContinent() 
	{
		boolean added = false;
		
		String countinentName = editView.askContinentName();
		int bonus = editView.askBonus();
		added = mapEditor.addContinent(countinentName, bonus);
	
		if(!added) 
		{
			 editView.errorAddingContinent();
		}
	}
}
