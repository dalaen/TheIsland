package theisland.savesystem;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Properties;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import theisland.castaway.Castaway;
import theisland.castaway.exception.InventoryOutOfRange;
import theisland.castaway.exception.NameOutOfRange;
import theisland.gui.Gui;
import theisland.item.Item;
import theisland.world.World;
import theisland.world.exception.InvalidDayNumber;
import theisland.world.exception.TooManyCastaway;

public final class Load implements Loadable {
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
		
		// Get the cabin state
		if (save.containsKey(prefix + "cabinBuilt")) {
			if (save.getProperty(prefix + "cabinBuilt").equals("true")) {
				World.getInstance().setCabinBuilt(true);
			} else if (save.getProperty(prefix + "cabinBuilt").equals("false")) {
				World.getInstance().setCabinBuilt(false);
			}
		}

		// Restore all the castaway statuses
		for (int i = 0 ; i < numberOfCastaway ; i++) {
			Castaway castaway;
			if (i == 0) { // it's a hero
				prefix = "hero.";
				castaway = new Castaway(true);
			} else {
				prefix = "castaway_" + i + ".";
				castaway = new Castaway(false);
			}

			if (save.containsKey(prefix + "name")) {
				try {
					castaway.setName(save.getProperty(prefix + "name"));
				} catch (NameOutOfRange e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return true;
				}
			} else {
				return true;
			}

			if (save.containsKey(prefix + "health")) {
				castaway.setHealth(new Integer(save.getProperty(prefix + "health")));
			} else {
				return true;
			}

			if (save.containsKey(prefix + "energy")) {
				castaway.setEnergy(new Integer(save.getProperty(prefix + "energy")));
			} else {
				return true;
			}

			if (save.containsKey(prefix + "moral")) {
				castaway.setMoral(new Integer(save.getProperty(prefix + "moral")));
			} else {
				return true;
			}

			if (save.containsKey(prefix + "affinity")) {
				castaway.setAffinity(new Integer(save.getProperty(prefix + "affinity")));
			} else {
				return true;
			}

			// -- Inventory
			String savedInventory = null;
			if (save.containsKey(prefix + "inventory") && !save.getProperty(prefix + "inventory").isEmpty()) {
				savedInventory = save.getProperty(prefix + "inventory");
			}

			if (savedInventory != null) {
				final String SEPARATION_CHAR = "&";
				String[] inventoryContent = savedInventory.split(SEPARATION_CHAR);
				for (String s : inventoryContent) {
					ByteArrayInputStream inventoryIn = null;
					try {
						inventoryIn = new ByteArrayInputStream(Base64.decode(s));
					} catch (Base64DecodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} // Bug here on s[1]
					ObjectInputStream iStream = null;
					try {
						iStream = new ObjectInputStream(inventoryIn);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // Let's prepare our inventory...
					try {
						castaway.addItemToInventory((Item) iStream.readObject());
					} catch (ClassNotFoundException | IOException | InventoryOutOfRange e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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