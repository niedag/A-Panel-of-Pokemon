package entityPackage;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import pokemonPack.*;

public class Player extends GameEntity {

	private ArrayList<Pokemon> pokeArray = new ArrayList<Pokemon>();
	private int maxPartySize = 6;
	private String lastPosition = "OPEN_SPACE"; //gets entry behavior of previous space
	private boolean bossDefeated = false; 
	
	//Constructor 1
	public Player(String name) { //only moves around, does no fighting
		super(name, new ImageIcon("PlayerSprite.png").getImage());
	}
	//Constructor 2
	public Player() {
		super("Player", new ImageIcon("PlayerSprite.png").getImage());
	}
	//Constructor 3
	public Player(Pokemon pokemon) {
		super("Player", new ImageIcon("PlayerSprite.png").getImage());
		addPokemonToParty(pokemon);
	}
	
	public boolean getIsBossDefeated() {
		return bossDefeated;
	}
	public void setIsBossDefeated(boolean isDead) {
		bossDefeated = isDead;
	}
	
	public void addPokemonToParty(Pokemon pokemon2Add) {
		if(pokeArray.size() >= maxPartySize) {
			System.out.println(this.getName() + "'s party is full");
			return;
		}
		pokeArray.add(pokemon2Add);
	}
	public Pokemon getCurrentPokemon() {
		return pokeArray.get(0); //gets the first Pokemon in your party
	}
	public ArrayList<Pokemon> getPlayerParty() {
		return this.pokeArray;
	}
	public void updatePlayerParty() {}
	
	//TEST PURPOSE ONLY
	public void clearParty() { 
		System.out.println("Player party reset");
		pokeArray = new ArrayList<Pokemon>();
	}
	public int getMaxPartySize() {
		return maxPartySize;
	}
	public void setLastPosition(String entryBehavior) {
		lastPosition = entryBehavior;
	}
	public String getLastPosition() {
		return lastPosition;
	}

	
}