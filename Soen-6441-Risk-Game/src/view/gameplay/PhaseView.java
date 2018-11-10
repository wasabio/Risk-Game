package view.gameplay;

import java.util.Observable;
import java.util.Observer;

import model.gameplay.Phase;
import model.map.Map;
import view.common.View;
/**
 * this class is the observer for every action during each phase
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 *
 */
public class PhaseView extends View implements Observer {

	/**
	 * the observer method of phases
	 */
	@Override
	public void update(Observable o, Object org) {
		// TODO Auto-generated method stub
		print((Phase) o);
	}

	/**
	 * print out the action and the phase
	 * @param o the current phase
	 */
	private void print(Phase o) {
		// TODO Auto-generated method stub
		System.out.print("\n"+o.getAction()+"\n");
		System.out.print("** P"+ o.getPlayer().getNumber()+" "+o.getPhase()+" ** ");
		
	}
	
	

}
