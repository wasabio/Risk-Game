package view.gameplay;

import model.gameplay.Player;
import model.map.Country;
import view.common.View;

/**
 * 
 * The class is for the view to show the changes and the steps of the Reinforcement phase in the game.
 *
 */
public class ReinforcementView extends View {

	/**
	 * The method is dealing with the view functions of the current player asking the selected country.
	 * @param p The current player.
	 * @return Returning the selected country number if the value is not correct.
	 */
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
	
	/**
	 * The method is for the view functions of the current player asking army numbers with the input.
	 * @param p The current player.
	 * @return Returning the selected army number if the value is not correct.
	 */
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
