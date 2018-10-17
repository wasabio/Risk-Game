package view.gameplay;

import model.gameplay.Player;
import model.map.Country;
import view.common.View;

public class StartUpView extends View {
	
	public int askCountry(Player p) {
		System.out.println("Startup phase of P" + p.getNumber() + " - "  + p.getArmies() + " armies available");
		System.out.println("Enter the country number you want to deploy 1 army: ");

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
}
