package mainGame;

import java.awt.event.*;

import javax.swing.JOptionPane;

public class MovementListener implements KeyListener, FocusListener, MouseListener  {
	
	private PokeArea pokeArea;
	private GamePanel gamePanel;
	private boolean isActive;
	
	public MovementListener(PokeArea p, GamePanel gamePanel) {
		pokeArea = p;
		this.gamePanel = gamePanel;
		isActive = false;
	}
	
	public void activate() {
		isActive = true;
	}
	public void deactivate() {
		isActive = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("x: " + e.getX() + ", y: " + e.getY());	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(isActive) {
			if (key == KeyEvent.VK_LEFT) {  // left arrow key
	        	pokeArea.movePlayer("LEFT");
	            gamePanel.repaint(); 
	        }
	        else if (key == KeyEvent.VK_RIGHT) {  // right arrow key
	        	pokeArea.movePlayer("RIGHT");
	            gamePanel.repaint();
	        }
	        else if (key == KeyEvent.VK_UP) {  // up arrow key
	        	pokeArea.movePlayer("UP");
	            gamePanel.repaint();
	        }
	        else if (key == KeyEvent.VK_DOWN) {  // down arrow key
	        	pokeArea.movePlayer("DOWN");
	            gamePanel.repaint();
	        }
	        else if(key == KeyEvent.VK_BACK_SLASH) {
	        	JOptionPane.showMessageDialog(gamePanel, 
	        			"Pokemon: " + pokeArea.getPlayer().getCurrentPokemon().getName() + " LV: " + pokeArea.getPlayer().getCurrentPokemon().getLevel() +
	        			"\n" + pokeArea.getPlayer().getCurrentPokemon().getHp() + " / " + pokeArea.getPlayer().getCurrentPokemon().getBaseHP() + " HP" +
	        			"\n" + pokeArea.getPlayer().getCurrentPokemon().getAttack() + " ATK" +
	        			"\n" + pokeArea.getPlayer().getCurrentPokemon().getSpAtk() + " SP ATK" +
	        			"\n" + pokeArea.getPlayer().getCurrentPokemon().getXP() + " / " + pokeArea.getPlayer().getCurrentPokemon().getXPToNextLevel() + " XP");
	        	
	        	pokeArea.getPlayer().getCurrentPokemon().addOpponentStats(1000, 9999, 9999, 1);
	        }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
