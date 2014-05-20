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

import javax.swing.JPanel;

import de.jochenskulj.game2048.Application;
import de.jochenskulj.game2048.model.BoardModel;
import de.jochenskulj.game2048.model.BoardModelListener;

/**
 * This class represents the board of tiles 
 */
public class TilePanel extends JPanel implements BoardModelListener {

	private Tile[][] tiles = 
			new Tile[Application.MAX_TILES][Application.MAX_TILES];
	private BoardModel model;
	
	/**
	 * creates an instance
	 */
	public TilePanel() {
		model = new BoardModel();
		model.addListener(this);
		initTiles();
		initLayout();
	}	
	/**
	 * returns the model of the board
	 * @return model of the board
	 */
	public BoardModel getModel() {
		return model;
	}
	
	
	/**
	 * is called if the BoardModel changes
	 */
	public void onUpdate() {
		for (int x = 0; x < Application.MAX_TILES; x++) {
			for (int y = 0; y < Application.MAX_TILES; y++) {
				int value = model.getValue(x, y);
				Tile tile = tiles[x][y];
				tile.setValue(value);
			}
		}
		repaint();
	}
	
	/**
	 * creetes the components
	 */
	protected void initTiles() {
		for (int x = 0; x < Application.MAX_TILES; x++) {
			for (int y = 0; y < Application.MAX_TILES; y++) {
				tiles[x][y] = new Tile();
			}
		}
	}

	/**
	 * adds a tile to the layout
	 * @param x
	 *        X-Position of the tile
	 * @param y
	 *        Y-Position of the tile
	 * @param aTile
	 *        tile to add
	 */
	protected void addTile(int x, int y, Tile aTile) {
		GridBagConstraints c = new GridBagConstraints();
	    c.gridx = x;
	    c.gridy = y;
	    c.weighty = 10;
	    c.weightx = 10;
	    c.fill = GridBagConstraints.BOTH;
	    c.anchor = GridBagConstraints.NORTHWEST;
	    c.insets = new Insets(5, 5, 5, 5);
	    add(aTile, c);
	}
	
	/**
	 * inits the layout of the panel
	 */
	protected void initLayout() {
		setLayout(new GridBagLayout());
		for (int x = 0; x < Application.MAX_TILES; x++) {
			for (int y = 0; y < Application.MAX_TILES; y++) {
				addTile(x, y, tiles[x][y]);
			}
		}
	}
}
