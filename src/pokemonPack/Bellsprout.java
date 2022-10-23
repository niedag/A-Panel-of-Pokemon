package pokemonPack;

import javax.swing.ImageIcon;

public class Bellsprout extends Pokemon {

	public Bellsprout(int avgLevel) {
		super("Bellsprout", new ImageIcon("BellsproutFrontSprite.png").getImage(), 
				getRandomNumberInRange(28, 35), getRandomNumberInRange(4, 6), getRandomNumberInRange(8, 10));
		this.xPosition = 443;
		this.yPosition = 130;
		this.calibrateStats();
		this.level = avgLevel;
	}

	@Override
	public void specialAttack(Pokemon attackedPokemon) {
		// TODO Auto-generated method stub
		
	}
}