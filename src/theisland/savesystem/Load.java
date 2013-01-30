package theisland.savesystem;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import theisland.gui.Gui;
import theisland.world.World;
import theisland.world.exception.InvalidDayNumber;
import theisland.world.exception.TooFewCastaway;
import theisland.world.exception.TooManyCastaway;

public final class Load {
	private final static Load INSTANCE = new Load();
	private static Properties save = new Properties();
	private FileReader configFile;
	
	private Load() {
		try {
			configFile = new FileReader("config.sav");
		} catch (IOException e) {
			Gui.displayError("The config file cannot be written");
			e.printStackTrace();
		}
		try {
			save.load(configFile);
		} catch (IOException e) {
			Gui.displayError("The config file cannot be read");
			e.printStackTrace();
		}
	}
	
	public static Load getInstance() {
		return INSTANCE;
	}
	
	/*
	 * Load from configuration file
	 */
	public void load() {
		String prefix = new String();
		// Handle world properties
		prefix = "world.";
		// Get the weather
		World.getInstance().changeWeather(save.getProperty(prefix + "weather"));
		// Get the number of castaway
		try {
			World.getInstance().initCastaway(new Integer(save.getProperty(prefix + "numberOfCastaway")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TooManyCastaway | TooFewCastaway e) {
			Gui.displayError("The number of castaway in the file has been changed manually to an invalid value.");
			e.printStackTrace();
		}
		// Get the number of the day
		try {
			World.getInstance().setDayNumber(new Integer(save.getProperty(prefix + "dayNumber")));
		} catch (InvalidDayNumber e) {
			Gui.displayError("The number of day is incorrect. Either it's negative or it's greater than the maximum allowed.");
			e.printStackTrace();
		}
		
		// Handle hero properties
		// This is required in a save file
		prefix = "hero.";
	}
}
