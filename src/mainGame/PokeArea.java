package mainGame;

import entityPackage.*;
import pokemonPack.*;

import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import java.util.*;

import javax.sound.sampled.*;
import javax.swing.*;

public class PokeArea {

	private GamePanel view;
	private int numRowsInGameWorld = 20, numColsInGameWorld = 20;
	private int playerCurrRow = 0, playerCurrCol = 0;
	private int numGrassyGrasses = 120, numTrees = 80;
	private PokemonGameMain pokeMain;
	private Pokemon currentOpponent;
	private Player player;
	
	private int totalBattlesWon = 0;
	private int averageLevel = 2;
	
	List<EntityInfo> entityInfo = new ArrayList<EntityInfo>();
	private GameEntity[][] gameEntities = new GameEntity[numRowsInGameWorld][numColsInGameWorld];
	boolean[][] occupied = new boolean[numRowsInGameWorld][numColsInGameWorld];
	
	//trying to do something funky here by resetting the map to the original version to 
	//avoid the problem about the bushes not reappearing after steeping on them. 
	//RESOLVED 11/09/19
	
	//constructor
	public PokeArea(Player player, PokemonGameMain pokeMain) { //rotate a reference to Player across all classes.
		this.player = player;
		this.pokeMain = pokeMain;
		entityInfo.add(new EntityInfo(new GrassyGrass(), numGrassyGrasses));
		entityInfo.add(new EntityInfo(new TreeTree(), numTrees));
		this.setUpGameData();
	}
	
	public PokeArea() {
		//Test Constructor
	}
	
	
	private void setUpGameData() {
		for(int i = 0; i < numColsInGameWorld; i++) {
			for(int a = 0; a < numRowsInGameWorld; a++) {
				gameEntities[i][a] = new OpenSpace();
			}
		}
		gameEntities[playerCurrRow][playerCurrCol] = player;
		gameEntities[numRowsInGameWorld - 2][numColsInGameWorld - 1] = new PokeCenter();
		gameEntities[0][numColsInGameWorld -1] = new PokeGym();
		occupied[playerCurrRow][playerCurrCol] = true;
		occupied[numRowsInGameWorld - 1][numColsInGameWorld - 1] = true;
		occupied[0][numColsInGameWorld -1] = true;
		
		for(EntityInfo entityType:entityInfo) {
			
			int row, col;
			for(int i = 0; i < entityType.getNumOfThisEntity(); i++) {
				do {
					row = (int)(Math.random()*(numRowsInGameWorld - 3)) + 2; // generate rand row  (leave a bit of space around both edges)
					col = (int)(Math.random()*(numColsInGameWorld - 3)) + 2; // generate rand col  (leave a bit of space around both edges) 
				} while(occupied[row][col]); // keep looking until you find an open spot (Open Spaces were not marked as occupied)
				// Add the appropriate object to this randomly chosen spot o the game board
				if (entityType.getEntity() instanceof GrassyGrass) {
					gameEntities[row][col] = new GrassyGrass();
				}
				else if(entityType.getEntity() instanceof TreeTree) {
					gameEntities[row][col] = new TreeTree();
				}
				
				occupied[row][col] = true; //eac entity will take up one 
			}
		}
	}
	
