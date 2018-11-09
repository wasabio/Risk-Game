package view.gameplay;

import java.util.Observable;
import java.util.Observer;

import model.gameplay.Player;
import model.map.Continent;
import model.map.Map;
import view.common.View;

public class WorldDominationView extends View implements Observer {

	@Override
	public void update(Observable o, Object org) {
		print((Map) o);
	}
	
	private void print(Map o) {
		System.out.print("\n*****************************************\n\n");
        System.out.println(" World Domination View ");
       
        for(Player p : o.players)
        {
        	int c = (int)(100*p.getPercentage(p, o));
            System.out.println("Player " + p.getNumber() + " conquered Percentage " + c+"%");
        }
       
        for(Player p : o.players)
        {  
            System.out.println("Player "+ p.getNumber() + " total army : " + (p.getTotalArmy()+ p.getArmies()));
        }
       
        for(Continent c : o.continents)
        {
            checkOwner(c);
        }
       
        System.out.print("\n*****************************************\n\n");
	}
	
	 public void checkOwner(Continent c) {
	        if(c.getOwner() != null)
	        {
	            System.out.println("Continent " + c.getName() + " owned by P" + c.getOwner().getNumber());
	        } else
	        {
	            System.out.print("");
	        }
	    }
}
