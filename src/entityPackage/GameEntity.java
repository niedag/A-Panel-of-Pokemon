package entityPackage;

import java.awt.Image;

public abstract class GameEntity {
	// Could be Hero, Enemy (Slime/Wizard...etc), Tree, Blank, Castle, etc
	private String name; // private => subclasses CANNOT directly access (unlike protected/public/package)
	private Image image;
	protected String entryBehavior;
	
	public GameEntity(String name, Image image) {
		this.name = name;
		this.image = image;
	}
	public GameEntity(String name) {
		this.name = name;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Image getImage() {
		return image;
	}

	public String toString() {
		return name;
	}
	public String getEntryBehavior() {
		return entryBehavior;
	}
}