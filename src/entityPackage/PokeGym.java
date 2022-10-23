package entityPackage;

import pokemonPack.*;
import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class PokeGym extends GameEntity {

	public PokeGym() {
		super("PokeGym", new ImageIcon("PokeGym.png").getImage());
		// TODO Auto-generated constructor stub
		this.entryBehavior = "POKE_GYM";
	}
}
