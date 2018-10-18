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
public class MapEditorController {
	private MapEditor mapEditor;
	private MapEditorView mapEditorView;
	private EditorMenuView editorMenuView;
	private EditView editView;

	public MapEditorController()
	{
		try {
			mapEditor = new MapEditor();
			mapEditorView = new MapEditorView();
			editorMenuView = new EditorMenuView();
			editView = new EditView();
			
			mapEditor.addObserver(mapEditorView);
			
			execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void execute() throws IOException
	{
		int choice = editorMenuView.print();
		
		if(choice == 1) {
			String mapName = editorMenuView.askMapName();
			mapEditor.setMapName(mapName);
		} 
		else if(choice == 2) {
			MapSelectionView mapSelectionView = new MapSelectionView();
			String mapFilePath = mapSelectionView.selectMap();
			mapEditor.load(mapFilePath);
			mapEditorView.print(mapEditor.map);
		}
		
		int option;
		do {
			option = editorMenuView.menu();
			
			switch(option) {
			case 0: /* Save & exit */
				break;
			case 1: /* Add country */
				addCountry();
				break;
			case 2: /* Add continent */
				addContinent();
				break;
			case 3: /* Delete country */
				editView.askCountryNumber();
				break;
			case 4: /* Delete continent */
				int maxInput = mapEditor.getMaxInputNumber();
				editView.askContinentNumber(maxInput);
				break;
			case 5: /* Exit */
				return;
			}
		}while(option != 0);
	}
	
	public void addCountry() {
		String ctryName = editView.askCountryName();
		int maxInput = mapEditor.getMaxInputNumber();
		int contNb = editView.askContinentNumber(maxInput);
		ArrayList<Integer> neighborNumbers = new ArrayList<Integer>();
		/* Ask all neighbors until user press 0 */
		int currentInput;
		do {
			currentInput = editView.askNeighbor();
			if(currentInput != 0) {
				neighborNumbers.add(currentInput);
			}
		}while(currentInput != 0);
		 boolean added = mapEditor.addCountry(ctryName, contNb, neighborNumbers);
		 if(!added) {
			 editView.errorAddingCountry();
		 }
	}
	
	public void addContinent() {
		//editView.
	}
}
