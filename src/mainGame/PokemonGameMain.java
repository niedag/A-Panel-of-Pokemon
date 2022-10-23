package mainGame;

import entityPackage.*;
import pokemonPack.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class PokemonGameMain extends JFrame {
	private JPanel mainPanel;
	private GamePanel gamePanel;
	private PokeArea pokeArea;
	private CardLayout cardLayout;
	private Player player;
	private Clip clip;
	
	
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	
	public static void main(String[] args) {
		PokemonGameMain game = new PokemonGameMain();
		
		game.setUpMainContainerPanel();
		
		game.setContentPane(game.mainPanel);
		game.setSize(WIDTH, HEIGHT);
		game.setLocation(100,100);
		game.setVisible(true);
		game.setResizable(false);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void setUpMainContainerPanel() {
		mainPanel = new JPanel();
		cardLayout = new CardLayout();
		mainPanel.setLayout(cardLayout);

		JPanel titleScreenPanel = setUpTitleScreen();
		JPanel introPanel = setUpIntroPanel();
		
		player = new Player();
		pokeArea = new PokeArea(player, this);
			
		mainPanel.add(titleScreenPanel, "TitleScreen");
		mainPanel.add(introPanel, "Intro Panel");

		cardLayout.show(mainPanel, "TitleScreen");
	}
	
	public JPanel setUpTitleScreen() {
		JPanel title = new JPanel();
		title.setLayout(new BorderLayout());
		
		JLabel gameText = createJLabelWithImageAndText("Pokescreen.jpg", "<html><br><br><br><br><br><br><br><br><br>Press Enter to Start", Color.WHITE, 30);
		
		title.add(gameText, BorderLayout.CENTER);
		JPanel titleScreenSouth = new JPanel();
		
		
		JTextField nameField = new JTextField(10);
		nameField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainPanel, "Intro Panel");
			}
		});
		
		titleScreenSouth.add(nameField);
		title.add(titleScreenSouth, BorderLayout.SOUTH);
		playMusic("TitleScreenDiamond2.wav");
		return title;
	}
	public JPanel setUpIntroPanel() {
		
		JPanel intro = new JPanel();
		intro.setLayout(new BorderLayout());
		JPanel choices = new JPanel();
		choices.setLayout(new GridLayout(0,3));
		intro.add(choices, BorderLayout.SOUTH);
		
		String textToDisplay = "<html><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br> Welcome to a Panel of Pokemon! <br> "
				+ "Catch monsters and add their stats to yours! <br>"
				+ "Train hard to beat the gym boss! Best of Luck! <br>";
		JLabel introText = createJLabelWithImageAndText("PokeIntro.jpg", textToDisplay, Color.BLACK, 18);
		intro.add(introText, BorderLayout.CENTER);
		
		JButton starter1 = new JButton("Torchic");
		JButton starter2 = new JButton("Treecko");
		JButton starter3 = new JButton("Mudkip");
		
		starter1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.addPokemonToParty(new Torchic());
				player.getCurrentPokemon().setLevel(5); //change level here
				player.getCurrentPokemon().calibratePlayerStats();
				showGamePanel();
			}
		});
		starter2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.addPokemonToParty(new Treecko());
				player.getCurrentPokemon().setLevel(5); //change level here
				player.getCurrentPokemon().calibratePlayerStats();

				showGamePanel();
			}
		});
		starter3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.addPokemonToParty(new Mudkip());
				player.getCurrentPokemon().setLevel(5); //change level here
				player.getCurrentPokemon().calibratePlayerStats();
				showGamePanel();
			}
		});
		
		choices.add(starter1, BorderLayout.SOUTH);
		choices.add(starter2, BorderLayout.SOUTH);
		choices.add(starter3, BorderLayout.SOUTH);
		
		return intro;
	}
	
	
	public JLabel createJLabelWithImageAndText(String fileName, String text, Color color, int fontSize) {
		JLabel label = null;  
		try {
			Image i = ImageIO.read(new File(fileName));
			label = new JLabel(new ImageIcon(i.getScaledInstance(PokemonGameMain.WIDTH, PokemonGameMain.HEIGHT, 0)));
		} catch (IOException e) {
			e.printStackTrace();
			label = new JLabel(text);
		}
		label.setFont(new Font("Courier", Font.BOLD, fontSize));
		label.setForeground(color);
		label.setText(text);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.CENTER);
		return label;
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
	
	public void showGamePanel() {
		gamePanel = new GamePanel(pokeArea);
		mainPanel.add(gamePanel, "Game Panel");
		this.cardLayout.show(getContentPane(), "Game Panel");
		clip.stop();
		gamePanel.playMusic("Route3.wav");
		gamePanel.requestFocusInWindow();
	}
	
	public void simplyDelay(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Clip getClip() {
		return clip;
	}
}
