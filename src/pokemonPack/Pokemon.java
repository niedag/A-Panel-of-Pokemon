package pokemonPack;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import entityPackage.*;

public abstract class Pokemon extends GameEntity {
	
	//stats and stuf
	private int atk;
	private int hp; //changes over time
	private int XP;
	private int baseHP; //remains constant
	private int spAtk;
	private int XPToNextLevel;
	
	protected int level; //makes changing enemy levels easier
	
	//Position on Screen
	protected int xPosition; //makes setting x and y position easier
	protected int yPosition;
	
	//Moves 
	private ArrayList<Move> pokemonMoveList = new ArrayList<Move>();
	private int maxMoves = 4;
	
	//For defeated enemies only
	protected int XPValue;
	
	public Pokemon(String name, Image image, int hp, int atk, int spAtk) {
		super(name, image);
		this.hp = hp;
		this.atk = atk;
		
		this.spAtk = spAtk;
		XP = 0;
		level = 1;
		baseHP = hp;
		this.entryBehavior = "NOTHING"; //can probably just remove this
		XPToNextLevel = (int)(Math.pow(level, 2.5));
	}
	
	
	public abstract void specialAttack(Pokemon attackedPokemon);
	
	public void setHP(int HP) {
		this.hp = hp;
	}
	
//	public void levelUp() { //increase stats each level
//		this.level++;
//		this.atk += getRandomNumberInRange(1, 3);
//		this.baseHP += getRandomNumberInRange(2, 5);
//		this.hp = baseHP; //restore to full health after each level
//		this.XP = 0;
//		this.spAtk += getRandomNumberInRange(0, 3);
//		this.XPToNextLevel = (int)(Math.pow(level, 3)); //all require the same amonut of exp per level
//	}
	
	public String levelUp() {
		this.level++;
		int gainedAtk = getRandomNumberInRange(2, 4);
		int gainedHp = getRandomNumberInRange(2, 4);
		int gainedSpAtk =  getRandomNumberInRange(1, 3);
		this.atk += gainedAtk;
		this.baseHP += gainedHp;
		this.spAtk += gainedSpAtk;
		
		this.hp = baseHP; //restore to full health after each level
		this.XP = 0;
		
		this.XPToNextLevel = (int)(Math.pow(level, 2.5)); //formula for xp for next level
		return " gained \n +" + gainedAtk + " atk \n +"+ gainedHp + " HP \n + "+ gainedSpAtk + " SpAtk";
	}
	
	public void calibratePlayerStats() {
		if(level == 1) return;
		int counter = 1;
		while(counter < level) {
			atk += getRandomNumberInRange(1, 3);
			baseHP += getRandomNumberInRange(2, 3);
			spAtk += getRandomNumberInRange(1, 2);
			counter++;
		}
		hp = baseHP;
	}
	
	public void calibrateStats() { //for random pokemon encounters above lv 1
		if(level == 1) return;
		int counter = 1;
		while(counter < level) {
			atk += getRandomNumberInRange(2, 10);
			if(counter >= 20) {
				atk += getRandomNumberInRange(5, 8);
				baseHP += getRandomNumberInRange(20, 44);
			} else {
				baseHP += getRandomNumberInRange(10, 28);
			}
			spAtk += getRandomNumberInRange(5, 18);
			counter++;
		}
		hp = baseHP;
	}
	
	public void calibrateMuscleChicStats() { //not overridden in torchic class because it easier to manage here
		int counter = 1;
		while(counter < level) {
			atk += getRandomNumberInRange(1, 2);
			baseHP += getRandomNumberInRange(90, 200);
			spAtk += getRandomNumberInRange(1, 8);
			counter++;
		}
		hp = baseHP;
		XPValue = (baseHP * level)/7;
	}
	public void setXPValue() {
		this.XPValue = (this.getBaseHP() * this.level)/3;
	}
	
	public int getXPValue() {
		return XPValue;
	}
	public int getXPToNextLevel() {
		return XPToNextLevel;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAttack() {
		return atk;
	}
	public int getSpAtk() {
		return spAtk;
	}
	public int getLevel() {
		return level;
	}
	public int getXP() {
		return XP;
	}
	public int getBaseHP() {
		return baseHP;
	}
	
	public void addOpponentStats(int opponentLevel, int opponentHP, int opponentAtk, int opponentSpAtk) {
		this.baseHP += opponentHP;
		this.hp += opponentHP;
		this.atk += opponentAtk;
		this.spAtk += opponentSpAtk;
		
		this.level += opponentLevel;
		this.XPToNextLevel = (int)(Math.pow(level, 2.5));
		calibratePlayerStats();
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	public void subtractHP(int damage) {
		hp = hp - damage;
		if(hp < 0) {
			hp = 0;
		}
	}
	public void addXP(int EXP) {
		this.XP += EXP;
	}
	public int getXPosition() {
		return this.xPosition;
	}
	public int getYPosition() {
		return this.yPosition;
	}
	
	public ArrayList<Move> getMoveList() { //unused
		return pokemonMoveList;
	}
	
	public void addPokeMove(Move move) { //unused
		if(pokemonMoveList.size() < 4) {
			pokemonMoveList.add(move);
		} else {
			//TODO: exchange moves for other moves..
		}
	}
	
	public static int getRandomNumberInRange(int min, int max) { //from stack exchange
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
}
 