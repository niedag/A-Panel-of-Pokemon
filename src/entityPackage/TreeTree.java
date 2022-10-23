package entityPackage;

import javax.swing.ImageIcon;

public class TreeTree extends GameEntity {

	public TreeTree() {
		super("TreeTree", new ImageIcon("TreeImage.JPG").getImage());
		this.entryBehavior = "NO_ENTRY";
	}
}
