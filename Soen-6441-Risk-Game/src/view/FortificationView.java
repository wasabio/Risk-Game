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
			if(correctValue && !p.owns(country_number) && country_number != 0) {
				correctValue = false;
				System.out.println("Error : This country does not belong you.");
			}
		}while(!correctValue);
		
		return country_number;
	}
	
	public int chooseDestinationCountry(Player p) {
		System.out.println("Enter the destination country number: ");
		
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

	public int askArmiesNumber(Player p, int armiesMaxNumber) {
		System.out.println("Enter the number of armies: ");
		
		int armiesNumber;
		boolean correctValue;
		
		do {
			armiesNumber = getInteger();
			correctValue = isValueCorrect(armiesNumber, 2, Country.Counter);
			if(armiesNumber > (armiesMaxNumber-1) || armiesNumber <= 0) {
				correctValue = false;
				System.out.println("Error : Wrong number of armies.");
			}
		}while(!correctValue);
		
		return armiesNumber;
	}

	public int armyError(Player p) {
		System.out.println("Error : You can't move armies from a country that has only 1 army.");
		
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
}
