package theisland.savesystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import theisland.castaway.Castaway;
import theisland.gui.Gui;
import theisland.world.World;

public final class Save {
	private final static Save INSTANCE = new Save();
	private static Properties save = new Properties();
	private FileWriter configFile;
	
	private Save() {
	}
	
	public static Save getInstance() {
		return INSTANCE;
	}
	
	/*
	 * Save and write in configuration file the data of a character
	 * @param character: the character's data to save
	 */
	public void saveCharacterData(Castaway character, int characterId) {
		String prefix;
		
		if (character.isHero()) {
			prefix = "hero.";
		} else {
			// TODO: Include all castaway_, not only the first one
			prefix = "castaway_" + characterId + ".";
		}
		save.setProperty(prefix + "name", character.getName());
		save.setProperty(prefix + "health", new Integer(character.getHealth()).toString());
		save.setProperty(prefix + "energy", new Integer(character.getEnergy()).toString());
		save.setProperty(prefix + "moral", new Integer(character.getMoral()).toString());
		save.setProperty(prefix + "affinity", new Integer(character.getAffinity()).toString());
		save.setProperty(prefix + "inventory", character.getInventory().toString());
		
		write();
	}

	public void saveWorldData(World world) {
		save.setProperty("world.weather", world.getWeather().toString());
		save.setProperty("world.numberOfCastaway", new Integer(world.getNumberOfCastaway()).toString());
		save.setProperty("world.dayNumber", new Integer(world.getDayNumber()).toString());
		
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
