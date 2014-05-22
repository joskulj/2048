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
package de.jochenskulj.game2048.model;

import java.util.ArrayList;
import java.util.HashMap;

import de.jochenskulj.game2048.Application;

/**
 * This class represents the model of the board 
 */
public class BoardModel {
	
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	private int score;
	private int size;
	private int[][] values;
	private ArrayList<BoardModelListener> listeners;
	private HashMap<Integer, BoardPosition> startPositions;
	
	/**
	 * creates an instance
	 */
	public BoardModel() {
		size = Application.MAX_TILES;
		values = new int [size][size];
		listeners = new ArrayList<BoardModelListener>();
		startPositions = initsStartPositions();
	}

	/**
	 * adds a listener
	 * @param aListener
	 *        listener to add
	 */
	public void addListener(BoardModelListener aListener) {
		listeners.add(aListener);
	}
	
	/**
	 * returns the value of a given position
	 * @param x
	 *        X-Position
	 * @param y
	 *        Y-Position
	 * @return value at that position
	 */
	public int getValue(int x, int y) {
		return values[x][y];
	}
	
	/**
	 * returns the current score
	 * @return current score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * checks if the game is won
	 * @return <code>true</code> if the game is won; otherwise
	 *         <code>false</code>
	 */
	public boolean isWon() {
		return false;
	}

	/**
	 * checks if the game is lost
	 * @return <code>true</code> if the game is lost; otherwise
	 *         <code>false</code>
	 */
	public boolean isLost() {
		boolean emptyTile = false;
		int possibleMoves = 0;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (values[x][y] == 0) {
					emptyTile = true;
				}
			}
			possibleMoves += moveRow(LEFT, x, false);
			possibleMoves += moveRow(DOWN, x, false);
		}
		return (possibleMoves == 0 && emptyTile == false);
	}
	
	/**
	 * returns the positions of free fields
	 * @return ArrayList of free positions
	 */
	public ArrayList<BoardPosition> getFreePositions() {
		ArrayList<BoardPosition> result = new ArrayList<BoardPosition>();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int value = values[x][y];
				if (value == 0) {
					BoardPosition position = new BoardPosition(x, y);
					result.add(position);
				}
			}
		}
		return result;
	}
	
	/**
	 * sets a new tile at a random position
	 */
	public void newTile() {
		ArrayList<BoardPosition> freePosition = getFreePositions();
		int randomIndex = (int) (Math.random() * freePosition.size());
		BoardPosition randomPosition = freePosition.get(randomIndex);
		int newValue = (Math.random() > 0.5) ? 2 : 1;
		randomPosition.setValue(values, newValue);
		fireUpdate();
	}
	
	/**
	 * makes a move
	 * @param aDirection
	 *        direction to move
	 */
	public void move(int aDirection) {
		int count = 0;
		for (int i = 0; i < Application.MAX_TILES; i++) {
			count += moveRow(aDirection, i, true);
		}
		if (count > 0) {
			newTile();
			fireUpdate();
		}
	}
	
	/**
	 * initializes the start positions for different directions
	 * @return Hashmap that maps the directions to start positions
	 */
	protected HashMap<Integer, BoardPosition> initsStartPositions() {
		HashMap<Integer, BoardPosition> result = new HashMap<Integer, BoardPosition>();
		BoardPosition position = new BoardPosition(0, 0, 0, 1);
		result.put(UP, position);
		position = new BoardPosition(0, Application.MAX_TILES - 1, 0, -1);
		result.put(DOWN, position);
		position = new BoardPosition(0, 0, 1, 0);
		result.put(LEFT, position);
		position = new BoardPosition(Application.MAX_TILES -1, 0, -1, 0);
		result.put(RIGHT, position);
		return result;
	}
	/**
	 * signals changes of the models to the listeners
	 */
	protected void fireUpdate() {
		for (BoardModelListener listener : listeners) {
			listener.onUpdate();
		}
	}
	
	/**
	 * moves a single row
	 * @param aDirection
	 *        direction to move
	 * @param aRowIndex
	 *        index of the row to moves
	 * @param moveFlag
	 *        indicates, if the tiles should actually move
	 * @return count of the tile moves
	 */
	protected int moveRow(int aDirection, int aRowIndex, boolean moveFlag) {
		int result = 0;
		BoardPosition current = startPositions.get(aDirection).cloneInstance();
		current.setRow(aRowIndex);
		while (current.isValid()) {
			boolean nextTile = true;
			int currentValue = current.getValue(values);
			BoardPosition next = current.cloneInstance();
			next.nextTile();
			next.nextValueTile(values);
			int nextValue = next.getValue(values);
			if (currentValue == 0) {
				if (nextValue > 0) {
					if (moveFlag) {
						current.setValue(values, nextValue);
						next.setValue(values, 0);
						nextTile = false;
					}	
					result += 1;
				}
			} else {
				if (currentValue == nextValue) {
					int newValue = currentValue + 1;
					if (moveFlag) {
						current.setValue(values, newValue);
						next.setValue(values, 0);
						score += Math.pow(2, newValue);
					}
					result += 1;
				}
			}
			if (nextTile == true) {
				current.nextTile();
			}
		}
		return result;
	}
}
