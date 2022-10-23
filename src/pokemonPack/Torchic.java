package pokemonPack;

import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class Torchic extends Pokemon {
	
	public Torchic() {
		super("Torchic", new ImageIcon("torchicbacksprite2.png").getImage(),
				getRandomNumberInRange(16, 20), getRandomNumberInRange(14, 19), getRandomNumberInRange(7, 15));
		this.xPosition = 120;
		this.yPosition = 195;
		addPokeMove(new Move("Tackle", this.getAttack(), 35));
		addPokeMove(new Move("Ember", this.getSpAtk(), 15));
	}
	
	@Override
	public void specialAttack(Pokemon attackedPokemon) {
		attackedPokemon.setHp(attackedPokemon.getHp() - this.getSpAtk());
		System.out.println(this.getName() + " used Ember! "
				+ attackedPokemon.getName() + " took "
				+ this.getSpAtk() + " damage!");
	}
}
