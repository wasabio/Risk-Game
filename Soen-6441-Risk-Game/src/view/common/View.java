package view.common;

import java.util.Scanner;

public abstract class View {
	
	private static Scanner input;
	
	protected int getInteger() 
	{
		 input = new Scanner(System.in);
		 
		 do
		 {
			 if(input.hasNextInt())
			 {
				 return input.nextInt();
			 }
			 else
			 {
				 input.nextLine();
				 System.out.println("Error : Please enter an integer number.");
			 }
		 }while(true);
	}
	
	protected String getString() 
	{
		String inputString = "";
		do {
			input = new Scanner(System.in);
			inputString = input.nextLine();
		}while(inputString == null || inputString.equals(""));
		return inputString;
	}
	
	protected boolean isValueCorrect(int value, int minimum, int maximum)
	{
		if(value < minimum || value > maximum)
		{
			System.out.println("Error : Number out of range");
			return false;
		}
		
		return true;
	}

}
