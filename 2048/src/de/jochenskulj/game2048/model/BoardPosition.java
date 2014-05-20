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

import de.jochenskulj.game2048.Application;

/**
 * This class represents a position on the board 
 */
public class BoardPosition {
	
	private int xPosition;
	private int yPosition;
	private int xDelta;
	private int yDelta;
	
	/**
	 * creates an instance
	 */
	public BoardPosition() {
		
	}
	
	/**
	 * creates an instance
	 * @param x
	 *        the X-Position
	 * @param y
	 *        the Y-Position
	 */
	public BoardPosition(int x, int y) {
		xPosition = x;
		yPosition = y;
	}

	/**
	 * creates an instance
	 * @param x
	 *        the X-Position
	 * @param y
	 *        the Y-Position
	 * @param xd
	 *        step in X-Direction
	 * @param yd
	 *        step in Y-Direction
	 */
	public BoardPosition(int x, int y, int xd, int yd) {
		xPosition = x;
		yPosition = y;
		xDelta = xd;
		yDelta = yd;
	}

	
	/**
	 * creates a copy of the instance
	 * @return copy of the instance with the same values
	 */
	public BoardPosition cloneInstance() {
		BoardPosition result = new BoardPosition();
		result.xPosition = xPosition;
		result.yPosition = yPosition;
		result.xDelta = xDelta;
		result.yDelta = yDelta;
		return result;
	}
	
	/**
	 * sets the position values from another Board Position
	 * @param aPosition
	 *        position to set
	 */
	public void setPosition(BoardPosition aPosition) {
		xPosition = aPosition.getX();
		yPosition = aPosition.getY();
	}

	/**
	 * sets the row
	 * @param aRowIndex
	 *        row index to save
	 */
	public void setRow(int aRowIndex) {
		if (xDelta != 0 && yDelta == 0) {
			yPosition = aRowIndex;
		}
		if (xDelta == 0 && yDelta != 0) {
			xPosition = aRowIndex;
		}

	}
	
	/**
	 * returns the X-Position
	 * @return X-Position
	 */
	public int getX() {
		return xPosition;
	}

	/**
	 * returns the y-Position
	 * @return Y-Position
	 */	
	public int getY() {
		return yPosition;
	}
	
	/**
	 * checks if the position is valid
	 * @return <code>true</code>, if the position is valid; otherwise
	 *         <code>false</code>
	 */
	public boolean isValid() {
		boolean result = true;
		if (xPosition < 0 || xPosition >= Application.MAX_TILES) {
			result = false;
		}
		if (yPosition < 0 || yPosition >= Application.MAX_TILES) {
			result = false;
		}
		return result;
	}
	
	/**
	 * moves to the next tile
	 */
	public void nextTile() {
		xPosition += xDelta;
		yPosition += yDelta;
	}
	
	/**
	 * moves to the next tile
	 */
	public void previousTile() {
		xPosition -= xDelta;
		yPosition -= yDelta;
	}

	/**
	 * moves to the first position of the next row
	 */
	public void nextRow() {
		if (xDelta != 0 && yDelta == 0) {
			xPosition = 0;
			yPosition++;
		}
		if (xDelta == 0 && yDelta != 0) {
			xPosition++;
			yPosition = 0;
		}
	}
	
	public void nextValueTile(int[][] aBoard) {
		while (isValid()) {
			if (getValue(aBoard) != 0) {
				break;
			}
			nextTile();
		}
	}
	
	/**
	 * returns the value at the position
	 * @param aBoard
	 *        board
	 * @return value at the position
	 */
	public int getValue(int[][] aBoard) {
		int result = -1;
		if (isValid()) {
			result = aBoard[xPosition][yPosition];
		}
		return result;
	}
	
	/**
	 * sets the value at the position
	 * @param aBoard
	 *        board
	 * @param aValue
	 *        value to set
	 */
	public void setValue(int[][] aBoard, int aValue) {
		if (isValid()) {
			aBoard[xPosition][yPosition] = aValue;
		}
	}
}
