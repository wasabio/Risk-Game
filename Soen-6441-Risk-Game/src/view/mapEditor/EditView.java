package view.mapEditor;

import view.common.View;

public class EditView extends View {
	/* Add country functions */
	public String askCountryName() {
		System.out.println("Enter the name of the country: ");
		String name = getString();
		System.out.println();
		return name;
	}
	 
	public int askContinentNumber(int maxNumber) {
		System.out.println("Enter the continent the country belongs to (line number): ");
		
		int number;
		boolean correctValue;
		
		do {
			number = getInteger();
			correctValue = isValueCorrect(number, 0, maxNumber);
		}while(!correctValue);
		
		return number;
	}
	 
	public int askNeighbor() {
		return 0;		 
	}
	
	/*Add continent functions */
	public String askContinentName() { 
		return null;
	}
	 
	public String askBonus() {
		return null;
	}
	
	/* Remove country functions */
	public int askCountryNumber() {
		return 0;
	}

	public void errorAddingCountry() {
		System.out.println("Error : The country could not be added (Wrong input or 0 continents).");
		
	}
}
