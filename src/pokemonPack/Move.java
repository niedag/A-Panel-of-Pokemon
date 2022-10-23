package pokemonPack;

public class Move {
	
	private String name;
	private int damage;
	private int PP; //changes when used
	private int basePP; //constant
	
	
	public Move(String name, int damage, int PP) {
		this.name = name;
		this.damage = damage;
		this.PP = PP; //number of usages
		basePP = PP; 
	}
	
	public int getDamage() {
		return damage;
	}
	public int getPP() {
		return PP;
	}
	public void recoverPP() {
		PP = basePP;
	}
	public String toString() {
		return name;
	}
}
