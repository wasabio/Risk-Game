package controller;
import java.io.Console;
import java.io.IOException;

import view.View;

public class GameController extends View{

	public GameController() 
	{
		execute();
	}
	
	private void execute() 
	{
		System.out.println("Choose the number of players (2-6): ");
		boolean CorrectValue = true;
		int Player_num;
		do {
			Player_num = getInteger();
			CorrectValue = isValueCorrect(Player_num,2,6);
		}
		while(CorrectValue == false);
		
			
	}
	
	//private void 
}
