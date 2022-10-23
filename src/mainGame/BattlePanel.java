package mainGame;

import entityPackage.*;
import pokemonPack.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;


public class BattlePanel extends JPanel {
	
	//for the data and the graphics for the Battle panel
	//the rest of the stuff
	private GamePanel gamePanel;
	private Image battleBackground;
	private Image playerPokeImage, opponentImage;
	private Player player;
	private ArrayList<Pokemon> playerParty;
	private PokeArea pokeArea;
	private BattlePanelListener battleListener;
	
	private CardLayout cardLayout; //to switch between panels on player's turn
	
	//pokemons data
	private Pokemon playerPoke, opponent;
	private int playerXPosition, playerYPosition;
	private int opponentXPosition, opponentYPosition;
	
	private boolean playerTurn;
	
	//Labels
	public JLabel playerPokeName, playerPokeTotalHealth, playerCurrentHealth, playerPokeLv;
	//made public to allow Labels to change dynamically
	
	private JLabel opponentPokeName, opponentPokeLv;

	//buttons
	private JButton FIGHT = new JButton("FIGHT");
	private JButton CATCH = new JButton("CATCH");
	private JButton RUN = new JButton("RUN");
	private JButton PKMN = new JButton("PKMN");
	
	//class is for the buttons on the battle screen
	public BattlePanel(PokeArea pokeArea, GamePanel gamePanel) {
		this.pokeArea = pokeArea;
		this.gamePanel = gamePanel;
		
		this.player = pokeArea.getPlayer();
		playerParty = player.getPlayerParty();
		this.playerPoke = pokeArea.getPlayer().getCurrentPokemon();
		this.opponent = pokeArea.getCurrentOpponent();
		
		battleListener = new BattlePanelListener(this);
		this.setLayout(null);
		
		battleBackground = new ImageIcon("BattleBackground.jpg").getImage();
	
		//opponentImage = new ImageIcon(opponent.getImageURL()).getImage();
		opponentImage = opponent.getImage();
		opponentXPosition = opponent.getXPosition();
		opponentYPosition = opponent.getYPosition();

		playerPokeImage = playerPoke.getImage();
		playerXPosition = playerPoke.getXPosition();
		playerYPosition = playerPoke.getYPosition();
		setUpBattleLabels();
		setUpBattleButtons();
		
		
		FIGHT.addActionListener(battleListener);
		CATCH.addActionListener(battleListener);
		RUN.addActionListener(battleListener);
		PKMN.addActionListener(battleListener);
		//this.requestFocusInWindow();
	}

	
	@Override
	public void paintComponent(Graphics g) { //change the screen dynamically
		super.paintComponent(g); //makes the background gray 
		g.drawImage(battleBackground, 100, 100, this);
		g.drawImage(opponentImage, opponentXPosition, opponentYPosition, this);
		g.drawImage(playerPokeImage, playerXPosition, playerYPosition, this);	
	}
	
	
	public void opponentTurn() {
		dealDamage(opponent.getAttack(), playerPoke);
	}
		
	public void showGameOverPanel() {
		JOptionPane.showMessageDialog(this, player.getName() + " has no usable Pokemon left. \n"
				+ player.getName() + " was sent to the Pokemon Center");
		pokeArea.transportToPokeCenter(player);
		gamePanel.showPokeArea();
	}
	public void showEndBattle() {
		if(opponent.getName().equals("Muscle-chic")) {
			//System.out.println("You Win!");
			JOptionPane.showMessageDialog(this, "You defeated Muscle-chic! \n "
					+ "You Win!");
			//player.setIsBossDefeated(true);
			pokeArea.transportToPokeCenter(player);
			gamePanel.showPokeArea();
		}
		getPlayerPokemon().addXP(getOpponent().getXPValue());
		//System.out.println("opponent value: " + getOpponent().getXPValue());
		//System.out.println("playerPoke's XP: " + playerPoke.getXP());
		//System.out.println("poke's xp to next level: " + playerPoke.getXPToNextLevel());
		if(playerPoke.getXP() >= playerPoke.getXPToNextLevel()) {
			String stats = getPlayerPokemon().levelUp();
			JOptionPane.showMessageDialog(this, player.getCurrentPokemon().getName() + " leveled up!!! \n"
					+ player.getCurrentPokemon().getName() + " is now level " + player.getCurrentPokemon().getLevel() + "!!! \n"
					+ player.getCurrentPokemon().getName() + stats);
		} else {
			JOptionPane.showMessageDialog(this, player.getCurrentPokemon().getName() + " gained " + opponent.getXPValue() + " XP!! \n"
					+ player.getCurrentPokemon().getXP() + "/" + player.getCurrentPokemon().getXPToNextLevel() + "XP"); 
		}
		gamePanel.showPokeArea();
	}
	
	
	public void endBattleWithCatch() {
		System.out.println("Player caught " + opponent.getName());
		player.addPokemonToParty(opponent);
		player.getCurrentPokemon().addOpponentStats((int)(opponent.getLevel()/2), (int)(opponent.getBaseHP()/2), (int)(opponent.getAttack()), (int)(opponent.getSpAtk()/2));
		
		JOptionPane.showMessageDialog(this, player.getCurrentPokemon().getName() + " gained: \n" 
		+ "+" + (int)(opponent.getLevel()/2) + "LV \n"
		+ "+" + (int)(opponent.getBaseHP()/2) + "HP \n" 
		+ "+" + (int)(opponent.getAttack()) + "ATK \n"
		+ "+" + (int)(opponent.getSpAtk()) + "Sp ATK");
		
		pokeArea.setAverageLevel(player.getCurrentPokemon().getLevel());
		gamePanel.showPokeArea();
	}
	
