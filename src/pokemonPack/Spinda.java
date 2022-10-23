package pokemonPack;

import javax.swing.*;

public class Spinda extends Pokemon {

	public Spinda(int avgLevel) {
		super("Spinda", new ImageIcon("SpindaSpriteFrontV2.png").getImage(),
				getRandomNumberInRange(35, 57), getRandomNumberInRange(2, 4), getRandomNumberInRange(0, 1));
		this.xPosition = 415;
		this.yPosition = 60;
		this.calibrateStats();
		this.level = avgLevel;
	}

	@Override
	public void specialAttack(Pokemon attackedPokemon) {
	}
}
