package entityPackage;
import java.awt.*;
import java.util.*;
import javax.swing.ImageIcon;

public class OpenSpace extends GameEntity {

	public OpenSpace() {
		super("Open Space", new ImageIcon("GroundPatch2.JPG").getImage());
		// TODO Auto-generated constructor stub
		this.entryBehavior = "PASSABLE";
	}	
}
