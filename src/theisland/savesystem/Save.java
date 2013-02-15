package theisland.savesystem;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import theisland.castaway.Castaway;
import theisland.gui.Gui;
import theisland.item.Item;
import theisland.world.World;

/**
 * The Save class manages to save the game data onto the user's hard drive, in order to keep it through the time.
 * @author Xavier
 *
 */
public final class Save implements Savable {
	private final static Save INSTANCE = new Save();
	private static Properties save = new Properties();
	private FileWriter configFile;

	private Save() {
	}

	public static Save getInstance() {
		return INSTANCE;
	}

	/**
	 * Save and write in configuration file the data of a character
	 * @param character the character's data to save
	 * @param characterId the character's ID in the array
	 */
	public void saveCharacterData(Castaway character, int characterId) {
		String prefix;

		if (character.isHero()) {
			prefix = "hero.";
		} else {
			prefix = "castaway_" + characterId + ".";
		}
		save.setProperty(prefix + "name", character.getName());
		save.setProperty(prefix + "health", new Integer(character.getHealth()).toString());
		save.setProperty(prefix + "energy", new Integer(character.getEnergy()).toString());
		save.setProperty(prefix + "moral", new Integer(character.getMoral()).toString());
		save.setProperty(prefix + "affinity", new Integer(character.getAffinity()).toString());

		String inventoryOut = new String();

		if (!character.getInventory().isEmpty()) {
			final char SEPARATION_CHAR = '&';
			try {
				ObjectOutputStream oStream;
				for (Item item : character.getInventory()) {
					ByteArrayOutputStream inventoryOutBuffer = new ByteArrayOutputStream(); // Let's prepare our inventory...
					oStream = new ObjectOutputStream(inventoryOutBuffer);
					oStream.writeObject(item);
					oStream.close();
					inventoryOut += Base64.encode(inventoryOutBuffer.toByteArray()) + SEPARATION_CHAR;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//save.setProperty(prefix + "inventory", character.getInventory().toString());
		save.setProperty(prefix + "inventory", inventoryOut);

		write();
	}
	
	/**
	 * Save all the characters existing in the world instance.
	 * @param world the world instance to save data from
	 */
	public void saveAllCharacterData(World world) {
		for (int i = 0 ; i < world.getNumberOfCastaway() ; i++) {
			saveCharacterData(world.getCastaway(i), i);
		}
	}

	/**
	 * Save the world data
	 * @param world the world instance to save data from
	 */
	public void saveWorldData(World world) {
		save.setProperty("world.weather", world.getWeather().toString());
		save.setProperty("world.numberOfCastaway", new Integer(world.getNumberOfCastaway()).toString());
		save.setProperty("world.dayNumber", new Integer(world.getDayNumber()).toString());
		save.setProperty("world.maximumDayNumber", new Integer(world.getMaximumDayNumber()).toString());
		if (world.isCabinBuilt()) {
			save.setProperty("world.cabinBuilt", "true");
		} else {
			save.setProperty("world.cabinBuilt", "false");
		}
		
		write();
	}

	private void write() {
		try {
			configFile = new FileWriter("config.sav", false);
		} catch (IOException e) {
			Gui.displayError("The config file cannot be opened.");
			e.printStackTrace();
		}

		try {
			save.store(configFile, "Save file");
		} catch (IOException e) {
			Gui.displayError("The config file cannot be written.");
			e.printStackTrace();
		}
	}
}