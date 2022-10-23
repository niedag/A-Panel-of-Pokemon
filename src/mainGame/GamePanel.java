package mainGame;

import javax.sound.sampled.*;
import javax.sound.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

import entityPackage.*;
import pokemonPack.MuscleTorchic;

public class GamePanel extends JPanel { //takes in player movement input, the battle screen, stuff that has to do with visuals
	
	private PokeArea pokeAreaData;
	private MovementListener moveListener;
	private CardLayout cardLayout;
	private BattlePanel battlePanel;
	private PokeAreaPanel pokePanel;
	
	private Clip clip; //Music file for PokeArea and the BattlePanel
	
	public GamePanel(PokeArea p) {
		pokeAreaData = p;
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		
		pokePanel = new PokeAreaPanel();	
		pokeAreaData.registerView(this);
		
		this.add(pokePanel, "PokeMap");		
		cardLayout.show(this, "PokeMap");
		
		moveListener = new MovementListener(pokeAreaData, this);
		
		this.addKeyListener(moveListener);
		this.addFocusListener(moveListener);
		this.addMouseListener(moveListener);
		
		moveListener.activate();
		
	} //end of constructor
	
	private class PokeAreaPanel extends JPanel {
		public void paintComponent(Graphics g) {
			int rows = pokeAreaData.getNumRowsInGameWorld();
        	int cols = pokeAreaData.getNumColsInGameWorld();
        	int widthOfEachSquare = PokemonGameMain.WIDTH / cols;	
        	int heightOfEachSquare = (PokemonGameMain.HEIGHT) / rows - 1; 
        	
        	GameEntity[][] entities = pokeAreaData.getGameEntities();
        	for(int i = 0; i < rows; i++) {
        		for(int a = 0; a < cols; a++) {
        			g.setColor(Color.WHITE);
        			g.fillRect(a*widthOfEachSquare, i*heightOfEachSquare, widthOfEachSquare, heightOfEachSquare);
	               	g.setColor(Color.BLACK);
	               	g.drawRect(a*widthOfEachSquare, i*heightOfEachSquare, widthOfEachSquare, heightOfEachSquare);
	               	g.drawImage(entities[i][a].getImage(), a*widthOfEachSquare, i*heightOfEachSquare, widthOfEachSquare, heightOfEachSquare, null);
        		}
        	}
		}
	}
	
	public void warningMessage() {
		if(pokeAreaData.getPlayer().getIsBossDefeated() == false) {
			String[] buttons = new String[] { "Yes", "No"};    
			int returnValue = JOptionPane.showOptionDialog(this, "You're about to fight the final boss?", "Warning!!!",
			        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
			if(returnValue == 0) {
				pokeAreaData.setCurrentOpponent(new MuscleTorchic());
				System.out.println("A wild " + pokeAreaData.getCurrentOpponent().getName() + " has appeared!");
				System.out.println(pokeAreaData.getPlayer().getName() + " sent out " + pokeAreaData.getPlayer().getCurrentPokemon().getName() + "!");
				this.setUpBossBattle();
			}
			else if(returnValue == 1) {
				return;
			}
		} else {
			JOptionPane.showMessageDialog(this, "You have already defeated the boss");
			return;
		}
	}
	
	public void setUpBattlePanel() {
		moveListener.deactivate();
		battlePanel = new BattlePanel(pokeAreaData, this);
		this.add(battlePanel, "Battle Panel");
		cardLayout.show(this, "Battle Panel");
		this.requestFocusInWindow();
	}
	
	public void setUpBossBattle() {
		clip.stop();
		clip.close();
		moveListener.deactivate();
		playMusic("PokeBossBattleMusic.wav");
		battlePanel = new BattlePanel(pokeAreaData, this);
		this.add(battlePanel, "Battle Panel");
		cardLayout.show(this, "Battle Panel");
		this.requestFocusInWindow();
	}
	
	public void showPokeArea() {
		clip.stop();
		clip.close();
		playMusic("Route3.wav");
		moveListener.activate();
		cardLayout.show(this, "PokeMap");
		this.requestFocusInWindow();
	}
	public CardLayout getCardLayout() {
		return cardLayout;
	}
	public BattlePanel getBattlePanel() {
		return battlePanel;
	}
	public void updatePlayer(Player player) {
		pokeAreaData.updatePlayer(player);
	}
	public void playMusic(String fileName) {
		try {
			File soundFile = new File(fileName); // Open an audio input stream from a wave File
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip(); // Get a sound clip resource.
			clip.open(audioIn); // Open audio clip and load samples from the audio input stream.
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} 
		catch (Exception e) { 
			e.printStackTrace();
		} 
	}
	public Clip getClip() {
		return clip;
	}
}
