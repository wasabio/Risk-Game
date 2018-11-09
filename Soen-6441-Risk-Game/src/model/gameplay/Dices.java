package model.gameplay;

import java.util.Collections;
import java.util.PriorityQueue;

import model.utilities.Random;
/**
 * The class that is dealing with dice functions
 * 
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 */
public class Dices {
	private PriorityQueue<Integer> attackerDices = new PriorityQueue<Integer>(Collections.reverseOrder());
	private PriorityQueue<Integer> defenderDices = new PriorityQueue<Integer>(Collections.reverseOrder());
	private int attackerMaxDices;
	private int defenderMaxDices;
	private int attDicesNumber = 0;
	private int defDicesNumber = 0;
	private int attackerLoss = 0;
	private int defenderLoss = 0;
	
	/**
	 * Dice for attack and defend player
	 * @param new_attackerArmiesNumber army number of attacker
	 * @param new_defenderArmiesNumber army number of defender
	 */
	public Dices(int new_attackerArmiesNumber, int new_defenderArmiesNumber) {
		attackerMaxDices = Math.min(new_attackerArmiesNumber-1, 3);
		defenderMaxDices = Math.min(new_defenderArmiesNumber, 2);
	}

	/**
	 * the max number of dice of attacker
	 * @return return the max number of attacker dice
	 */
	public int getAttackerMaxDices() {
		return attackerMaxDices;
	}
	
	/**
	 * the max number of defender
	 * @return return the max number of defender dice
	 */
	public int getDefenderMaxDices() {
		return defenderMaxDices;
	}
	
	/**
	 * function of start rolling the dice an compare the dice number
	 */
	public void roll() {
		if(attDicesNumber == 0 && defDicesNumber == 0) {	/* All-out mode */
			attDicesNumber = attackerMaxDices;
			defDicesNumber = defenderMaxDices;
		}
		/* Rolling attacker dices */
		for(int dice = 1; dice <= attDicesNumber; dice++) {
			int a = rollADice();
			System.out.println("att:" + a);
			attackerDices.add(a);
		}
		/* Rolling defender dices */
		for(int dice = 1; dice <= defDicesNumber; dice++) {
			int a = rollADice();
			System.out.println("def:" + a);
			defenderDices.add(a);
		}
		
		/* Comparing dice results */
		while(attackerDices.size() != 0 && defenderDices.size() != 0) {
			int attDice = attackerDices.remove();	//Max attacker dice
			int defDice = defenderDices.remove();	//Max defender dice
			
			if(defDice >= attDice)	attackerLoss++;	
			else	defenderLoss++;
		}
	}
	
	/**
	 * show attacker loss
	 * @return attacker loss message
	 */
	public int getAttackerLoss() {
		System.out.println("attloss:" + attackerLoss);
		return attackerLoss;
	}
	
	/**
	 * show defender loss
	 * @return defender loss message
	 */
	public int getDefenderLoss() {
		System.out.println("defloss:" + defenderLoss);
		return defenderLoss;
	}
	
	/**
	 * set the dice number for both side
	 * @param attDicesNumber attacker dice num
	 * @param defDicesNumber defender dice num
	 */
	public void setDicesNumber(int attDicesNumber, int defDicesNumber) {
		this.attDicesNumber = attDicesNumber;
		this.defDicesNumber = defDicesNumber;
	}
	
	/**
	 * A dice number constraint 1~6
	 * @return dice number between 1~6
	 */
	private int rollADice() {
		return Random.getRandomInt(1, 6);
	}
}
