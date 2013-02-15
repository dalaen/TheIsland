package theisland.savesystem;

import theisland.castaway.Castaway;
import theisland.world.World;

/**
 * The Savable interface must be implemented in order to make a system that can save the game
 * @author Jérémy Huron & Xavier Poirot
 * @version 1.0
 *
 */
public interface Savable {
	/**
	 * Save a character data
	 * @param castaway the character to save
	 * @param characterId the character ID in the big character array
	 */
	public void saveCharacterData(Castaway castaway, int characterId);
	
	/**
	 * Save the world data
	 * @param world the world instance to save
	 */
	public void saveWorldData(World world);
}
