package model.gameplay;

import java.util.Collections;
import java.util.PriorityQueue;

import model.utilities.Random;
/**
 * The class for the main functions of dices
 * @author 
 *
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
	 * Dice value for both attack and defend
	 * @param new_attackerArmiesNumber attacker's army number
	 * @param new_defenderArmiesNumber defender's army number
	 */
	public Dices(int new_attackerArmiesNumber, int new_defenderArmiesNumber) {
		attackerMaxDices = Math.min(new_attackerArmiesNumber-1, 3);
		defenderMaxDices = Math.min(new_defenderArmiesNumber, 2);
	}

	/**
	 * get the max number of attacker's dice
	 * @return return the attacker max dice number
	 */
	public int getAttackerMaxDices() {
		return attackerMaxDices;
	}
	
	/**
	 * get the max number of Defender dice
	 * @return return the Defender max dice number
	 */
	public int getDefenderMaxDices() {
		return defenderMaxDices;
	}
	
	/**
	 * The roll function to check both player use the dice and compare it
	 */
	public void roll() {
		if(attDicesNumber == 0 && defDicesNumber == 0) {	/* All-out mode */
			attDicesNumber = attackerMaxDices;
			defDicesNumber = defenderMaxDices;
		}
		/* Rolling attacker dices */
		for(int dice = 1; dice <= attDicesNumber; dice++) {
			int a = rollADice();
			attackerDices.add(a);
		}
		/* Rolling defender dices */
		for(int dice = 1; dice <= defDicesNumber; dice++) {
			int a = rollADice();
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
	 * get message of attacker loss
	 * @return message of attacker loss
	 */
	public int getAttackerLoss() {
		return attackerLoss;
	}
	
	/**
	 * get message of Defender loss
	 * @return message of Defender loss
	 */
	public int getDefenderLoss() {
		return defenderLoss;
	}
	
	/**
	 * set the dice number for both player
	 * @param attDicesNumber dice number of attacker
	 * @param defDicesNumber dice number of defender
	 */
	public void setDicesNumber(int attDicesNumber, int defDicesNumber) {
		this.attDicesNumber = attDicesNumber;
		this.defDicesNumber = defDicesNumber;
	}
	
	/**
	 * roll the single dice with random value 1~6
	 * @return the 1~6 number
	 */
	private int rollADice() {
		return Random.getRandomInt(1, 6);
	}
}
