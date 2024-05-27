package pokemonPack;

import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class Mudkip extends Pokemon {

	
	public Mudkip() {
		super("Mudkip", new ImageIcon("MudkipBackSpriteV2.png").getImage(), 
				getRandomNumberInRange(500, 760), getRandomNumberInRange(1000,1400), getRandomNumberInRange(4,	13));
		this.xPosition = 170;
		this.yPosition = 234;
		this.addPokeMove(new Move("Water Gun", 40, 30));
		this.addPokeMove(new Move( "Tackle", 50, 35));
	}

	@Override
	public void specialAttack(Pokemon attackedPokemon) {
		attackedPokemon.setHp(attackedPokemon.getHp() - this.getSpAtk());
		System.out.println(this.getName() + " used Water Gun! "
				+ attackedPokemon.getName() + " took "
				+ this.getSpAtk() + " damage!");
	}
}
