package pokemonPack;

import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class Treecko extends Pokemon {
	public Treecko() {
		super("Treecko", new ImageIcon("TreeckoBackSprite.png").getImage(),
				getRandomNumberInRange(25, 32), getRandomNumberInRange(11, 15), getRandomNumberInRange(2, 9));
		this.xPosition = 170;
		this.yPosition = 234;
	}
	
	@Override
	public void specialAttack(Pokemon attackedPokemon) {
		attackedPokemon.setHp(attackedPokemon.getHp() - this.getSpAtk());
		System.out.println(this.getName() + " used Vine Whip! "
				+ attackedPokemon.getName() + " took "
				+ this.getSpAtk() + " damage!");
	}
}
