package pokemonPack;

import javax.swing.*;

public class Tallow extends Pokemon {

	public Tallow(int avgLevel) {
		super("Tallow", new ImageIcon("TallowSpriteFront.png").getImage(),
				getRandomNumberInRange(29, 39), getRandomNumberInRange(3, 5), getRandomNumberInRange(1, 2));
		this.xPosition = 400;
		this.yPosition = 100;
		this.calibrateStats();
		this.level = avgLevel;
	}
	
	@Override
	public void specialAttack(Pokemon attackedPokemon) {}

	
}
