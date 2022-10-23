package entityPackage;

import pokemonPack.*;
import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class PokeCenter extends GameEntity {

	public PokeCenter() {
		super("PokeCenter", new ImageIcon("PokeCenter.png").getImage());
		// TODO Auto-generated constructor stub
		this.entryBehavior = "POKE_CENTER";
	}
	
}
