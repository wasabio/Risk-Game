package view.gameplay;

import model.gameplay.Player;
import model.map.Country;
import view.common.View;

/**
 * 
 * This class is for showing the view of deploying armies in the map
 *
 */
public class FortificationView extends View {

	/**
	 * The method is to choose the origin country using the specify numbers.
	 * @param p The current player.
	 * @return Returning the selected country number if the value of the input is incorrect.
	 */
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
	
	/**
	 * The method is to choose the destination country using the specify numbers.
	 * @param p The current player.
	 * @return Returning the selected country number if the value of the input is incorrect.
	 */
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

	/**
	 * The method for showing the entered armies numbers and the changes. 
	 * It also has several conditions for checking the value is correct or not.
	 * @param p The current player.
	 * @param armiesMaxNumber The max number of the army that the current player can deploy.
	 * @return Returning the total army number that the current player can be deployed.
	 */
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

	/**
	 * The method is to showing the error messages for the origin country that is selected.
	 * @param p The current player.
	 * @return Returning the selected country number.
	 */
	public int originError(Player p) {
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
