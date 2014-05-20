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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.jochenskulj.game2048.Application;

/**
 * This class represents a single tile of the board
 *
 */
public class Tile extends JPanel {

	private int value= 0;
	private BufferedImage image = Application.getImage(0);
	
	/**
	 * creates an instance
	 */
	public Tile() {
		Dimension sizeDimension = new Dimension(128, 128);
		setSize(200, 200);
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		setValue(0);
	}
	
	/**
	 * sets the value of the tile
	 * @param aValue
	 */
	public void setValue(int aValue) {
		value = aValue;
		image = Application.getImage(aValue);
		repaint();
	}
	
	/**
	 * paints the component
	 * @param g
	 *        graphics to paint the component on
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}