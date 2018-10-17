package view;

import model.gameplay.Player;
import model.map.Country;

public class ReinforcementView extends View {

	public int askCountry(Player p)
	{
		System.out.println("Reinforcement phase of P" + p.getNumber() + " - "  + p.getArmies() + " armies available");
		System.out.println("Enter the country number to reinforce: ");
		
		int country_number;
		boolean correctValue;
		
		do {
			country_number = getInteger();
			correctValue = isValueCorrect(country_number, 1, Country.Counter);
			if(correctValue && !p.owns(country_number)) {
				correctValue = false;
				System.out.println("Error : This country does not belong you.");
			}
		}while(!correctValue);
		
		return country_number;
	}
	
	public int askArmiesNumber(Player p)
	{
		System.out.println("Enter the number of armies to send: ");
		
		int armies_number;
		boolean correctValue;
		
		do {
			armies_number = getInteger();
			correctValue = isValueCorrect(armies_number, 1, p.getArmies());
		}while(!correctValue);
		
		return armies_number;
	}
}
