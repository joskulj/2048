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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import de.jochenskulj.game2048.model.BoardModel;

/**
 * Button to start a new Game
 */
public class NewButton extends JButton implements ActionListener {
	
	private Frame frame;
	private BoardModel model;

	/**
	 * creates a new instance
	 * @param aFrame
	 *        parent that handles the key events
	 * @param aModel
	 *        model to use
	 */
	public NewButton(Frame aFrame, BoardModel aModel) {
		frame = aFrame;
		model = aModel;
		setText("New Game");
		addKeyListener(frame);
		addActionListener(this);
	}
	
	@Override
    public void actionPerformed(ActionEvent arg0) {
        model.newGame();
    }
}
