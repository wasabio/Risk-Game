package view.mapEditor;

import java.util.Observable;
import java.util.Observer;

import model.map.Continent;
import model.map.Country;
import model.map.Map;
import view.common.View;

public class MapEditorView extends View implements Observer {
	@Override
	public void update(Observable o, Object arg) {
		print((Map) o);
	}

	public void print(Map map) {
		System.out.print("\n\n\n\n\n*****************************************\n");
		System.out.println("          World map - Editor mode");
		int counter = 0;
		for(Continent c : map.continents) {
			counter++;
			System.out.println(counter + " - Continent " + c.getName());
			
			for(Country ctry : c.countries) {
				counter++;
				System.out.print(counter + " - " + ctry.getName() + " --> ");
				/* Displaying neighbors */
				for(int i = 0; i < ctry.neighbors.size(); i++) {
					System.out.print(ctry.neighbors.get(i).getName());
					if(i != (ctry.neighbors.size() - 1)) {
						System.out.print(", ");
					}
				}
				System.out.println();
			}
			System.out.println();			
		}
		
		System.out.print("\n*****************************************\n");
	}


}