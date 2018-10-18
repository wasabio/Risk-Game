package view.mapEditor;

import view.common.View;

public class EditView extends View {
	/* Add country functions */
	public String askCountryName() {
		System.out.println("Enter the name of the country: ");
		String name = getString();
		return name;
	}
	 /**
	  * ask player to enter a valid continent number
	  * @param maxNumber the maximum number player can enter
	  * @return the number the player entered
	  */
	public int askContinentNumber(int maxNumber) {
		System.out.println("Enter the continent number (line number): ");
		int number;
		boolean correctValue;
		
		do {
			number = getInteger();
			correctValue = isValueCorrect(number, 0, maxNumber);
		}while(!correctValue);
		
		return number;
	}
	/**
	 * ask player to enter a valid neighbor to the country
	 * @param maxNumber maximum number player can enter
	 * @return the number of the country the player entered
	 */
	public int askNeighbor(int maxNumber) {
		System.out.println("Enter the neighbor of the country (line number, 0 to stop): ");
		int number;
		boolean correctValue;
		do {
			number = getInteger();
			correctValue = isValueCorrect(number,0, maxNumber);
		}while(!correctValue);
		return number;		 
	}
	
	/*Add continent functions */
	public String askContinentName() { 
		System.out.println("Enter the continent name: ");
		String name = getString();
		System.out.println();
		return name;
	}
	/**
	 * ask player to enter the number of bonus armies of the continent
	 * @return the bonus number the player entered
	 */
	public int askBonus() {
		System.out.println("Enter the the bonus of the continent(1-20): ");
		int number;
		boolean correctValue;
		do {
			number = getInteger();
			correctValue = isValueCorrect(number,1, 20);
		}while(!correctValue);
		return number;	
	}
	
	/* Remove country functions */
	/**
	 * ask player to enter the number of the country
	 * @return the country number the player entered
	 */
	public int askCountryNumber(int maxNumber) {
		System.out.println("Enter the country you want to delete (line number): ");
		int number;
		boolean correctValue;
		do {
			number = getInteger();
			correctValue = isValueCorrect(number,0, maxNumber);
		}while(!correctValue);
		return number;	
	}

	public void errorAddingCountry() {
		System.out.println("Error : The country could not be added (Wrong input or 0 continents).");
		
	}
	public void errorAddingContinent() {
		System.out.println("Error : The continent could not be added (Wrong input).");	
	}
	public void errorDeletingContinent() {
		System.out.println("Error : The continent could not be deleted (Wrong input).");	
	}
	public void errorDeletingCountry() {
		System.out.println("Error : The country could not be deleted (Wrong input).");	
	}
}
