package controller;

import view.common.MainMenuView;

/**
 * This class is the point of entry of the application. It controls the execution
 * flow of the main menu and let the user choose between playing or editing maps.
 * It is the first menu that is displayed to the user when he starts the program.
 */
public class MainController 
{
	
	/**
	 * Start the application by creating a main menu.
	 */
	public static void main(String[] args) 
	{
		mainMenu();
	}
	
	/**
	 * This function creates the main menu view that is displayed to the user. It also 
	 * controls the execution flow of the main menu and dispatch the work to other
	 * controllers, depending on the user's choice.
	 */
	public static void mainMenu() 
	{
		MainMenuView mainMenu = new MainMenuView();
		int choice = mainMenu.print();
		
		switch(choice)
		{
		case 1:
			new GameController();
			break;
		case 2:
			new MapEditorController();
			break;
		}
	}

}