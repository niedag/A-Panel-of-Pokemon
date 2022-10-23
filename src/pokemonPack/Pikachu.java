package pokemonPack;

import javax.swing.ImageIcon;

public class Pikachu extends Pokemon {

	public Pikachu(int avgLevel) {
		super("Pikachu", new ImageIcon("PikachuSpriteFront.png").getImage(), 
				getRandomNumberInRange(35, 47), getRandomNumberInRange(3, 7), getRandomNumberInRange(8, 10));
		this.xPosition = 450;
		this.yPosition = 146;
		this.calibrateStats();
		this.level = avgLevel;
	}

	@Override
	public void specialAttack(Pokemon attackedPokemon) {
		// TODO Auto-generated method stub	
	}
}
