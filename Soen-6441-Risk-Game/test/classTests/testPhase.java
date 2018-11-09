package classTests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.gameplay.Phase;
import model.gameplay.Player;
import model.map.Map;

import org.junit.Before;
public class testPhase {

	/**
	 * this method to test the functionality of Phase class
	 * @author Yueshuai
	 */
	Phase phase;
	Player p;
	Map map;
	@Before
	public void Before() {
		phase = new Phase();
		p = new Player(1,1);
		map = new Map();
		map.players.add(p);
		phase.setPhase("start up", p,1);
		phase.setAction("ok");
	}
	@Test
	public void test() {
		assertSame("start up",phase.getPhase());
		assertSame("ok",phase.getAction());
		assertSame(p,phase.getPlayer());
	}
	

}
