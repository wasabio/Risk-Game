package view;

import java.util.Scanner;

import model.gameplay.Player;
import model.map.Country;
import model.map.Map;

public class StartUpView extends View {

	public void print() {
		//Yueshuai implementation
		//Use input methods from the view (check other view to get integer)
		
		Map map = new Map();
		int ctyCode;
		for(Player p : map.players) {
			System.out.println("Enter the country code you want to deploy your army: ");
			ctyCode=getInteger();
			//to check if the player entered the country code within right range
			if(isValueCorrect(ctyCode,1,map.countries.size())) {
				//to check if the player chose their own territory
				if(p.getNumber() == map.countries.get(ctyCode).getPlayer().getNumber()) {
					//deploy the 1 army to the territory the player chose 
					int armyNum = map.countries.get(ctyCode).getArmyNumber();
					map.countries.get(ctyCode).setArmyNumber(++armyNum);
				}
				else {
					System.out.println("This country does not belong to you");
				}
				
			}
			else {
				System.out.println("Country code out of range!");
			}
		}
		}
}
