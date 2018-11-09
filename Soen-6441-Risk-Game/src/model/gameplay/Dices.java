package model.gameplay;

import java.util.Collections;
import java.util.PriorityQueue;

import model.utilities.Random;

public class Dices {
	private PriorityQueue<Integer> attackerDices = new PriorityQueue<Integer>(Collections.reverseOrder());
	private PriorityQueue<Integer> defenderDices = new PriorityQueue<Integer>(Collections.reverseOrder());
	private int attackerMaxDices;
	private int defenderMaxDices;
	private int attDicesNumber = 0;
	private int defDicesNumber = 0;
	private int attackerLoss = 0;
	private int defenderLoss = 0;
	
	public Dices(int new_attackerArmiesNumber, int new_defenderArmiesNumber) {
		attackerMaxDices = Math.min(new_attackerArmiesNumber, 3);
		defenderMaxDices = Math.min(new_defenderArmiesNumber, 2);
	}

	public int getAttackerMaxDices() {
		return attackerMaxDices;
	}
	
	public int getdefenderMaxDices() {
		return defenderMaxDices;
	}
	
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
	
	public int getAttackerLoss() {
		System.out.println("attloss:" + attackerLoss);
		return attackerLoss;
	}
	
	public int getDefenderLoss() {
		System.out.println("defloss:" + defenderLoss);
		return defenderLoss;
	}
	
	public void setDicesNumber(int attDicesNumber, int defDicesNumber) {
		this.attDicesNumber = attDicesNumber;
		this.defDicesNumber = defDicesNumber;
	}
	
	private int rollADice() {
		return Random.getRandomInt(1, 6);
	}
}
