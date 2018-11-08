package view.gameplay;

import java.util.Observable;
import java.util.Observer;

import model.gameplay.Phase;
import model.map.Map;
import view.common.View;

public class PhaseView extends View implements Observer {

	@Override
	public void update(Observable o, Object org) {
		// TODO Auto-generated method stub
		print((Phase) o);
	}

	private void print(Phase o) {
		// TODO Auto-generated method stub
		System.out.print(o.getPhase()+" P"+o.getPlayer().getNumber()+" ");
	}
	
	

}
