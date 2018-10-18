package view.common;

import java.util.Observable;
import java.util.Observer;

import model.map.Continent;
import model.map.Country;
import model.map.Map;
/**
 * 
 * The class is dealing with showing the data and data changes of the map and update with the observer pattern 
 *
 */
public class MapView extends View implements Observer 
{	
	
	/**
	 * The override method for update observer of the printed map.
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
		print((Map) o);
	}

	/**
	 * The method is to print the information in a type of order for the map and showing to the user
	 * @param map The map file that had been selected and will show to the user
	 */
	private void print(Map map) 
	{
		System.out.print("\n\n\n\n\n*****************************************\n");
		System.out.println("          World map - " + map.getPlayerNumber() + " players\n");

		for(Continent c : map.continents) 
		{
			System.out.print("Continent " + c.getName());
			if(c.getOwner() != null) {
				System.out.println( " owned by P" + c.getOwner().getNumber() + " (+" + c.getExtraArmies() + ")");
			} else 
			{
				System.out.println();
			}

			
			for(Country ctry : c.countries) 
			{
				System.out.print("P" + ctry.getPlayer().getNumber() + " - " + ctry.getNumber() +"  " + ctry.getName() + " (" + ctry.getArmyNumber() + ") --> ");
				for(int i = 0; i < ctry.neighbors.size(); i++) {
					System.out.print(ctry.neighbors.get(i).getName());
					if(i != (ctry.neighbors.size() - 1)) 
					{
						System.out.print(", ");
					}
				}
				System.out.println();
			}
			System.out.println();			
		}
		
		System.out.print("\n*****************************************\n\n");
	}

}
