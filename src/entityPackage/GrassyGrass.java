package entityPackage;

import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class GrassyGrass extends GameEntity {
	static int grassyCount = 0;
	
	
	public GrassyGrass() {
		super("Grassy Grass", new ImageIcon("GrassPatch2.JPG").getImage());
		this.entryBehavior = "INTERACTABLE";
		
		grassyCount++;
	}
}
