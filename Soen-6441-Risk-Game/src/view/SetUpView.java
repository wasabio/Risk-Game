package view;

public class SetUpView extends View {

	public int print() 
	{
		System.out.println("Choose the number of players (2-6): ");
		int player_num;
		boolean correctValue;
		
		do {
			player_num = getInteger();
			correctValue = isValueCorrect(player_num, 2, 6);
		}
		while(!correctValue);
		
		return player_num;
	}
}
