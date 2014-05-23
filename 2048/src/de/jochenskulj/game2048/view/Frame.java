/*
 * Just another implementation of the 2048 game  
 *
 *  Copyright (C) 2014 Jochen Skulj <jochen@jochenskulj.de>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/
 */
package de.jochenskulj.game2048.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.jochenskulj.game2048.Application;
import de.jochenskulj.game2048.model.BoardModel;
import de.jochenskulj.game2048.model.BoardModelListener;

/**
 * Frame of the Application 
 */
public class Frame extends JFrame implements KeyListener {

	private TilePanel tilePanel;
	private ScorePanel scorePanel;
	private HashMap<Integer,Integer> keyMap;
	
	/**
	 * creates an instance
	 */
	public Frame() {
		initComponents();
		keyMap = initKeyMap();
		setIconImage(Application.getImage(10));
		setTitle("2048");
		setSize(new Dimension(570, 650));	
	}

	/**
	 * returns the board model
	 * @return the board model
	 */
	public BoardModel getModel() {
		BoardModel result = null;
		if (tilePanel != null) {
			result = tilePanel.getModel();
		}
		return result;
	}
	
	@Override
	public void keyPressed(KeyEvent anEvent) {
		int keyCode = anEvent.getKeyCode();
		if (keyMap.keySet().contains(keyCode)) {
			int direction = keyMap.get(keyCode);
			tilePanel.getModel().move(direction);
			if (tilePanel.getModel().isWon()) {
				JOptionPane.showMessageDialog(this, 
						"Yeah, you won the game!!!");
			}
			if (tilePanel.getModel().isLost()) {
				System.out.print("GAME OVER.");
				JOptionPane.showMessageDialog(this, 
						"GAME OVER! Sorry, you lost the game.");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent anEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent anEvent) {
				
	}

	
	/**
	 * initializes the components of the frame
	 */
	protected void initComponents() {
		tilePanel = new TilePanel();
		scorePanel = new ScorePanel(this, tilePanel.getModel());
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
	    c.gridx = 0;
	    c.gridy = 0;
	    c.weighty = 0;
	    c.weightx = 10;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.NORTHWEST;
	    c.insets = new Insets(5, 5, 5, 5);
		add(scorePanel, c);
		
		c = new GridBagConstraints();
	    c.gridx = 0;
	    c.gridy = 1;
	    c.weighty = 10;
	    c.weightx = 10;
	    c.fill = GridBagConstraints.BOTH;
	    c.anchor = GridBagConstraints.NORTHWEST;
	    c.insets = new Insets(5, 5, 5, 5);
		add(tilePanel, c);
		tilePanel.requestFocus();
		
		addKeyListener(this);
	}
	
	/**
	 * initializes the key map
	 * @return Hashmap that maps keycodes to moving directions
	 */
	protected HashMap<Integer,Integer> initKeyMap() {
		HashMap<Integer,Integer> result = new HashMap<Integer,Integer>();
		result.put(KeyEvent.VK_UP, BoardModel.UP);
		result.put(KeyEvent.VK_DOWN, BoardModel.DOWN);
		result.put(KeyEvent.VK_LEFT, BoardModel.LEFT);
		result.put(KeyEvent.VK_RIGHT, BoardModel.RIGHT);
		return result;
	}
}
