package view;

import java.util.Observable;
import java.util.Observer;

import model.map.Continent;
import model.map.Country;
import model.map.Map;

public class MapView extends View implements Observer {	
	@Override
	public void update(Observable o, Object arg) {
		print((Map) o);
	}
	static int countryNum = 1;
	private void print(Map map) {
		System.out.print("\n\n\n\n\n*****************************************\n");
		System.out.println("          World map - " + map.getPlayerNumber() + " players\n");

		for(Continent c : map.continents) {
			System.out.print("Continent " + c.getName());
			if(c.getOwner() != null) {
				System.out.println( " owned by P" + c.getOwner().getNumber() + " (+" + c.getExtraArmies() + ")");
			} else {
				System.out.println();
			}

			
			for(Country ctry : c.countries) {
				System.out.print("P" + ctry.getPlayer().getNumber() + " - " + countryNum++ +"  " + ctry.getName() + " (" + ctry.getArmyNumber() + ") --> ");
				for(int i = 0; i < ctry.neighbours.size(); i++) {
					System.out.print(ctry.neighbours.get(i).getName());
					if(i != (ctry.neighbours.size() - 1)) {
						System.out.print(", ");
					}
				}
				System.out.println();
			}
			System.out.println();			
		}
	}

}
