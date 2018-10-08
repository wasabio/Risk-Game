package view;

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
				 int x = 1;//input.nextInt();
				 input.close();
				 return x;
			 }
			 else
			 {
				 input.nextLine();
				 System.out.println("Error : Please enter an integer number.");
			 }
		 }while(true);
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
