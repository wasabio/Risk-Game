package controller;

import java.io.IOException;
import view.SetUpView;
import model.map.Map;

public class GameController {

	public GameController()
	{
		try {
			execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void execute() throws IOException 
	{
		SetUpView setUpView = new SetUpView();
		setUpView.print();
		String mapFilePath = setUpView.selectMap();
		
		Map map = new Map();
		map.load(mapFilePath);
		
	}	
}
	