	public void endBattle() {
		pokeArea.setAverageLevel(player.getCurrentPokemon().getLevel());
		gamePanel.showPokeArea();
	}
	
//	public boolean checkPlayerPartyForFaintedPokemon() { //only used when player runs out of usable pokemon
//		int numFaintedPokemon = 0;
//		for(int i = 0; i < playerParty.size(); i++) {
//			if(playerParty.get(i).getHp() == 0) {
//				numFaintedPokemon++;
//			}
//		}
//		if(numFaintedPokemon == playerParty.size()) return true;
//		else return false;
//	}
	
	public void setUpBattleLabels() { //includes HP, Moves, Pokemon Names/LVs
		playerPokeName = new JLabel(playerPoke.getName());
		playerPokeTotalHealth = new JLabel("" + playerPoke.getBaseHP());
		playerCurrentHealth = new JLabel("" + playerPoke.getHp());
		playerPokeLv = new JLabel("" + playerPoke.getLevel());

		opponentPokeName = new JLabel(opponent.getName());
		opponentPokeLv = new JLabel("" + opponent.getLevel());
		
		
		this.add(playerPokeName);
		this.add(playerPokeTotalHealth);		
		this.add(playerPokeLv);
		this.add(playerCurrentHealth);
		this.add(opponentPokeName);
		this.add(opponentPokeLv);
		
		
		playerPokeName.setBounds(486, 276, 300, 35);
		playerPokeLv.setBounds(591, 283, 100, 20);
		playerPokeTotalHealth.setBounds(586, 320, 100, 20);
		playerCurrentHealth.setBounds(554, 320, 100, 20);
		
		opponentPokeName.setBounds(205, 140, 300, 35);
		opponentPokeLv.setBounds(308, 151, 100, 20);	
	}
	
	
	public void setUpBattleButtons() { //change into CardLayout
		cardLayout = new CardLayout(); //cardlayout for changing between move panel and items and player options 
		JPanel playerPanel = new JPanel();
		
		//All the panels in playerPanel
		JPanel buttonPanel = new JPanel();
		JPanel movePanel = new JPanel();
		JPanel itemsPanel = new JPanel();
		JPanel pokePartyPanel = new JPanel();

		playerPanel.setLayout(cardLayout);
		
		playerPanel.add(movePanel, "MovePanel");
		playerPanel.add(itemsPanel, "ItemsPanel");
		playerPanel.add(pokePartyPanel, "PokePartyPanel");
		
		buttonPanel.setPreferredSize(new Dimension(350, 250));
		
		
		//Initial Panel
		buttonPanel.add(FIGHT);
		buttonPanel.add(PKMN);
		buttonPanel.add(CATCH);
		buttonPanel.add(RUN);
		
		this.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(2,2));
		buttonPanel.setBounds(438, 370, 200, 91);
		
		this.add(playerPanel); 
		
		cardLayout.show(playerPanel, "ButtonPanel");
		System.out.println("Battle Button method complete");
	}
	
	public void simplyDelay(int miliseconds) { //from stackover flow
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public PokeArea getPokeArea() {
		return pokeArea;
	}
	public Pokemon getPlayerPokemon() {
		return playerPoke;
	}
	public Pokemon getOpponent() {
		return opponent;
	}
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	public Player getPlayer() {
		return player;
	}
	//temp
	
	public void dealDamage(int dealtDamage, Pokemon opponent) { //call *printBattleText here
		int randomNum = getRandomNumberInRange(1, 50);
		if(randomNum == 5) {
			dealtDamage = dealtDamage *2;
			opponent.subtractHP(dealtDamage * 2);
			JOptionPane.showMessageDialog(this, "A Critical Hit!");
		} else {
			opponent.subtractHP(dealtDamage);	
		}
		JOptionPane.showMessageDialog(this, opponent.getName() + " took " + dealtDamage + " damage!");
		System.out.println(opponent.getName() + " took " + dealtDamage + " damage!");
	}
	
	public static int getRandomNumberInRange(int min, int max) { //from stack exchange
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public BattlePanelListener getBattleListener() {
		return battleListener;
	}
}
