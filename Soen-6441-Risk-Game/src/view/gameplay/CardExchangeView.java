package view.gameplay;

import java.util.Observable;
import java.util.Observer;

import model.gameplay.Phase;
import view.common.View;

public class CardExchangeView extends View implements Observer 
{	
	/**
	 * The override method for update observer of the printed map.
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
		print((Phase) o);
	}
	
	public void print(Phase phase) {
		if(phase.getPhase().equals("Reinforcement phase")) {
			System.out.println("Your cards : " + phase.getPlayer().getCards());
		}
	}
}