	public void movePlayer(String direction) {
		int playerPrevRow = playerCurrRow, playerPrevCol = playerCurrCol;
		GameEntity entityOccupyingSpace = null;
		
		if(direction.equals("LEFT")) {
			if(playerCurrCol == 0) return;
			entityOccupyingSpace = gameEntities[playerCurrRow][playerCurrCol - 1];
			if(entityOccupyingSpace.getEntryBehavior().equals("NO_ENTRY"))	return;
			else if(entityOccupyingSpace.getEntryBehavior().equals("POKE_CENTER")) {
				pokeCenter();
				return;
			}
			else if(entityOccupyingSpace.getEntryBehavior().equals("INTERACTABLE")) {
				if(player.getLastPosition() == "INTERACTABLE") {
					gameEntities[playerPrevRow][playerPrevCol] = new GrassyGrass();
				} else {
					gameEntities[playerPrevRow][playerPrevCol] = new OpenSpace();
				}
				bush(player);
				player.setLastPosition("INTERACTABLE");

			} else {
				if(player.getLastPosition() == "INTERACTABLE") {
					gameEntities[playerPrevRow][playerPrevCol] = new GrassyGrass();
					player.setLastPosition("OPEN_SPACE");
				} else {
					gameEntities[playerPrevRow][playerPrevCol] = new OpenSpace();
					player.setLastPosition("OPEN_SPACE");	
				}
			}
			playerCurrCol--;
		}
		else if(direction.equals("RIGHT")) {
			if(playerCurrCol == numColsInGameWorld -  1) return;
			entityOccupyingSpace = gameEntities[playerCurrRow][playerCurrCol + 1];
			if(entityOccupyingSpace.getEntryBehavior().equals("NO_ENTRY")) return;
			else if(entityOccupyingSpace.getEntryBehavior().equals("POKE_CENTER")) {
				pokeCenter();
				return;
			}
			else if(entityOccupyingSpace.getEntryBehavior().equals("POKE_GYM")) {
				pokeGym();
				return;
			}
			else if(entityOccupyingSpace.getEntryBehavior().equals("INTERACTABLE")) {		
				if(player.getLastPosition() == "INTERACTABLE") {
					gameEntities[playerPrevRow][playerPrevCol] = new GrassyGrass();
				} 
				else {
					gameEntities[playerPrevRow][playerPrevCol] = new OpenSpace();
				}
				bush(player);
				player.setLastPosition("INTERACTABLE");
				
			} else {
				if(player.getLastPosition() == "INTERACTABLE") {
					gameEntities[playerPrevRow][playerPrevCol] = new GrassyGrass();
					player.setLastPosition("OPEN_SPACE");
				} else {
					gameEntities[playerPrevRow][playerPrevCol] = new OpenSpace();
					player.setLastPosition("OPEN_SPACE");	
				}
			}
			playerCurrCol++;
		}
		else if(direction.equals("UP")) {
			if(playerCurrRow == 0) return;
			entityOccupyingSpace = gameEntities[playerCurrRow - 1][playerCurrCol];
			if(entityOccupyingSpace.getEntryBehavior().equals("NO_ENTRY")) return;
			else if(entityOccupyingSpace.getEntryBehavior().equals("POKE_CENTER")) {
				pokeCenter();
				return;
			}
			else if(entityOccupyingSpace.getEntryBehavior().equals("POKE_GYM")) {
				pokeGym();
				return;
			}
			else if(entityOccupyingSpace.getEntryBehavior().equals("INTERACTABLE")) {
				if(player.getLastPosition() == "INTERACTABLE") {
					gameEntities[playerPrevRow][playerPrevCol] = new GrassyGrass();
				} else {
					gameEntities[playerPrevRow][playerPrevCol] = new OpenSpace();
				}
				bush(player);
				player.setLastPosition("INTERACTABLE");
				
			} else {
				if(player.getLastPosition() == "INTERACTABLE") {
					gameEntities[playerPrevRow][playerPrevCol] = new GrassyGrass();
					player.setLastPosition("OPEN_SPACE");
				} else {
					gameEntities[playerPrevRow][playerPrevCol] = new OpenSpace();
					player.setLastPosition("OPEN_SPACE");	
				}
			}
			playerCurrRow--;
		}
		else if(direction.equals("DOWN")) {
			if(playerCurrRow == numRowsInGameWorld - 1) return;
			entityOccupyingSpace = gameEntities[playerCurrRow + 1][playerCurrCol];
			if(entityOccupyingSpace.getEntryBehavior().equals("NO_ENTRY")) return;
			else if(entityOccupyingSpace.getEntryBehavior().equals("POKE_CENTER")) {
				pokeCenter();
				return;
			}
			else if(entityOccupyingSpace.getEntryBehavior().equals("INTERACTABLE")) {					
				if(player.getLastPosition() == "INTERACTABLE") {
					gameEntities[playerPrevRow][playerPrevCol] = new GrassyGrass();
				} else {
					gameEntities[playerPrevRow][playerPrevCol] = new OpenSpace();
				}
				bush(player);
				player.setLastPosition("INTERACTABLE");

			} else { //handles case where next space is OPEN_SPACE
				if(player.getLastPosition() == "INTERACTABLE") {
					gameEntities[playerPrevRow][playerPrevCol] = new GrassyGrass();
					player.setLastPosition("OPEN_SPACE");
				} else {
					gameEntities[playerPrevRow][playerPrevCol] = new OpenSpace();
					player.setLastPosition("OPEN_SPACE");	
				}
			}
			playerCurrRow++;
		}
		//System.out.println(player.getLastPosition());
		
		gameEntities[playerCurrRow][playerCurrCol] = player;
	}
	
