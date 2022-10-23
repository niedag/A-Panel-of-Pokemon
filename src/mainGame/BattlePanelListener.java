package mainGame;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import entityPackage.*;
import pokemonPack.*;

public class BattlePanelListener implements KeyListener, MouseListener, ActionListener {

	private BattlePanel battlePanel; //also has battle information as well
	private boolean isActive;
	
	public BattlePanelListener(BattlePanel battlePanel) {
		this.battlePanel = battlePanel;
		isActive = true; //all listeners active	
	}
	
	public void activate() {
		isActive = true;
		//System.out.println("Activated BattleListener");
	}
	public void deactivate() {
		isActive = false;
		//System.out.println("Deactivated");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evt) { //all things checked for in the listener cause battles change dynamically
		String action = evt.getActionCommand();
		if(isActive == true) { 
			if(action.equals("FIGHT")) {
				battlePanel.dealDamage(battlePanel.getPlayerPokemon().getAttack(), battlePanel.getOpponent());
				System.out.println("Opponent HP: " + battlePanel.getOpponent().getHp());
				if(battlePanel.getOpponent().getHp() <= 0) {
					deactivate();
					JOptionPane.showMessageDialog(battlePanel, battlePanel.getOpponent().getName() + " has fainted!");
					battlePanel.showEndBattle();
				} else {
					deactivate();
					battlePanel.opponentTurn();
					battlePanel.playerCurrentHealth.setText("" + battlePanel.getPlayerPokemon().getHp());
					battlePanel.repaint();
					if(battlePanel.getPlayerPokemon().getHp() <= 0) {
						deactivate();
						JOptionPane.showMessageDialog(battlePanel, battlePanel.getPlayerPokemon().getName() + " has fainted");
						battlePanel.showGameOverPanel();
					} else {
						activate();
					}
				}
			} else if(action.equals("RUN")) {
				deactivate();
				int num = battlePanel.getRandomNumberInRange(1, 2);
				if(battlePanel.getOpponent().getName().equals("Muscle-chic")) {
					JOptionPane.showMessageDialog(battlePanel, "You can't run from a Gym Battle!");
					battlePanel.opponentTurn();
					battlePanel.playerCurrentHealth.setText("" + battlePanel.getPlayerPokemon().getHp());
					battlePanel.repaint();
					if(battlePanel.getPlayerPokemon().getHp() <= 0) {
						deactivate();
						System.out.println(battlePanel.getPlayerPokemon().getName() + " has fainted");
						battlePanel.showGameOverPanel();
					activate();
					
					}
				}
				if(battlePanel.getPlayerPokemon().getLevel() < battlePanel.getOpponent().getLevel()) {
					if(num == 1) {
						JOptionPane.showMessageDialog(battlePanel, battlePanel.getPlayer().getName() + " escaped successfully");
						battlePanel.endBattle();
					} else {
						JOptionPane.showMessageDialog(battlePanel, battlePanel.getPlayer().getName() + " couldn't get away!");
						battlePanel.opponentTurn();
						battlePanel.playerCurrentHealth.setText("" + battlePanel.getPlayerPokemon().getHp());
						battlePanel.repaint();
						if(battlePanel.getPlayerPokemon().getHp() <= 0) {
							deactivate();
							JOptionPane.showMessageDialog(battlePanel, battlePanel.getOpponent().getName() + " has fainted");
							battlePanel.showGameOverPanel();
						} else {
							activate();
						}
					}
				} else { //if the player is higher level, immediate escape		
					battlePanel.endBattle();
				}
			} else if(action.equals("PKMN")) { //Doesn't work yet
				JOptionPane.showMessageDialog(battlePanel, "You have " + battlePanel.getPokeArea().getPlayer().getPlayerParty().size() + " Pokemon out of " + battlePanel.getPokeArea().getPlayer().getMaxPartySize() + " Pokemon in your party");
				return;
			} else if (action.equals("CATCH")) {
				if(battlePanel.getOpponent().getName().equals("Muscle-chic")) {
					JOptionPane.showMessageDialog(battlePanel, "You can't catch this Pokemon");
					return;
				}
				if(battlePanel.getPokeArea().getPlayer().getPlayerParty().size() >= battlePanel.getPokeArea().getPlayer().getMaxPartySize()) {
					JOptionPane.showMessageDialog(battlePanel, "Player party full, cannot add");
					return;
				} else {
					deactivate();
					battlePanel.endBattleWithCatch();
				}
			}
		}
		battlePanel.repaint();
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	public boolean getActivity() {
		return isActive;
	}	
}
