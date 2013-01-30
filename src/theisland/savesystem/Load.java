package theisland.savesystem;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import theisland.castaway.Castaway;
import theisland.castaway.exception.AffinityOutOfRange;
import theisland.castaway.exception.EnergyOutOfRange;
import theisland.castaway.exception.HealthOutOfRange;
import theisland.castaway.exception.MoralOutOfRange;
import theisland.castaway.exception.NameOutOfRange;
import theisland.castaway.exception.StressOutOfRange;
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
	}
	
	public static Load getInstance() {
		return INSTANCE;
	}
	
	/*
	 * Load from configuration file
	 */
	public void load() {
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
		
		String prefix = new String();
		// Handle world properties
		prefix = "world.";
		
		// TODO: Add security to loaded values (do they exist?)
		
		// Get the weather
		World.getInstance().changeWeather(save.getProperty(prefix + "weather"));
		
		// Get the number of castaway
		int numberOfCastaway = new Integer(save.getProperty(prefix + "numberOfCastaway"));
		/*try {
			World.getInstance().initCastaway(new Integer(save.getProperty(prefix + "numberOfCastaway")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TooManyCastaway | TooFewCastaway e) {
			Gui.displayError("The number of castaway in the file has been changed manually to an invalid value.");
			e.printStackTrace();
		}*/
		
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
		Castaway hero = new Castaway(true);
		try {
			hero.setName(save.getProperty(prefix + "name"));
		} catch (NameOutOfRange e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			hero.setHealth(new Integer(save.getProperty(prefix + "health")));
		} catch (HealthOutOfRange e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			hero.setEnergy(new Integer(save.getProperty(prefix + "energy")));
		} catch (EnergyOutOfRange e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			hero.setMoral(new Integer(save.getProperty(prefix + "moral")));
		} catch (MoralOutOfRange e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			hero.setAffinity(new Integer(save.getProperty(prefix + "affinity")));
		} catch (AffinityOutOfRange e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			hero.setStress(new Integer(save.getProperty(prefix + "stress")));
		} catch (StressOutOfRange e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO: Inventory handling, see what separe each item [item;item] [item,item] [item:item] ??
		// -- Inventory
		//ArrayList<Item> inventory = new ArrayList<Item>();
		
		//hero.setInventory((new ArrayList<Item>())
		try {
			World.getInstance().addCastaway(hero);
		} catch (TooManyCastaway e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 1 ; i < numberOfCastaway ; i++) {
			Castaway castaway = new Castaway(false);
			prefix = "castaway_" + i + ".";
			try {
				castaway.setName(save.getProperty(prefix + "name"));
			} catch (NameOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				castaway.setHealth(new Integer(save.getProperty(prefix + "health")));
			} catch (NumberFormatException | HealthOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				castaway.setEnergy(new Integer(save.getProperty(prefix + "energy")));
			} catch (NumberFormatException | EnergyOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				castaway.setMoral(new Integer(save.getProperty(prefix + "moral")));
			} catch (NumberFormatException | MoralOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				castaway.setAffinity(new Integer(save.getProperty(prefix + "affinity")));
			} catch (NumberFormatException | AffinityOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				castaway.setStress(new Integer(save.getProperty(prefix + "stress")));
			} catch (NumberFormatException | StressOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				World.getInstance().addCastaway(castaway);
			} catch (TooManyCastaway e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
