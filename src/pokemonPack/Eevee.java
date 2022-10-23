package pokemonPack;

import javax.swing.ImageIcon;

public class Eevee extends Pokemon {

	public Eevee(int avgLevel) {
		super("Eevee", new ImageIcon("EeveeSpriteFront.png").getImage(), 
				getRandomNumberInRange(27, 43), getRandomNumberInRange(3, 4), getRandomNumberInRange(2, 5));
		this.xPosition = 443;
		this.yPosition = 153;
		this.calibrateStats();
		this.level = avgLevel;
	}

	@Override
	public void specialAttack(Pokemon attackedPokemon) {
		// TODO Auto-generated method stub
		
	}
}
