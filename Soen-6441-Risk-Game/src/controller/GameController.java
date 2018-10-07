package controller;

import model.map.Map;

public class GameController {

	public GameController()
	{
		execute();
	}
	
	private void execute()
	{
		Map map = new Map();
		map.setName("world.map");
		map.load();
	}
	
	//private void 
}
