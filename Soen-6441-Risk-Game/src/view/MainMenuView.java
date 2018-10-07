package view;

public class MainMenuView extends View {
		
	public int print() 
	{
		int choice = 1;
		
		System.out.println("*****    Welcome in the Risk Game of team 14    *****");
		System.out.println();
		System.out.println("Choose an option by entering the number :");
		System.out.println("1 - Play");
		System.out.println("2 - Map Editor");
		
		do
		{
			choice = getInteger();
			
			if(isValueCorrect(choice, 1, 2))
			{
				return choice;
			}
		}while(true);
	}
}
