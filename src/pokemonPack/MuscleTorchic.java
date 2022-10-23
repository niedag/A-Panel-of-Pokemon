package pokemonPack;

import javax.swing.ImageIcon;

public class MuscleTorchic extends Pokemon {

	public MuscleTorchic() {
		super("Muscle-chic", new ImageIcon("MuscleTorchic.JPG").getImage(),
			getRandomNumberInRange(60, 90), getRandomNumberInRange(10, 18), getRandomNumberInRange(25, 29));
		this.xPosition = 390;
		this.yPosition = 15;
		this.level = getRandomNumberInRange(90, 100);
		this.calibrateMuscleChicStats();
		addPokeMove(new Move("Flex", this.getAttack()*2, 5));	//not implemented yet 
	}
	
	@Override
	public void specialAttack(Pokemon attackedPokemon) {}
}
