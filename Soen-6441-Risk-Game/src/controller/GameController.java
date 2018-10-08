package controller;
import java.io.Console;
import java.io.IOException;

import view.SetUpView;
import view.View;
import model.map.Map;

public class GameController {
	
	public GameController() 
	{
		execute();
	}
	
	private void execute() 
	{
		SetUpView setUpView = new SetUpView();
		setUpView.print();
		
		Map map = new Map();
		//map.load();
		
		
		

		

	}
	
	//private void 
}
