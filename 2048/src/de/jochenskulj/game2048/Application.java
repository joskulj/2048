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
package de.jochenskulj.game2048;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.jochenskulj.game2048.view.Frame;

/**
 * A Java implementation of the 2048 game
 */
public class Application {
	
	public final static int MAX_TILES = 4;
	public final static int MAX_VALUE = 12;
	
	private Frame frame;
	
	/**
	 * Icon names that are used for the tiles
	 */
	public final static String[] ICON_NAME = {
		"0.jpg", "2.jpg", "4.jpg", "8.jpg", "16.jpg", "32.jpg",
		"64.jpg", "128.jpg", "256.jpg", "512.jpg", "1024.jpg", "2048.jpg"
	};
	
	public void run() {
		frame = new Frame();
		frame.setVisible(true);
		frame.getModel().newTile();
		frame.getModel().newTile();
	}
	
	/**
	 * loads a tile image for a given value
	 * @param aValue
	 *        value of a tile
	 * @return image to display
	 */
	public static BufferedImage getImage(int aValue) {
		BufferedImage result = null;
		try {
			StringBuffer filepathBuffer = new StringBuffer();
			filepathBuffer.append(System.getProperty("user.dir"));
			filepathBuffer.append("/pictures/");
			filepathBuffer.append(ICON_NAME[aValue]);
			result = ImageIO.read(new File(filepathBuffer.toString()));
		} catch(IOException e) {
			System.err.println("Unable to load tile image.");
		}
		return result;
	}

	/**
	 * main method to start the application
	 * @param args
	 *        command line arguments (not used)
	 */
	public static void main(String[] args) {
		Application application = new Application();
		application.run();
	}

}
