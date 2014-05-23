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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.jochenskulj.game2048.model.BoardModel;
import de.jochenskulj.game2048.model.BoardModelListener;

/**
 * Panel to display the score
 */
public class ScorePanel extends JPanel implements BoardModelListener {

	private Frame frame;
	private BoardModel model;
	private JLabel scoreLabel;
	private NewButton newButton;
	private ExitButton exitButton;
	
	/**
	 * creates an instance
	 * @param aFrame
	 *        the parent frame
	 * @param aModel
	 *        model of the game
	 * 
	 */
	public ScorePanel(Frame aFrame, BoardModel aModel) {
		frame = aFrame;
		model = aModel;
		model.addListener(this);
		initComponents();
	}
	
	/**
	 * is called if the BoardModel changes
	 */
	public void onUpdate() {
		int scoreValue = model.getScore();
		scoreLabel.setText(String.valueOf(scoreValue));
	}

	/**
	 * initializes the components
	 */
	protected void initComponents() {
		newButton = new NewButton(frame, model);
		exitButton = new ExitButton(frame);
		scoreLabel = new JLabel();
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
	    c.gridx = 0;
	    c.gridy = 0;
	    c.weighty = 10;
	    c.weightx = 5;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.NORTHWEST;
	    c.insets = new Insets(5, 5, 5, 5);
		add(newButton, c);
		
		c = new GridBagConstraints();
	    c.gridx = 1;
	    c.gridy = 0;
	    c.weighty = 10;
	    c.weightx = 30;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.NORTHWEST;
	    c.insets = new Insets(5, 5, 5, 5);
		add(scoreLabel, c);
		
		c = new GridBagConstraints();
	    c.gridx = 2;
	    c.gridy = 0;
	    c.weighty = 10;
	    c.weightx = 5;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.NORTHWEST;
	    c.insets = new Insets(5, 5, 5, 5);
		add(exitButton, c);
	}
}