	public void pokeCenter() {
		player.getCurrentPokemon().setHp(player.getCurrentPokemon().getBaseHP());
		JOptionPane.showMessageDialog(view, player.getCurrentPokemon().getName() + "'s health has been restored");
	}
	
	public void pokeGym() {
		view.warningMessage();
	}
	
	public void bush(Player player) {
		//TODO: set up random interactions upon stepping on it.
		int rand = randomWithRange(0,18); //range from 0 to x amount of different Pokemon in the area
		
		switch(rand) {
			case 0:
				setCurrentOpponent(new Spinda(getRandomNumberInRange(averageLevel, averageLevel + 10)));
				fight();
				break;
			case 1:
				setCurrentOpponent(new Tallow(getRandomNumberInRange(averageLevel, averageLevel + 5)));
				fight();
				break;
			case 2:
				setCurrentOpponent(new Bellsprout(getRandomNumberInRange(averageLevel, averageLevel + 6)));
				fight();
				break;
			case 3:
				setCurrentOpponent(new Eevee(getRandomNumberInRange(averageLevel, averageLevel + 4)));
				fight();
				break;
			case 4:
				setCurrentOpponent(new Pikachu(getRandomNumberInRange(averageLevel, averageLevel + 7)));
				fight();
				break;
			default:
				return;
		}
	}
	
	public void fight() { //open up fight panel (for wild battles only).
		view.getClip().stop();
		view.getClip().close();
		view.playMusic("WildPokemonBattle.wav");
		JOptionPane.showMessageDialog(view, "A wild " + currentOpponent.getName() + " has appeared! \n" +  
											player.getName() + " sent out " + player.getCurrentPokemon().getName() + "!");
		
		System.out.println("A wild " + currentOpponent.getName() + " has appeared!");
		System.out.println(player.getName() + " sent out " + player.getCurrentPokemon().getName() + "!");
		
		this.averageLevel = player.getCurrentPokemon().getLevel();
		currentOpponent.setXPValue();
		view.setUpBattlePanel();
		//TODO: open up BattlePanel tab (this should happen in the GamePanel) 
		//have this class send signal to GamePanel telling it to switch
		//switch back after done
		return;	
	}
	public void transportToPokeCenter(Player player) {
		gameEntities[playerCurrRow][playerCurrCol] = new OpenSpace();
		playerCurrRow = numRowsInGameWorld - 3;
		playerCurrCol = numColsInGameWorld - 1;
		gameEntities[playerCurrRow][playerCurrCol] = player;
		player.setLastPosition("OPEN_SPACE");
		view.repaint();
	}
	
	public void updatePlayer(Player player) {
		this.player = player;
	}
	public int getPlayerRow() {
		return playerCurrRow;
	}
	public int getPlayerCol() {
		return playerCurrCol;
	}
	public void registerView(GamePanel panel) {
		view = panel;
	}
	public GameEntity[][] getGameEntities() {
		return gameEntities;
	}
	public int getNumColsInGameWorld() {
		return numColsInGameWorld;
	}
	public int getNumRowsInGameWorld() {
		return numRowsInGameWorld;
	}
	public Player getPlayer() {
		return player;
	}
	public int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int)(Math.random() * range) + min;
	}
	public void setCurrentOpponent(Pokemon opponent) {
		this.currentOpponent = opponent;
	}
	public Pokemon getCurrentOpponent() {
		return this.currentOpponent;
	}
	public void simplyDelay(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static int getRandomNumberInRange(int min, int max) { //from stack exchange
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	public int getAverageLevel() {
		return averageLevel;
	}
	public void setAverageLevel(int avg) {
		averageLevel = avg;
	}
}