package view.gameplay;

import java.util.Observable;
import java.util.Observer;

import model.map.Continent;
import model.map.Country;
import model.map.Map;
import view.common.View;

/**
 * 
 * The class is dealing with showing the data and data changes of the map and update with the observer pattern 
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
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
	private void print(Map o) 
	{
		System.out.print("\n\n\n\n\n*****************************************\n");
		System.out.println("          World map - " + o.getPlayerNumber() + " players\n");

		for(Continent c : o.continents) 
		{
			System.out.print("Continent " + c.getName());
			if(c.getOwner() != null) 
			{
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
		
       
    }
 
	/**
	 * function for show the neighbor of a country
	 * @param ctry the current country
	 */
    public void showNeighbors(Country ctry){
        for(int i = 0; i < ctry.neighbors.size(); i++) {
            System.out.print(ctry.neighbors.get(i).getName());
            if(i != (ctry.neighbors.size() - 1))
            {
                System.out.print(", ");
            }
        }
    }
   
    /**
     * check the owner of the country in the current continent
     * @param c the current continent
     */
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
