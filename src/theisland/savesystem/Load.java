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
	
	/**
	 * Load from configuration file
	 * @return true if loading has been correctly fulfilled, false if any mistake occurred
	 */
	public boolean load() {
		try {
			configFile = new FileReader("config.sav");
		} catch (IOException e) {
			Gui.displayError("The config file cannot be written");
			e.printStackTrace();
			return true; // Abort loading
		}
		try {
			save.load(configFile);
		} catch (IOException e) {
			Gui.displayError("The config file cannot be read");
			e.printStackTrace();
			return true; // Abort loading
		}
		
		String prefix = new String();
		// Handle world properties
		prefix = "world.";
		
		// TODO: Add security to loaded values (do they exist?)
		
		// Get the weather
		// If none, default weather will be loaded
		if (save.containsKey(prefix + "weather")) {
			World.getInstance().changeWeather(save.getProperty(prefix + "weather"));
		}
		
		// Get the number of castaway
		int numberOfCastaway = 1;
		if (save.containsKey(prefix + "numberOfCastaway")) {
			numberOfCastaway = new Integer(save.getProperty(prefix + "numberOfCastaway"));
		} else {
			// Abort loading if there's no numberOfCastaway
			return true;
		}
		
		// Get the number of the day
		// If it doesn't exist, get the default value
		if (save.containsKey(prefix + "dayNumber")) {
			try {
				World.getInstance().setDayNumber(new Integer(save.getProperty(prefix + "dayNumber")));
			} catch (InvalidDayNumber e) {
				save.remove(prefix + "dayNumber");
				e.printStackTrace();
			}
		}
		
		// Handle hero properties
		// This is required in a save file
		prefix = "hero.";
		Castaway hero = new Castaway(true);
		if (save.containsKey(prefix + "name")) {
			try {
				hero.setName(save.getProperty(prefix + "name"));
			} catch (NameOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return true;
			}
		} else {
			return true;
		}
		
		if (save.containsKey(prefix + "health")) {
			try {
				hero.setHealth(new Integer(save.getProperty(prefix + "health")));
			} catch (HealthOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return true;
			}
		} else {
			return true;
		}
		
		if (save.containsKey(prefix + "energy")) {
			try {
				hero.setEnergy(new Integer(save.getProperty(prefix + "energy")));
			} catch (EnergyOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return true;
			}
		} else {
			return true;
		}
		
		if (save.containsKey(prefix + "moral")) {
			try {
				hero.setMoral(new Integer(save.getProperty(prefix + "moral")));
			} catch (MoralOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return true;
			}
		} else {
			return true;
		}
		
		if (save.containsKey(prefix + "affinity")) {
			try {
				hero.setAffinity(new Integer(save.getProperty(prefix + "affinity")));
			} catch (AffinityOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return true;
			}
		} else {
			return true;
		}
		
		// TODO: Inventory handling, see what separe each item [item;item] [item,item] [item:item] ??
		// -- Inventory
		//ArrayList<Item> inventory = new ArrayList<Item>();
		
		try {
			World.getInstance().addCastaway(hero);
		} catch (TooManyCastaway e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO: Add security on castaway missing elements too
		
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
				try {
					castaway.setHealth(100);
				} catch (HealthOutOfRange e) {}
			}
			try {
				castaway.setEnergy(new Integer(save.getProperty(prefix + "energy")));
			} catch (NumberFormatException | EnergyOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					castaway.setEnergy(100);
				} catch (EnergyOutOfRange e) {}
			}
			try {
				castaway.setMoral(new Integer(save.getProperty(prefix + "moral")));
			} catch (NumberFormatException | MoralOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					castaway.setMoral(100);
				} catch (MoralOutOfRange e) {}
			}
			try {
				castaway.setAffinity(new Integer(save.getProperty(prefix + "affinity")));
			} catch (NumberFormatException | AffinityOutOfRange e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					castaway.setAffinity(100);
				} catch (AffinityOutOfRange e) {}
			}
			
			try {
				World.getInstance().addCastaway(castaway);
			} catch (TooManyCastaway e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
