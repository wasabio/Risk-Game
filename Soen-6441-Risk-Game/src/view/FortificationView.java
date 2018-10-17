package view;

import model.gameplay.Player;
import model.map.Country;

public class FortificationView extends View {

	public int chooseOriginCountry(Player p) {
		System.out.println("Fortification phase of P" + p.getNumber());
		System.out.println("Enter the origin country number (0 to skip): ");
		
		int country_number;
		boolean correctValue;
		
		do {
			country_number = getInteger();
			correctValue = isValueCorrect(country_number, 0, Country.Counter);
			if(!p.owns(country_number)) {
				correctValue = false;
				System.out.println("Error : This country does not belong you.");
			}
		}while(!correctValue);
		
		return country_number;
	}
	
	public int chooseDestinationCountry(Player p) {
		System.out.println("Fortification phase of P" + p.getNumber());
		System.out.println("Enter the destination country number: ");
		
		int country_number;
		boolean correctValue;
		
		do {
			country_number = getInteger();
			correctValue = isValueCorrect(country_number, 1, Country.Counter);
			if(!p.owns(country_number)) {
				correctValue = false;
				System.out.println("Error : This country does not belong you.");
			}
		}while(!correctValue);
		
		return country_number;
	}

	public int askArmiesNumber(Player p) {
		System.out.println("Fortification phase of P" + p.getNumber());
		System.out.println("Enter the destination country number: ");
		
		int country_number;
		boolean correctValue;
		
		do {
			country_number = getInteger();
			correctValue = isValueCorrect(country_number,1, Country.Counter);
			if(!p.owns(country_number)) {
				correctValue = false;
				System.out.println("Error : This country does not belong you.");
			}
		}while(!correctValue);
		
		return country_number;
	}
}
